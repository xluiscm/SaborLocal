/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.model;

import java.sql.*;

/**
 *
 * @author xLuisCM
 */
public class VentaServicioModel {

    public int idVentaServicio;
    private int idUsuarioC;
    private int idUsuarioO;
    private int idServicio;
    private int idVehiculo;
    private String fecha;
    private String hora;
    private String estatus;
    private String pagado;

    public void setIdVentaServicio(int idVentaServicio) {
        this.idVentaServicio = idVentaServicio;
    }

    public void setIdUsuarioC(int idUsuarioC) {
        this.idUsuarioC = idUsuarioC;
    }

    public void setIdUsuarioO(int idUsuarioO) {
        this.idUsuarioO = idUsuarioO;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public int getIdVentaServicio() {
        return idVentaServicio;
    }

    public int getIdUsuarioC() {
        return idUsuarioC;
    }

    public int getIdUsuarioO() {
        return idUsuarioO;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getPagado() {
        return pagado;
    }

    public VentaServicioModel(int idVentaServicio, int idUsuarioC, int idUsuarioO, int idServicio, int idVehiculo, String fecha,
            String hora, String estatus, String pagado) {
        this.idVentaServicio = idVentaServicio;
        this.idUsuarioC = idUsuarioC;
        this.idUsuarioO = idUsuarioO;
        this.idServicio = idServicio;
        this.idVehiculo = idVehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.pagado = pagado;
    }

    public VentaServicioModel(int idUsuarioC, int idUsuarioO, int idServicio, int idVehiculo, String fecha,
            String hora, String estatus, String pagado) {
        this.idUsuarioC = idUsuarioC;
        this.idUsuarioO = idUsuarioO;
        this.idServicio = idServicio;
        this.idVehiculo = idVehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.pagado = pagado;
    }

    public VentaServicioModel() {

    }
}
