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
import mx.edu.utxicotepec.autolavado.model.UsuariosModel;

/**
 *
 * @author xLuisCM
 */
public class UsuariosController {

    public static boolean insertarUsuarios(UsuariosModel rol) {
        String sql = "INSERT INTO tbc_usuarios (nombre,primerApellido,segundoApellido,direccion,"
                + "telefono,correo,usuario,password,fechaNacimiento,idRol) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getPrimerApellido());
            ps.setString(3, rol.getSegundoApellido());
            ps.setString(4, rol.getDireccion());
            ps.setString(5, rol.getTelefono());
            ps.setString(6, rol.getCorreo());
            ps.setString(7, rol.getUsuario());
            ps.setString(8, rol.getPassword());
            ps.setString(9, rol.getFechaNacimiento());
            ps.setInt(10, rol.getIdRol());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static List<UsuariosModel> mostrarTodos() {
        var lista = new ArrayList<UsuariosModel>();
        String sql = "SELECT u.*, r.nombreRol " + " FROM tbc_usuarios u " + "JOIN tbi_roles r ON u.idRol = r.idRol ";
        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                UsuariosModel user = new UsuariosModel(
                        rs.getInt("idUsuario"),
                        rs.getInt("idRol"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("fechaNacimiento"));
                user.setNombreRolDisplay(rs.getString("nombreRol"));
                lista.add(user);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar usuarios: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static List<UsuariosModel> buscarUsuariosPorTermino(String termino) {
        List<UsuariosModel> lista = new ArrayList<>();
        String sql = "SELECT u.*, r.nombreRol FROM tbc_usuarios u "
                + "JOIN tbi_roles r ON u.idRol = r.idRol "
                + "WHERE u.nombre LIKE ? OR u.primerApellido LIKE ? OR u.segundoApellido LIKE ?";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String parametro = "%" + termino + "%";
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            ps.setString(3, parametro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsuariosModel usuario = new UsuariosModel(
                            rs.getInt("idUsuario"),
                            rs.getInt("idRol"),
                            rs.getString("nombre"),
                            rs.getString("primerApellido"),
                            rs.getString("segundoApellido"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("usuario"),
                            rs.getString("password"),
                            rs.getString("fechaNacimiento")
                    );
                    usuario.setNombreRolDisplay(rs.getString("nombreRol"));
                    lista.add(usuario);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar usuarios: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static boolean modificarUsuario(UsuariosModel usuario) {
        String sql = "UPDATE tbc_usuarios SET nombre = ?, primerApellido = ?, segundoApellido = ?, direccion = ?, "
                + "telefono = ?, correo = ?, usuario = ?, password = ?, fechaNacimiento = ?, idRol = ? "
                + "WHERE idUsuario = ?";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getSegundoApellido());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setString(7, usuario.getUsuario());
            ps.setString(8, usuario.getPassword());
            ps.setString(9, usuario.getFechaNacimiento());
            ps.setInt(10, usuario.getIdRol());
            ps.setInt(11, usuario.getIdUsuario());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar usuario: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM tbc_usuarios WHERE idUsuario = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar usuario: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static UsuariosModel autenticarUsuario(String usuario, String password) {
        String sql = "SELECT * FROM tbc_usuarios WHERE usuario = ? AND password = ?";
        try (Connection con = conexionDB.obtenerConexion(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UsuariosModel(
                            rs.getInt("idUsuario"),
                            rs.getInt("idRol"),
                            rs.getString("nombre"),
                            rs.getString("primerApellido"),
                            rs.getString("segundoApellido"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("usuario"),
                            rs.getString("password"),
                            rs.getString("fechaNacimiento")
                    );
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error de autenticación: " + ex.getMessage());
        }
        return null;
    }

    public static List<UsuariosModel> mostrarOperarios() {
        var listaOperarios = new ArrayList<UsuariosModel>();
        String sql = "SELECT * FROM tbc_usuarios WHERE idRol = 3";

        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                UsuariosModel operario = new UsuariosModel(); // <--- Usas el constructor vacío
                operario.setIdUsuario(rs.getInt("idUsuario"));
                operario.setNombre(rs.getString("nombre"));
                operario.setNombreRolDisplay(rs.getString("idRol"));

                listaOperarios.add(operario);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar operarios: " + ex.getMessage());
            ex.printStackTrace();
        }
        return listaOperarios;
    }
     public static List<UsuariosModel> mostrarCajero() {
        var listaOperarios = new ArrayList<UsuariosModel>();
        String sql = "SELECT * FROM tbc_usuarios WHERE idRol = 1";

        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                UsuariosModel operario = new UsuariosModel(); // <--- Usas el constructor vacío
                operario.setIdUsuario(rs.getInt("idUsuario"));
                operario.setNombre(rs.getString("nombre"));
                operario.setNombreRolDisplay(rs.getString("idRol"));

                listaOperarios.add(operario);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar operarios: " + ex.getMessage());
            ex.printStackTrace();
        }
        return listaOperarios;
    }
    
}
