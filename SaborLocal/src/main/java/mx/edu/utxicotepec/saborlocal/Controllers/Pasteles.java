/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Controllers;

import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xidon
 */
public class Pasteles {

    public static List<String> mostrarPasteles() {
        var listaNombresPasteles = new ArrayList<String>();
        String sql = "SELECT nombre FROM Pasteles ORDER BY nombre"; // Select only the 'nombre' column

        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                listaNombresPasteles.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar pasteles: " + ex.getMessage());
            ex.printStackTrace();
        }
        return listaNombresPasteles;
    }
}
