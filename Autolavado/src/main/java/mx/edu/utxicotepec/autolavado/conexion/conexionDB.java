/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.conexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author xLuisCM
 */
public class conexionDB {

    public static final String URL = "jdbc:mysql://localhost:3306/db_autolavado";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver para MySQL Connector/J 8.0+
//
    public static Connection obtenerConexion() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
