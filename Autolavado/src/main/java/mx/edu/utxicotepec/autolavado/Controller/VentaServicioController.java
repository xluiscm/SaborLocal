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
import mx.edu.utxicotepec.autolavado.model.VentaServicioModel;

/**
 *
 * @author xLuisCM
 */
public class VentaServicioController {

    public static boolean insertarVentaS(VentaServicioModel vs) {
        String sql = "INSERT INTO tbc_venta_servicios(idUsuarioC, idUsuarioO, idServicio, idVehiculo, fecha, hora, estatus, pagado) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (con == null) {
                System.err.println("Error: La conexion a la base de datos es nula");
                return false;
            }
            ps.setInt(1, vs.getIdUsuarioC());
            ps.setInt(2, vs.getIdUsuarioO());
            ps.setInt(3, vs.getIdServicio());
            ps.setInt(4, vs.getIdVehiculo());
            ps.setString(5, vs.getFecha());
            ps.setString(6, vs.getHora());
            ps.setString(7, vs.getEstatus());
            ps.setString(8, vs.getPagado());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar vehiculo: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static List<VentaServicioModel> mostrarTodos() {
        var lista = new ArrayList<VentaServicioModel>();
        String sql = "SELECT * FROM tbc_venta_servicios";
        try (Connection con = conexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                VentaServicioModel venta = new VentaServicioModel(
                        rs.getInt("idVentaServicios"),
                        rs.getInt("idUsuarioC"),
                        rs.getInt("idUsuarioO"),
                        rs.getInt("idServicio"),
                        rs.getInt("idVehiculo"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("estatus"),
                        rs.getString("pagado")
                );
                lista.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println("Error al mostrar venta de servicio" + ex.getMessage());
        }
        return lista;
    }

    public static List<VentaServicioModel> buscarVentasPorTermino(String termino) {
        List<VentaServicioModel> listaVentas = new ArrayList<>();

        // La consulta busca el término en la matrícula del vehículo, nombres de cajeros y lavadores
        String sql = "SELECT vs.* FROM tbc_venta_servicios vs "
                + "JOIN tbc_vehiculo v ON vs.idVehiculo = v.idVehiculo "
                + "JOIN tbc_usuarios uc ON vs.idUsuarioC = uc.idUsuario "
                + "JOIN tbc_usuarios ul ON vs.idUsuarioO = ul.idUsuario "
                + "WHERE v.matricula LIKE ? OR uc.nombre LIKE ? OR ul.nombre LIKE ?";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            String parametro = "%" + termino + "%";
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            ps.setString(3, parametro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VentaServicioModel venta = new VentaServicioModel();
                    venta.setIdVentaServicio(rs.getInt("idVentaServicios"));
                    venta.setIdUsuarioC(rs.getInt("idUsuarioC"));
                    venta.setIdUsuarioO(rs.getInt("idUsuarioO"));
                    venta.setIdServicio(rs.getInt("idServicio"));
                    venta.setIdVehiculo(rs.getInt("idVehiculo"));
                    venta.setFecha(rs.getString("fecha"));
                    venta.setHora(rs.getString("hora"));
                    venta.setEstatus(rs.getString("estatus"));
                    venta.setPagado(rs.getString("pagado"));
                    listaVentas.add(venta);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar ventas por término: " + ex.getMessage());
            ex.printStackTrace();
        }
        return listaVentas;
    }

    public static VentaServicioModel obtenerVentaPorId(int idVenta) {
        String sql = "SELECT * FROM tbc_venta_servicios WHERE idVentaServicios = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    VentaServicioModel venta = new VentaServicioModel();
                    venta.setIdVentaServicio(rs.getInt("idVentaServicios"));
                    venta.setIdUsuarioC(rs.getInt("idUsuarioC"));
                    venta.setIdUsuarioO(rs.getInt("idUsuarioO"));
                    venta.setIdServicio(rs.getInt("idServicio"));
                    venta.setIdVehiculo(rs.getInt("idVehiculo"));
                    venta.setFecha(rs.getString("fecha"));
                    venta.setHora(rs.getString("hora"));
                    venta.setEstatus(rs.getString("estatus"));
                    venta.setPagado(rs.getString("pagado"));
                    return venta;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la venta: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean actualizarVenta(VentaServicioModel venta) {
        String sql = "UPDATE tbc_venta_servicios SET idUsuarioC = ?, idUsuarioO = ?, idServicio = ?, idVehiculo = ?, fecha = ?, hora = ?, estatus = ?, pagado = ? WHERE idVentaServicios = ?";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getIdUsuarioC());
            ps.setInt(2, venta.getIdUsuarioO());
            ps.setInt(3, venta.getIdServicio());
            ps.setInt(4, venta.getIdVehiculo());
            ps.setString(5, venta.getFecha());
            ps.setString(6, venta.getHora());
            ps.setString(7, venta.getEstatus());
            ps.setString(8, venta.getPagado());
            ps.setInt(9, venta.getIdVentaServicio());

            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al actualizar la venta: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    // En tu VentaServicioController.java

    public static boolean eliminarVenta(int idVenta) {
        String sql = "DELETE FROM tbc_venta_servicios WHERE idVentaServicios = ?";

        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar la venta: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
