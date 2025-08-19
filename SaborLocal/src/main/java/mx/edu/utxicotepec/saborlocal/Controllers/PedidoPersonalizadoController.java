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
    // ✅ Nuevo método para obtener los nombres de los tamaños desde la tabla 'tamanios'

    public static List<String> obtenerNombresTamanios() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM tamanios"; // ✅ ¡Esta es la consulta correcta!
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener nombres de tamaños: " + ex.getMessage());
        }
        return nombres;
    }

    public static boolean guardarPedido(PedidoPersonalizadoModel pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        // ✅ Se agrega 'ingredientes' a la consulta SQL
        String sql = "INSERT INTO pedido_personalizado (IdCliente, ocasion, tipo_pan, sabor, cubierta, forma, tamanio, decoracion, ingredientes, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);

            double tamanioNumerico = convertirTamanio(pedido.getTamanio());

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setString(2, pedido.getOcasion());
            stmt.setString(3, pedido.getTipoPan());
            stmt.setString(4, pedido.getSabor());
            stmt.setString(5, pedido.getCubierta());
            stmt.setString(6, pedido.getForma());
            stmt.setDouble(7, tamanioNumerico);
            stmt.setString(8, pedido.getDecoracion());
            // ✅ Se añade el parámetro de los ingredientes
            stmt.setString(9, pedido.getIngredientes());
            stmt.setString(10, "En espera"); // ✅ Se establece el estado por defecto

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar el pedido: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static boolean modificarPedido(PedidoPersonalizadoModel pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE pedido_personalizado SET IdCliente=?, ocasion=?, tipo_pan=?, sabor=?, cubierta=?, forma=?, tamanio=?, decoracion=?, ingredientes=?, estado=? WHERE IdPedido=?";

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);

            double tamanioNumerico = convertirTamanio(pedido.getTamanio());

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setString(2, pedido.getOcasion());
            stmt.setString(3, pedido.getTipoPan());
            stmt.setString(4, pedido.getSabor());
            stmt.setString(5, pedido.getCubierta());
            stmt.setString(6, pedido.getForma());
            stmt.setDouble(7, tamanioNumerico);
            stmt.setString(8, pedido.getDecoracion());
            stmt.setString(9, pedido.getIngredientes());
            stmt.setString(10, pedido.getEstado());
            stmt.setInt(11, pedido.getIdPedido());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar el pedido: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static List<PedidoPersonalizadoModel> mostrarPedidos() {
        List<PedidoPersonalizadoModel> pedidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // Selecciona solo los campos que se usarán en la interfaz
        String sql = "SELECT IdPedido, ocasion, tipo_pan, sabor, cubierta, forma, tamanio, decoracion, estado FROM pedido_personalizado";

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoPersonalizadoModel pedido = new PedidoPersonalizadoModel();
                pedido.setIdPedido(rs.getInt("IdPedido"));
                pedido.setOcasion(rs.getString("ocasion"));
                pedido.setTipoPan(rs.getString("tipo_pan"));
                pedido.setSabor(rs.getString("sabor"));
                pedido.setCubierta(rs.getString("cubierta"));
                pedido.setForma(rs.getString("forma"));
                pedido.setTamanio(rs.getString("tamanio"));
                pedido.setDecoracion(rs.getString("decoracion"));
                pedido.setEstado(rs.getString("estado"));
                // Se elimina el campo "ingredientes" porque no se usa en la interfaz
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar los pedidos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return pedidos;
    }

    public static boolean eliminarPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM pedido_personalizado WHERE IdPedido = ?";

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el pedido: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static List<PedidoPersonalizadoModel> buscarPedidosPorTermino(String termino) {
        List<PedidoPersonalizadoModel> resultados = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pedido_personalizado WHERE IdPedido LIKE ? OR IdCliente LIKE ? OR ocasion LIKE ? OR tipo_pan LIKE ? OR sabor LIKE ? OR cubierta LIKE ? OR forma LIKE ? OR tamanio LIKE ? OR decoracion LIKE ? OR ingredientes LIKE ? OR estado LIKE ?";

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);
            String terminoBusqueda = "%" + termino + "%";
            for (int i = 1; i <= 11; i++) {
                stmt.setString(i, terminoBusqueda);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoPersonalizadoModel pedido = new PedidoPersonalizadoModel();
                pedido.setIdPedido(rs.getInt("IdPedido"));
                pedido.setIdCliente(rs.getInt("IdCliente"));
                pedido.setOcasion(rs.getString("ocasion"));
                pedido.setTipoPan(rs.getString("tipo_pan"));
                pedido.setSabor(rs.getString("sabor"));
                pedido.setCubierta(rs.getString("cubierta"));
                pedido.setForma(rs.getString("forma"));
                pedido.setTamanio(rs.getString("tamanio"));
                pedido.setDecoracion(rs.getString("decoracion"));
                pedido.setIngredientes(rs.getString("ingredientes"));
                pedido.setEstado(rs.getString("estado"));
                resultados.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar pedidos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resultados;
    }

    public static double convertirTamanio(String tamanioStr) {
        if (tamanioStr == null) {
            return 0.0;
        }
        switch (tamanioStr.toLowerCase()) {
            case "chico":
                return 1.0;
            case "mediano":
                return 2.0;
            case "grande":
                return 3.0;
            default:
                try {
                    return Double.parseDouble(tamanioStr);
                } catch (NumberFormatException e) {
                    return 0.0;
                }
        }
    }

    public static String obtenerNombreTamanioPorId(double idTamanio) {
        if (idTamanio == 1.0) {
            return "Chico";
        }
        if (idTamanio == 2.0) {
            return "Mediano";
        }
        if (idTamanio == 3.0) {
            return "Grande";
        }
        return "Desconocido";
    }

    public static int obtenerIdClientePorNombre(String nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT IdCliente FROM clientes WHERE nombre = ?";

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("IdCliente");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID del cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return 0;
    }
}
