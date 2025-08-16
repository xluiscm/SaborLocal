/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utxicotepec.autolavado.conexion.conexionDB;
import mx.edu.utxicotepec.autolavado.model.RolModel;
    
/**
 *
 * @author xLuisCM
 */
public class RolDAOS {
    public List<RolModel> obtenertodoslosrols() {
        List<RolModel> roles = new ArrayList<>();
        String sql = "SELECT idRol, nombreRol FROM tbi_roles";
        try (Connection con = conexionDB.obtenerConexion();
           PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
            while (rs.next()) {
                int idRol = rs.getInt("idRol");
                String nombreRol = rs.getString("nombreRol");
                roles.add(new RolModel(idRol, nombreRol));
            } 
        } catch (SQLException e) {
            System.err.println("Error al obtener los roles: " + e.getMessage());
            e.printStackTrace();
        }
        return roles;
    }
}
