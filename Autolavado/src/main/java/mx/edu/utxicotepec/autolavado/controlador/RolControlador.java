/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.edu.utxicotepec.autolavado.conexion.conexionDB;

/**
 *
 * @author xLuisCM
 */
public class RolControlador {

    public static boolean insertarRol() {
        String sql = "INSERT INTO tbi_roles (nombreRol) VALUES (?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
