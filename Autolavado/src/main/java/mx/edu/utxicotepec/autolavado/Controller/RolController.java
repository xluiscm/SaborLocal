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
import javax.swing.JOptionPane;
import mx.edu.utxicotepec.autolavado.DAOS.RolDAOS;
import mx.edu.utxicotepec.autolavado.conexion.conexionDB;
import mx.edu.utxicotepec.autolavado.model.RolModel;

/**
 *
 * @author xLuisCM
 */
public class RolController {

    private RolDAOS rolDAO;

    public RolController() {
        this.rolDAO = new RolDAOS();
    }

    public List<RolModel> obtenertodoslosrols() {
        return rolDAO.obtenertodoslosrols();
    }

    public static boolean insertarRol(RolModel rol) {
        String sql = "INSERT INTO tbi_roles (nombreRol) VALUES (?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol.getNombreRol());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarRol(int idRol) {
        // 1. Eliminar primero a los usuarios que usan este rol
        String sqlDeleteUsuarios = "DELETE FROM tbc_usuarios WHERE idRol = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement psUsuarios = con.prepareStatement(sqlDeleteUsuarios)) {
            psUsuarios.setInt(1, idRol);
            psUsuarios.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Si falla, no continuar
        }

        // 2. Si se eliminaron los usuarios sin error, ahora elimina el rol
        String sqlDeleteRol = "DELETE FROM tbi_roles WHERE idRol = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement psRol = con.prepareStatement(sqlDeleteRol)) {
            psRol.setInt(1, idRol);
            int filasAfectadas = psRol.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<RolModel> mostrarTodos() {
        var lista = new ArrayList<RolModel>();
        String sql = "SELECT * FROM tbi_roles";
        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new RolModel(rs.getInt("idRol"), rs.getString("nombreRol")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
