/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utxicotepec.autolavado.conexion.conexionDB;
import mx.edu.utxicotepec.autolavado.model.ClientesModel;
import mx.edu.utxicotepec.autolavado.model.UsuariosModel;

/**
 *
 * @author xLuisCM
 */
public class ClientesController {

    public static boolean insertarClientes(ClientesModel rol) {
        String sql = "INSERT INTO tbi_clientes( nombre,primerApellido,segundoApellido,direccion,telefono,correo,password)"
                + "VALUES(?,?,?,?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getPrimerApellido());
            ps.setString(3, rol.getSegundoApellido());
            ps.setString(4, rol.getDireccion());
            ps.setString(5, rol.getTelefono());
            ps.setString(6, rol.getCorreo());
            ps.setString(7, rol.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al guardar Cliente " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static List<ClientesModel> mostrarTodos() {
        var lista = new ArrayList<ClientesModel>();
        String sql = "SELECT * FROM tbi_clientes";
        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ClientesModel client = new ClientesModel(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("password"));
                lista.add(client);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar clientes " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static List<ClientesModel> buscarclientesPorTermino(String termino) {
        List<ClientesModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbi_clientes WHERE nombreServicio LIKE ? OR primerApellido LIKE ? OR segundoApellido LIKE ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String parametro = "%" + termino + "%";
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            ps.setString(3, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ClientesModel client = new ClientesModel(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("primerApellido"),
                            rs.getString("segundoApellido"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("password")
                    );
                    lista.add(client);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar cliente " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static boolean modificarcliente(ClientesModel cliente) {
        String sql = "UPDATE    tbi_clientes SET  nombre = ?, primerApellido = ?, segundoApellido = ?, direccion = ?,"
                + "telefono = ?, correo = ?, password = ? "
                + "WHERE idCliente = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPrimerApellido());
            ps.setString(3, cliente.getSegundoApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getPassword());
            ps.setInt(8, cliente.getIdCliente());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar cliente " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM tbi_clientes WHERE idCliente = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar cliente: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
