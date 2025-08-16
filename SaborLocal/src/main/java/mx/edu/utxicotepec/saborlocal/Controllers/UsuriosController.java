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
import mx.edu.utxicotepec.saborlocal.DAOS.UsuariosModel;

/**
 *
 * @author xidon
 */
public class UsuriosController {

    public static UsuariosModel verificarUsuario(String nombreUsuario, String password) {
        String sql = "SELECT idUsuario, nombreUsuario, contraseña, rolUsuario FROM usuarios WHERE nombreUsuario = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Asignamos el valor al primer y único parámetro de la consulta.
            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String contraseñaBD = rs.getString("contraseña");
                    String rolUsuario = rs.getString("rolUsuario");

                    // Aquí, en el código Java, verificamos si la contraseña coincide.
                    if (password.equals(contraseñaBD)) {
                        return new UsuariosModel(
                                rs.getInt("idUsuario"),
                                rs.getString("nombreUsuario"),
                                contraseñaBD,
                                rolUsuario
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error de autenticación: " + ex.getMessage());
        }
        return null;
    }
}
