package mx.edu.utxicotepec.saborlocal.Controllers;

import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utxicotepec.saborlocal.Model.PedidoModel;

public class PedidoController {

    // ========== GUARDAR PEDIDO ==========
    public static boolean guardarPedido(PedidoModel ped) {
        double precioBase = obtenerPrecioPastelDesdeBD(ped.getPastelSeleccionado());
        double precioAdicional = obtenerPrecioAdicionalTamanioDesdeBD(ped.getTamanioSeleccionado());
        double costoTotal = precioBase + precioAdicional;

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
            return false;
        }

        if (idPedido != -1) {
            String sqlDetalle = "INSERT INTO DetallePedido(idPedido, idPastel, idTamanio, precioUnitario) VALUES (?, ?, ?, ?)";
            try (Connection conn = Conexion.obtenerConexion(); PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle)) {

                int idPastel = obtenerIdPorNombre("Pasteles", "nombre", "idPastel", ped.getPastelSeleccionado(), conn);
                int idTamanio = obtenerIdPorNombre("Tamanios", "nombre", "idTamanio", ped.getTamanioSeleccionado(), conn);

                psDetalle.setInt(1, idPedido);
                psDetalle.setInt(2, idPastel);
                psDetalle.setInt(3, idTamanio);
                psDetalle.setDouble(4, costoTotal);
                psDetalle.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.err.println("Error al insertar el detalle del pedido: " + ex.getMessage());
                eliminarPedido(idPedido); // rollback manual
                return false;
            }
        }
        return false;
    }

    // ========== MODIFICAR PEDIDO ==========
    public static boolean modificarPedido(PedidoModel ped) {
        String sqlPedido = "UPDATE pedidos SET idCliente = ?, estado = ?, totalPedido = ?, mensaje = ?, fechaEntregaEstimada = ? WHERE idPedido = ?";
        String sqlDetalle = "UPDATE DetallePedido SET idPastel = ?, idTamanio = ?, precioUnitario = ? WHERE idPedido = ?";

        try (Connection con = Conexion.obtenerConexion()) {
            con.setAutoCommit(false);

            double precioBase = obtenerPrecioPastelDesdeBD(ped.getPastelSeleccionado());
            double precioAdicional = obtenerPrecioAdicionalTamanioDesdeBD(ped.getTamanioSeleccionado());
            double costoTotal = precioBase + precioAdicional;

            int idPastel = obtenerIdPorNombre("Pasteles", "nombre", "idPastel", ped.getPastelSeleccionado(), con);
            int idTamanio = obtenerIdPorNombre("Tamanios", "nombre", "idTamanio", ped.getTamanioSeleccionado(), con);

            try (PreparedStatement psPedido = con.prepareStatement(sqlPedido)) {
                psPedido.setInt(1, ped.getIdCliente());
                psPedido.setString(2, ped.getEstado());
                psPedido.setDouble(3, costoTotal);
                psPedido.setString(4, ped.getMensaje());
                psPedido.setString(5, ped.getFechaEntregaEstimada());
                psPedido.setInt(6, ped.getIdPedido());
                psPedido.executeUpdate();
            }

            try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                psDetalle.setInt(1, idPastel);
                psDetalle.setInt(2, idTamanio);
                psDetalle.setDouble(3, costoTotal);
                psDetalle.setInt(4, ped.getIdPedido());
                psDetalle.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("Error al modificar pedido: " + ex.getMessage());
            return false;
        }
    }

    // ========== ELIMINAR PEDIDO ==========
    public static boolean eliminarPedido(int id) {
        String sqlDetalle = "DELETE FROM DetallePedido WHERE idPedido = ?";
        String sqlPedido = "DELETE FROM pedidos WHERE idPedido = ?";

        try (Connection con = Conexion.obtenerConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                psDetalle.setInt(1, id);
                psDetalle.executeUpdate();
            }

            try (PreparedStatement psPedido = con.prepareStatement(sqlPedido)) {
                psPedido.setInt(1, id);
                int filas = psPedido.executeUpdate();
                if (filas > 0) {
                    con.commit();
                    return true;
                }
            }

            con.rollback();
            return false;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar pedido: " + ex.getMessage());
            return false;
        }
    }

    // ========== BUSCAR PEDIDOS ==========
    public static List<PedidoModel> buscarPedidosPorTermino(String termino) {
        List<PedidoModel> listaPedidos = new ArrayList<>();
        String sql = "SELECT pe.idPedido, p.nombre AS pastel, t.nombre AS tamanio, c.nombre AS cliente, " // <-- nombre cliente
                + "pe.totalPedido, pe.mensaje, pe.fechaEntregaEstimada, pe.estado "
                + "FROM pedidos AS pe "
                + "JOIN DetallePedido AS dp ON pe.idPedido = dp.idPedido "
                + "JOIN Pasteles AS p ON dp.idPastel = p.idPastel "
                + "JOIN Tamanios AS t ON dp.idTamanio = t.idTamanio "
                + "JOIN Clientes AS c ON pe.idCliente = c.idCliente "
                + "WHERE p.nombre LIKE ? OR t.nombre LIKE ? OR c.nombre LIKE ? OR pe.estado LIKE ?";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String terminoBusqueda = "%" + termino + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, terminoBusqueda);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PedidoModel pedido = new PedidoModel(
                            rs.getInt("idPedido"),
                            rs.getString("pastel"),
                            rs.getString("tamanio"),
                            rs.getString("cliente"), // nombre cliente
                            rs.getDouble("totalPedido"), // 🔹 faltaba este
                            rs.getString("mensaje"),
                            rs.getString("fechaEntregaEstimada"),
                            rs.getString("estado")
                    );
                    listaPedidos.add(pedido);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar pedidos: " + ex.getMessage());
        }
        return listaPedidos;
    }

    // ========== MOSTRAR PEDIDOS ==========
    public static List<PedidoModel> mostrarPedidos() {
        List<PedidoModel> listaPedidos = new ArrayList<>();
        String sql = "SELECT pe.idPedido, p.nombre AS pastel, t.nombre AS tamanio, c.nombre AS cliente, "
                + "pe.totalPedido, pe.mensaje, pe.fechaEntregaEstimada, pe.estado "
                + "FROM pedidos AS pe "
                + "JOIN DetallePedido AS dp ON pe.idPedido = dp.idPedido "
                + "JOIN Pasteles AS p ON dp.idPastel = p.idPastel "
                + "JOIN Tamanios AS t ON dp.idTamanio = t.idTamanio "
                + "JOIN Clientes AS c ON pe.idCliente = c.idCliente";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PedidoModel pedido = new PedidoModel(
                        rs.getInt("idPedido"),
                        rs.getString("pastel"),
                        rs.getString("tamanio"),
                        rs.getString("cliente"),
                        rs.getDouble("totalPedido"),
                        rs.getString("mensaje"),
                        rs.getString("fechaEntregaEstimada"),
                        rs.getString("estado")
                );
                listaPedidos.add(pedido);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar pedidos: " + ex.getMessage());
        }
        return listaPedidos;
    }

    // ========== MÉTODOS AUXILIARES ==========
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
        }
        return precioAdicional;
    }

    public static int obtenerIdClientePorNombre(String nombreCliente) {
        try (Connection con = Conexion.obtenerConexion()) {
            return obtenerIdPorNombre("clientes", "nombre", "idCliente", nombreCliente, con);
        } catch (SQLException ex) {
            System.err.println("Error al obtener ID de cliente: " + ex.getMessage());
            return -1;
        }
    }

    public static String obtenerNombreClientePorId(int idCliente) {
        String sql = "SELECT nombre FROM clientes WHERE idCliente = ?";
        String nombre = null;
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener nombre de cliente: " + ex.getMessage());
        }
        return nombre;
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
}
