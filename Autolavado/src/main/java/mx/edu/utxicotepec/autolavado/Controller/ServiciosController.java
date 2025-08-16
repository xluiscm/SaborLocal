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
import mx.edu.utxicotepec.autolavado.model.ServiciosModel;

/**
 *
 * @author xLuisCM
 */
public class ServiciosController {

    public static boolean insertarServicios(ServiciosModel rol) {
        String sql = "INSERT INTO tbi_servicios(nombreServicio, descripcion, precio, estatus)" + "VALUES(?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol.getNombreServicio());
            ps.setString(2, rol.getDescripcion());
            ps.setString(3, rol.getPrecio());
            ps.setString(4, rol.getEstatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al guardar Servicio" + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static List<ServiciosModel> mostrarTodos() {
        var lista = new ArrayList<ServiciosModel>();
        String sql = "SELECT * FROM tbi_servicios";
        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ServiciosModel servi = new ServiciosModel(
                        rs.getInt("idServiicios"),
                        rs.getString("nombreServicio"),
                        rs.getString("descripcion"),
                        rs.getString("precio"),
                        rs.getString("estatus")
                );
                lista.add(servi);
            }
        } catch (SQLException ex) {
            System.out.println("Error al mostrar servicios" + ex.getMessage());
        }
        return lista;
    }

    public static List<ServiciosModel> buscarservicioportermino(String termino) {
        List<ServiciosModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbi_servicios WHERE nombreServicio LIKE ? OR descripcion LIKE ? ";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String parametro = "%" + termino + "%";
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ServiciosModel serv = new ServiciosModel(
                            rs.getInt("idServiicios"),
                            rs.getString("nombreServicio"),
                            rs.getString("descripcion"),
                            rs.getString("precio"),
                            rs.getString("estatus")
                    );
                    lista.add(serv);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar servicio " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public static boolean modificarServicio(ServiciosModel servicio) {
        String sql = "UPDATE  tbi_servicios  SET nombreServicio = ? , descripcion = ? , precio = ?, estatus = ?"
                + "WHERE  idServiicios = ? ";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombreServicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setString(3, servicio.getPrecio());
            ps.setString(4, servicio.getEstatus());
            ps.setInt(5, servicio.getIdServiicios());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar servicio" + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarServicio (int idServiicios) {
        String sql = "DELETE FROM tbi_servicios WHERE idServiicios = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idServiicios);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el servicio"  + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }    
}
