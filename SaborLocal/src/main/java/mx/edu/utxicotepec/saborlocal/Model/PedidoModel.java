/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Model;

/**
 *
 * @author xidon
 */
public class PedidoModel {

    private int idPedido;
    private int idCliente;
    private String fechaPedido;
    private String estado;
    private double totalPedido;
    private String mensaje;
    private String fechaEntregaEstimada;
    private String pastelSeleccionado;
    private String tamanioSeleccionado;

    public String getPastelSeleccionado() {
        return pastelSeleccionado;
    }

    public void setPastelSeleccionado(String pastelSeleccionado) {
        this.pastelSeleccionado = pastelSeleccionado;
    }

    public String getTamanioSeleccionado() {
        return tamanioSeleccionado;
    }

    public void setTamanioSeleccionado(String tamanioSeleccionado) {
        this.tamanioSeleccionado = tamanioSeleccionado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(String fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public PedidoModel(int idPedido, int idCliente, String fechaPedido, String estado, double totalPedido, String mensaje, String fechaEntregaEstimada, String pastelSeleccionado, String tamanioSeleccionado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.totalPedido = totalPedido;
        this.mensaje = mensaje;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.pastelSeleccionado = pastelSeleccionado;
        this.tamanioSeleccionado = tamanioSeleccionado;
    }

    // ➡️ Constructor actualizado que coincide con el código de tu botón
    public PedidoModel(int idCliente, String fechaPedido, String estado, double totalPedido, String mensaje, String fechaEntregaEstimada, String pastelSeleccionado, String tamanioSeleccionado) {
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.totalPedido = totalPedido;
        this.mensaje = mensaje;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.pastelSeleccionado = pastelSeleccionado;
        this.tamanioSeleccionado = tamanioSeleccionado;
    }
}
