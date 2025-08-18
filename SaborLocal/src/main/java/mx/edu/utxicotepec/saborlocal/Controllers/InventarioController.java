package mx.edu.utxicotepec.saborlocal.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import mx.edu.utxicotepec.saborlocal.Model.InventarioModel;

public class InventarioController {

    public static boolean guardarIngrediente(InventarioModel ingrediente) {
        String sql = "INSERT INTO inventario (ingrediente, tipo_uso, cantidad) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ingrediente.getIngrediente());
            ps.setString(2, ingrediente.getTipoUso());
            ps.setInt(3, ingrediente.getCantidad());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al guardar ingrediente: " + ex.getMessage());
            return false;
        }
    }

    public static List<InventarioModel> mostrarIngredientes() {
        List<InventarioModel> listaIngredientes = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                InventarioModel inventario = new InventarioModel();

                inventario.setIdIngrediente(rs.getInt("idIngrediente"));
                inventario.setIngrediente(rs.getString("ingrediente"));
                inventario.setTipoUso(rs.getString("tipo_uso"));

                // ⚠️ Corrección: Lee la cantidad como String y luego extrae el número
                String cantidadTexto = rs.getString("cantidad");
                String soloNumeros = "";
                Pattern patron = Pattern.compile("\\d+");
                Matcher m = patron.matcher(cantidadTexto);

                if (m.find()) {
                    soloNumeros = m.group();
                }

                int cantidad = 0;
                if (!soloNumeros.isEmpty()) {
                    cantidad = Integer.parseInt(soloNumeros);
                }
                inventario.setCantidad(cantidad);

                listaIngredientes.add(inventario);
            }
        } catch (SQLException | NumberFormatException ex) {
            System.err.println("Error al mostrar ingredientes: " + ex.getMessage());
        }
        return listaIngredientes;
    }

    public static int obtenerCantidad(String nombreIngrediente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cantidad = 0;
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT cantidad FROM inventario WHERE ingrediente = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreIngrediente);
            rs = ps.executeQuery();
            if (rs.next()) {
                // ⚠️ Corrección: Lee la cantidad como String y luego extrae el número
                String cantidadTexto = rs.getString("cantidad");
                String soloNumeros = "";
                Pattern patron = Pattern.compile("\\d+");
                Matcher m = patron.matcher(cantidadTexto);
                if (m.find()) {
                    soloNumeros = m.group();
                }
                if (!soloNumeros.isEmpty()) {
                    cantidad = Integer.parseInt(soloNumeros);
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al obtener la cantidad del ingrediente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return cantidad;
    }

    public static List<InventarioModel> buscarInventarioPorTermino(String termino) {
        List<InventarioModel> ingredientesEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE ingrediente LIKE ? OR tipo_uso LIKE ? OR cantidad LIKE ?";

        try (Connection conn = Conexion.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String term = "%" + termino + "%";
            pstmt.setString(1, term);
            pstmt.setString(2, term);
            pstmt.setString(3, term);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    InventarioModel ingrediente = new InventarioModel(
                            rs.getInt("idIngrediente"),
                            rs.getString("ingrediente"),
                            rs.getString("tipo_uso"),
                            rs.getInt("cantidad"));
                    ingredientesEncontrados.add(ingrediente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar ingredientes: " + e.getMessage());
            e.printStackTrace();
        }
        return ingredientesEncontrados;
    }

    public static boolean eliminarIngrediente(int id) {
        String sql = "DELETE FROM inventario WHERE idIngrediente = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar ingrediente: " + ex.getMessage());
            return false;
        }
    }

    public static boolean modificarIngrediente(InventarioModel ingrediente) {
        String sql = "UPDATE inventario SET ingrediente=?, tipo_uso=?, cantidad=? WHERE idIngrediente=?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ingrediente.getIngrediente());
            ps.setString(2, ingrediente.getTipoUso());
            ps.setInt(3, ingrediente.getCantidad());
            ps.setInt(4, ingrediente.getIdIngrediente());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar ingrediente: " + ex.getMessage());
            return false;
        }
    }
}
