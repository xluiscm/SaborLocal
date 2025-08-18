package mx.edu.utxicotepec.saborlocal.Controllers;

import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import mx.edu.utxicotepec.saborlocal.Model.PedidoPersonalizadoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoPersonalizadoController {

    // ✅ Method to get a list of clients
    public static List<String> obtenerClientes() {
        List<String> clientes = new ArrayList<>();
        String sql = "SELECT nombre FROM clientes";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clientes.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener clientes: " + ex.getMessage());
        }
        return clientes;
    }

    // ✅ Method to get unique values from a column (from a 'catalogos' table)
    public static List<String> obtenerValoresUnicos(String columna) {
        List<String> valores = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columna + " FROM catalogos";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                valores.add(rs.getString(columna));
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener valores para " + columna + ": " + ex.getMessage());
        }
        return valores;
    }

    // ✅ Method to save a new order
    public static boolean guardarPedido(PedidoPersonalizadoModel pedido) {
        String sql = "INSERT INTO pedido_personalizado (IdCliente, ocasion, tipo_pan, sabor, cubierta, forma, tamanio, decoracion, ingredientes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setString(2, pedido.getOcasion());
            ps.setString(3, pedido.getTipoPan());
            ps.setString(4, pedido.getSabor());
            ps.setString(5, pedido.getCubierta());
            ps.setString(6, pedido.getForma());
            ps.setString(7, pedido.getTamanio());
            ps.setString(8, pedido.getDecoracion());
            ps.setString(9, pedido.getIngredientes());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al guardar pedido personalizado: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    // ✅ Method to modify an existing order
    public static boolean modificarPedido(PedidoPersonalizadoModel pedido) {
        String sql = "UPDATE pedido_personalizado SET IdCliente = ?, ocasion = ?, tipo_pan = ?, sabor = ?, cubierta = ?, forma = ?, tamanio = ?, decoracion = ?, ingredientes = ? WHERE IdPedido = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setString(2, pedido.getOcasion());
            ps.setString(3, pedido.getTipoPan());
            ps.setString(4, pedido.getSabor());
            ps.setString(5, pedido.getCubierta());
            ps.setString(6, pedido.getForma());
            ps.setString(7, pedido.getTamanio());
            ps.setString(8, pedido.getDecoracion());
            ps.setString(9, pedido.getIngredientes());
            ps.setInt(10, pedido.getIdPedido());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar pedido personalizado: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    // ✅ Method to delete an order
    public static boolean eliminarPedido(int id) {
        String sql = "DELETE FROM pedido_personalizado WHERE IdPedido = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar pedido personalizado: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    // ✅ Method to show all orders
    public static List<PedidoPersonalizadoModel> mostrarPedidos() {
        List<PedidoPersonalizadoModel> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido_personalizado";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PedidoPersonalizadoModel pedido = new PedidoPersonalizadoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("IdCliente"),
                        rs.getString("ocasion"),
                        rs.getString("tipo_pan"),
                        rs.getString("sabor"),
                        rs.getString("cubierta"),
                        rs.getString("forma"),
                        rs.getString("tamanio"),
                        rs.getString("decoracion"),
                        rs.getString("ingredientes")
                );
                listaPedidos.add(pedido);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar pedidos personalizados: " + ex.getMessage());
        }
        return listaPedidos;
    }

    // ✅ Method to search for orders by a term
    public static List<PedidoPersonalizadoModel> buscarPedidosPorTermino(String termino) {
        List<PedidoPersonalizadoModel> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido_personalizado "
                + "WHERE ocasion LIKE ? OR tipo_pan LIKE ? OR sabor LIKE ? OR cubierta LIKE ? OR forma LIKE ? OR tamanio LIKE ? OR decoracion LIKE ? OR ingredientes LIKE ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String terminoBusqueda = "%" + termino + "%";
            ps.setString(1, terminoBusqueda);
            ps.setString(2, terminoBusqueda);
            ps.setString(3, terminoBusqueda);
            ps.setString(4, terminoBusqueda);
            ps.setString(5, terminoBusqueda);
            ps.setString(6, terminoBusqueda);
            ps.setString(7, terminoBusqueda);
            ps.setString(8, terminoBusqueda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PedidoPersonalizadoModel pedido = new PedidoPersonalizadoModel(
                            rs.getInt("IdPedido"),
                            rs.getInt("IdCliente"),
                            rs.getString("ocasion"),
                            rs.getString("tipo_pan"),
                            rs.getString("sabor"),
                            rs.getString("cubierta"),
                            rs.getString("forma"),
                            rs.getString("tamanio"),
                            rs.getString("decoracion"),
                            rs.getString("ingredientes")
                    );
                    listaPedidos.add(pedido);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar pedidos personalizados: " + ex.getMessage());
        }
        return listaPedidos;
    }

    // ✅ Helper method to get the client ID by name
    public static int obtenerIdClientePorNombre(String nombreCliente) {
        String sql = "SELECT IdCliente FROM clientes WHERE nombre = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IdCliente");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el ID del cliente: " + ex.getMessage());
        }
        return -1;
    }

    // ✅ Helper method to get the client name by ID
    public static String obtenerNombreClientePorId(int idCliente) {
        String sql = "SELECT nombre FROM clientes WHERE IdCliente = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el nombre del cliente: " + ex.getMessage());
        }
        return null;
    }
}
