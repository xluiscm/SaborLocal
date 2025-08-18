/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Model;

public class PedidoPersonalizadoModel {

    private int idPedido;
    private int idCliente;
    private String ocasion;
    private String tipoPan;
    private String sabor;
    private String cubierta;
    private String forma;
    private String tamanio;
    private String decoracion;
    private String ingredientes;

    // Constructor para un pedido existente (con ID)
    public PedidoPersonalizadoModel(int idPedido, int idCliente, String ocasion, String tipoPan, String sabor, String cubierta, String forma, String tamanio, String decoracion, String ingredientes) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.ocasion = ocasion;
        this.tipoPan = tipoPan;
        this.sabor = sabor;
        this.cubierta = cubierta;
        this.forma = forma;
        this.tamanio = tamanio;
        this.decoracion = decoracion;
        this.ingredientes = ingredientes;
    }

    // Constructor para un nuevo pedido
    public PedidoPersonalizadoModel(int idCliente, String ocasion, String tipoPan, String sabor, String cubierta, String forma, String tamanio, String decoracion, String ingredientes) {
        this.idCliente = idCliente;
        this.ocasion = ocasion;
        this.tipoPan = tipoPan;
        this.sabor = sabor;
        this.cubierta = cubierta;
        this.forma = forma;
        this.tamanio = tamanio;
        this.decoracion = decoracion;
        this.ingredientes = ingredientes;
    }

    // --- Getters y Setters ---
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

    public String getOcasion() {
        return ocasion;
    }

    public void setOcasion(String ocasion) {
        this.ocasion = ocasion;
    }

    public String getTipoPan() {
        return tipoPan;
    }

    public void setTipoPan(String tipoPan) {
        this.tipoPan = tipoPan;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getCubierta() {
        return cubierta;
    }

    public void setCubierta(String cubierta) {
        this.cubierta = cubierta;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(String decoracion) {
        this.decoracion = decoracion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public PedidoPersonalizadoModel() {

    }
}
