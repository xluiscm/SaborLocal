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
public class Tamanio {

    public static List<String> mostrarTamanios() {
        var listaNombresTamanios = new ArrayList<String>();
        String sql = "SELECT nombre FROM Tamanios ORDER BY nombre";

        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                listaNombresTamanios.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar tamaños: " + ex.getMessage());
            ex.printStackTrace();
        }
        return listaNombresTamanios;
    }
}
