/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Controllers;

import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import mx.edu.utxicotepec.saborlocal.Model.PedidoModel;

/**
 *
 * @author xidon
 */
public class PedidoController {

    public static boolean guardarPedido(PedidoModel ped) {

        double precioBase = obtenerPrecioPastelDesdeBD(ped.getPastelSeleccionado());
        double precioAdicional = obtenerPrecioAdicionalTamanioDesdeBD(ped.getTamanioSeleccionado());
        double costoTotal = precioBase + precioAdicional;

        // 1. Insertar en la tabla 'pedidos'
        String sqlPedido = "INSERT INTO pedidos(idCliente, fechaPedido, estado, totalPedido, mensaje, fechaEntregaEstimada) VALUES (?, ?, ?, ?, ?, ?)";
        int idPedido = -1;

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement psPedido = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {

            psPedido.setInt(1, ped.getIdCliente());
            psPedido.setString(2, ped.getFechaPedido());
            psPedido.setString(3, ped.getEstado());
            psPedido.setDouble(4, costoTotal);
            psPedido.setString(5, ped.getMensaje());
            psPedido.setString(6, ped.getFechaEntregaEstimada());
            psPedido.executeUpdate();

            try (ResultSet rs = psPedido.getGeneratedKeys()) {
                if (rs.next()) {
                    idPedido = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error al insertar el pedido: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        // 2. Insertar en la tabla 'DetallePedido'
        if (idPedido != -1) {
            String sqlDetalle = "INSERT INTO DetallePedido(idPedido, idPastel, idTamanio, precioUnitario) VALUES (?, ?, ?, ?)";
            try (Connection con = Conexion.obtenerConexion(); PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {

                int idPastel = obtenerIdPorNombre("Pasteles", "nombre", "idPastel", ped.getPastelSeleccionado(), con);
                int idTamanio = obtenerIdPorNombre("Tamanios", "nombre", "idTamanio", ped.getTamanioSeleccionado(), con);

                double precioUnitario = costoTotal;

                psDetalle.setInt(1, idPedido);
                psDetalle.setInt(2, idPastel);
                psDetalle.setInt(3, idTamanio);
                psDetalle.setDouble(4, precioUnitario);
                psDetalle.executeUpdate();

            } catch (SQLException ex) {
                System.err.println("Error al insertar el detalle del pedido: " + ex.getMessage());
                ex.printStackTrace();
                // Opcional: si falla el detalle, podrías considerar eliminar el pedido para mantener la consistencia de la BD
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static double obtenerPrecioPastelDesdeBD(String nombrePastel) {
        String sql = "SELECT precioBase FROM Pasteles WHERE nombre = ?";
        double precio = 0.0;
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombrePastel);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    precio = rs.getDouble("precioBase");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el precio del pastel: " + ex.getMessage());
            ex.printStackTrace();
        }
        return precio;
    }

    public static double obtenerPrecioAdicionalTamanioDesdeBD(String nombreTamanio) {
        String sql = "SELECT precioAdicional FROM Tamanios WHERE nombre = ?";
        double precioAdicional = 0.0;
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreTamanio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    precioAdicional = rs.getDouble("precioAdicional");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el precio adicional por tamaño: " + ex.getMessage());
            ex.printStackTrace();
        }
        return precioAdicional;
    }

    private static int obtenerIdPorNombre(String tabla, String columnaBusqueda, String columnaId, String valor, Connection con) throws SQLException {
        String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + columnaBusqueda + " = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public static void cargarDatosPedidos(DefaultTableModel modeloTabla) {
        // Limpiamos los datos existentes en la tabla
        modeloTabla.setRowCount(0);

        // Definimos la consulta SQL para obtener los datos
String sql = "SELECT dp.idDetallePedido, p.nombre AS Pastel, t.nombre AS Tamanio, c.nombre AS Cliente, pe.totalPedido AS Costo, pe.mensaje AS Mensaje, "
        + "pe.fechaEntregaEstimada AS 'Fecha de entrega', pe.estado AS Estado FROM DetallePedido AS dp JOIN Pedidos AS pe ON dp.idPedido = pe.idPedido "
        + "JOIN Pasteles AS p ON dp.idPastel = p.idPastel JOIN Tamanios AS t ON dp.idTamanio = t.idTamanio JOIN Clientes AS c ON pe.idCliente = c.idCliente "
        + "ORDER BY dp.idDetallePedido ASC";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            // Recorremos el resultado de la consulta
            while (rs.next()) {
                // Obtenemos los datos de cada columna
                int idDetalle = rs.getInt("idDetallePedido");
                String pastel = rs.getString("Pastel");
                String tamanio = rs.getString("Tamanio");
                String cliente = rs.getString("Cliente");
                String costo = rs.getString("Costo"); // <-- Obtener el valor de la columna 'Costo'
                String mensaje = rs.getString("Mensaje");
                String fechaEntrega = rs.getString("Fecha de entrega");
                String estado = rs.getString("Estado");

                // Creamos un arreglo de objetos con los datos de una fila
                // <-- El orden debe coincidir con las columnas de tu JTable
                Object[] fila = {idDetalle, pastel, tamanio, cliente, costo, mensaje, fechaEntrega, estado};

                // Agregamos la fila al modelo de la tabla
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            System.err.println("Error al cargar los datos de los pedidos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
