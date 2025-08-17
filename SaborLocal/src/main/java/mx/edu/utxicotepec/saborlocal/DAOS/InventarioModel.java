/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.DAOS;

/**
 *
 * @author xidon
 */
public class InventarioModel {

    private int idIngrediente;
    private String ingrediente;
    private String tipoUso;
    private String cantidad;

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public InventarioModel(int idIngrediente, String ingrediente, String tipoUso, String cantidad) {
        this.idIngrediente = idIngrediente;
        this.ingrediente = ingrediente;
        this.tipoUso = tipoUso;
        this.cantidad = cantidad;
    }

    public InventarioModel(String ingrediente, String tipoUso, String cantidad) {
        this.ingrediente = ingrediente;
        this.tipoUso = tipoUso;
        this.cantidad = cantidad;
    }
}
