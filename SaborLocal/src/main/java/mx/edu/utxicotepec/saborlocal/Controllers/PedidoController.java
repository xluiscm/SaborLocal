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
import mx.edu.utxicotepec.saborlocal.DAOS.PedidoModel;

/**
 *
 * @author xidon
 */
public class PedidoController {

    public static boolean guardarPedido(PedidoModel ped) {
        Connection con = null;
        PreparedStatement psPedido = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;
        boolean exito = false;

        try {
            con = Conexion.obtenerConexion();
            con.setAutoCommit(false); // ➡️ Inicia la transacción

            // 1. Insertar en la tabla 'pedidos'
            String sqlPedido = "INSERT INTO pedidos(idCliente, fechaPedido, estado, totalPedido, mensaje, fechaEntregaEstimada) VALUES (?, ?, ?, ?, ?, ?)";
            psPedido = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            psPedido.setInt(1, ped.getIdCliente());
            psPedido.setString(2, ped.getFechaPedido());
            psPedido.setString(3, ped.getEstado());
            psPedido.setDouble(4, ped.getTotalPedido());
            psPedido.setString(5, ped.getMensaje());
            psPedido.setString(6, ped.getFechaEntregaEstimada());
            psPedido.executeUpdate();

            // 2. Obtener el ID del pedido recién insertado
            rs = psPedido.getGeneratedKeys();
            if (rs.next()) {
                int idPedido = rs.getInt(1);

                // 3. Insertar en la tabla 'DetallePedido'
                // ➡️ Corrección: Eliminada la columna 'cantidad' de la sentencia SQL
                String sqlDetalle = "INSERT INTO DetallePedido(idPedido, idPastel, idTamanio, precioUnitario) VALUES (?, ?, ?, ?)";
                psDetalle = con.prepareStatement(sqlDetalle);

                int idPastel = obtenerIdPorNombre("Pasteles", "nombre", "idPastel", ped.getPastelSeleccionado(), con);
                int idTamanio = obtenerIdPorNombre("Tamanios", "nombre", "idTamanio", ped.getTamanioSeleccionado(), con);

                psDetalle.setInt(1, idPedido);
                psDetalle.setInt(2, idPastel);
                psDetalle.setInt(3, idTamanio);
                // ➡️ Corrección: Eliminada la línea que asignaba el valor a 'cantidad'
                psDetalle.setDouble(4, 0.0);
                psDetalle.executeUpdate();
            }

            con.commit(); // ➡️ Confirma la transacción
            exito = true;
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.rollback(); // ➡️ Revierte la transacción en caso de error
                } catch (SQLException ex1) {
                    System.err.println("Error en rollback: " + ex1.getMessage());
                }
            }
            System.err.println("Error al guardar el pedido: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psPedido != null) {
                    psPedido.close();
                }
                if (psDetalle != null) {
                    psDetalle.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return exito;
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
        String sql = "SELECT dp.idDetallePedido, p.nombre AS Pastel, t.nombre AS Tamanio, c.nombre AS Cliente, pe.mensaje AS Mensaje, pe.fechaEntregaEstimada AS 'Fecha de entrega', pe.estado AS Estado FROM DetallePedido AS dp JOIN Pedidos AS pe ON dp.idPedido = pe.idPedido JOIN Pasteles AS p ON dp.idPastel = p.idPastel JOIN Tamanios AS t ON dp.idTamanio = t.idTamanio JOIN Clientes AS c ON pe.idCliente = c.idCliente";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            // Recorremos el resultado de la consulta
            while (rs.next()) {
                // Obtenemos los datos de cada columna
                int idDetalle = rs.getInt("idDetallePedido");
                String pastel = rs.getString("Pastel");
                String tamanio = rs.getString("Tamanio");
                String cliente = rs.getString("Cliente");
                String mensaje = rs.getString("Mensaje");
                String fechaEntrega = rs.getString("Fecha de entrega");
                String estado = rs.getString("Estado");

                // Creamos un arreglo de objetos con los datos de una fila
                Object[] fila = {idDetalle, pastel, tamanio, cliente, mensaje, fechaEntrega, estado};

                // Agregamos la fila al modelo de la tabla
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            System.err.println("Error al cargar los datos de los pedidos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
