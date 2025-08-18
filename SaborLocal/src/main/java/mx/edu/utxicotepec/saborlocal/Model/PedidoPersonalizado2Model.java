/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Model;

/**
 *
 * @author xidon
 */
public class PedidoPersonalizado2Model {

    private String clienteNombre;
    private String ocasion;
    private String tipoPan;
    private String sabor;
    private String cubierta;
    private String forma;
    private String decoracion;
    private String textoTamanio;

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
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

    public String getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(String decoracion) {
        this.decoracion = decoracion;
    }

    public String getTextoTamanio() {
        return textoTamanio;
    }

    public void setTextoTamanio(String textoTamanio) {
        this.textoTamanio = textoTamanio;
    }

    public PedidoPersonalizado2Model(String clienteNombre, String ocasion, String tipoPan, String sabor, String cubierta, String forma, String decoracion, String textoTamanio) {
        this.clienteNombre = clienteNombre;
        this.ocasion = ocasion;
        this.tipoPan = tipoPan;
        this.sabor = sabor;
        this.cubierta = cubierta;
        this.forma = forma;
        this.decoracion = decoracion;
        this.textoTamanio = textoTamanio;
    }

    public PedidoPersonalizado2Model() {
        // Este constructor no hace nada, solo existe para que puedas
        // crear un objeto sin pasarle parámetros.
    }
}
