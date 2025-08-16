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
import java.util.ArrayList;
import java.util.List;
import mx.edu.utxicotepec.saborlocal.DAOS.ClientesModel;

/**
 *
 * @author xidon
 */
public class ClientesController {

    public static boolean guardarCliente(ClientesModel cli) {
        String sql = "INSERT INTO Clientes(nombre, apellidoPaterno, apellidoMaterno, direccion, genero, fechaNacimiento, telefono, edad, idUsuario)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellidoPaterno());
            ps.setString(3, cli.getApellidoMaterno());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getGenero());
            ps.setString(6, cli.getFecha());
            ps.setString(7, cli.getTelefono());
            ps.setInt(8, cli.getEdad());
            ps.setInt(9, cli.getIdUsuario());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al guardar cliente: " + ex.getMessage());
            return false;
        }
    }

    public static List<ClientesModel> mostrarTodos() {
        var lista = new ArrayList<ClientesModel>();
        String sql = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, direccion, genero, fechaNacimiento, telefono, edad, idUsuario FROM Clientes";
        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ClientesModel client = new ClientesModel(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getString("direccion"),
                        rs.getString("genero"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getInt("idUsuario"));
                lista.add(client);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar clientes " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    // Método corregido para obtener el ID del cliente por su nombre completo.
    public static int obtenerIdClientePorNombreCompleto(String nombreCompleto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idCliente = -1; // Valor por defecto

        try {
            conn = Conexion.obtenerConexion(); // Uso consistente del método de conexión
            String sql = "SELECT idCliente FROM Clientes WHERE CONCAT(nombre, ' ', apellidoPaterno, ' ', apellidoMaterno) = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreCompleto);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("idCliente"); // Uso consistente del nombre de la columna
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idCliente;
    }
}
