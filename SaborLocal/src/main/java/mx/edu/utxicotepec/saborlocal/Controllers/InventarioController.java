/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import mx.edu.utxicotepec.saborlocal.DAOS.InventarioModel;

/**
 *
 * @author xidon
 */
public class InventarioController {

    public static boolean guardarIngrediente(InventarioModel ingrediente) {
        String sql = "INSERT INTO inventario (ingrediente, tipo_uso, cantidad) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ingrediente.getIngrediente());
            ps.setString(2, ingrediente.getTipoUso());
            ps.setString(3, ingrediente.getCantidad());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al guardar ingrediente: " + ex.getMessage());
            return false;
        }
    }

// Método para modificar un ingrediente existente
    public static boolean modificarIngrediente(InventarioModel ingrediente) {
        String sql = "UPDATE inventario SET ingrediente = ?, tipo_uso = ?, cantidad = ? WHERE idIngrediente = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ingrediente.getIngrediente());
            ps.setString(2, ingrediente.getTipoUso());
            ps.setString(3, ingrediente.getCantidad());
            ps.setInt(4, ingrediente.getIdIngrediente());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar ingrediente: " + ex.getMessage());
            return false;
        }
    }

// Método para buscar un ingrediente por ID
    public static InventarioModel buscarIngrediente(int id) {
        InventarioModel ingrediente = null;
        String sql = "SELECT * FROM inventario WHERE idIngrediente = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ingrediente = new InventarioModel(
                            rs.getInt("idIngrediente"),
                            rs.getString("ingrediente"),
                            rs.getString("tipo_uso"),
                            rs.getString("cantidad"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar ingrediente: " + ex.getMessage());
        }
        return ingrediente;
    }

// Método para mostrar todos los ingredientes
    public static List<InventarioModel> mostrarIngredientes() {
        var lista = new ArrayList<InventarioModel>();
        String sql = "SELECT idIngrediente, ingrediente, tipo_uso, cantidad FROM inventario";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                InventarioModel ingredient = new InventarioModel(
                        rs.getInt("idIngrediente"),
                        rs.getString("ingrediente"),
                        rs.getString("tipo_uso"),
                        rs.getString("cantidad"));
                lista.add(ingredient);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar ingredientes: " + ex.getMessage());
        }
        return lista;
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
                            rs.getString("cantidad")
                    );
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
}
