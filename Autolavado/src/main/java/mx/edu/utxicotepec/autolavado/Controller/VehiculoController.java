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
import mx.edu.utxicotepec.autolavado.model.VehiculoModel;

/**
 *
 * @author xLuisCM
 */
public class VehiculoController {

    public static boolean insertarVehiculo(VehiculoModel vehiculo) {
        String sql = "INSERT INTO tbc_vehiculo(matricula, marca, modelo, color, year, idCliente, tipo) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.err.println("Error: La conexión a la base de datos es nula.");
                return false;
            }

            ps.setString(1, vehiculo.getMatricula());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());
            ps.setInt(5, vehiculo.getAño());
            ps.setInt(6, vehiculo.getIdCliente());
            ps.setString(7, vehiculo.getTipo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se insertó al menos una fila

        } catch (SQLException ex) {
            System.err.println("Error al insertar vehiculo: " + ex.getMessage());
            ex.printStackTrace();
            return false; // Devuelve false si ocurre una excepción
        }
    }

    public static List<VehiculoModel> mostrarTodos() {
        var lista = new ArrayList<VehiculoModel>();
        String sql = "SELECT v.idVehiculo, v.matricula, v.marca, v.modelo, v.color, v.year, v.tipo, c.idCliente, c.nombre, c.primerApellido, c.segundoApellido "
                + "FROM tbc_vehiculo v "
                + "JOIN tbi_clientes c ON v.idCliente = c.idCliente";
        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                VehiculoModel vehiculo = new VehiculoModel(
                        rs.getInt("idVehiculo"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("color"),
                        rs.getInt("year"),
                        rs.getInt("idCliente"),
                        rs.getString("tipo")
                );
                lista.add(vehiculo);
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar vehiculo " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static List<VehiculoModel> buscarvehiculosPorTermino(String termino) {
        List<VehiculoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbc_vehiculo WHERE matricula LIKE ? OR marca LIKE ? OR modelo LIKE ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String parametro = "%" + termino + "%";
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            ps.setString(3, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VehiculoModel Vehiculo = new VehiculoModel(
                            rs.getInt("idVehiculo"),
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getString("color"),
                            rs.getInt("year"),
                            rs.getInt("idCliente"),
                            rs.getString("tipo")
                    );
                    lista.add(Vehiculo);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar vehiculo " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static boolean modificarVehiculo(VehiculoModel vehiculo) {
        String sql = "UPDATE tbc_vehiculo SET matricula = ?, marca = ?, modelo = ?, color = ? , year = ?, idCliente = ?, tipo  = ?"
                + "WHERE idVehiculo = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getMatricula());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());
            ps.setInt(5, vehiculo.getAño());
            ps.setInt(6, vehiculo.getIdCliente());
            ps.setString(7, vehiculo.getTipo());
            ps.setInt(8, vehiculo.getIdVehiculo());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar cliente " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarvehiculo(int idVehiculo) {
        String sql = "DELETE FROM tbc_vehiculo WHERE   idVehiculo = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar vehiculo" + ex.getMessage());
            return false;
        }
    }

    public static int buscarIdVehiculoPorMatricula(String matricula) {
        int idVehiculo = 0; // Valor por defecto si no se encuentra
        String sql = "SELECT idVehiculo FROM tbc_vehiculo WHERE matricula = ?";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idVehiculo = rs.getInt("idVehiculo");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar vehículo: " + ex.getMessage());
            ex.printStackTrace();
        }
        return idVehiculo;
    }
}
