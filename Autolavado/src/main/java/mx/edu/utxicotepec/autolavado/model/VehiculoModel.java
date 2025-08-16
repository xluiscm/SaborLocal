/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.model;

/**
 *
 * @author xLuisCM
 */
public class VehiculoModel {

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    private int idVehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int año;
    private int idCliente;
    private String tipo;

    public VehiculoModel(int idVehiculo, String matricula, String marca, String modelo, String color, int año, int idCliente, String tipo) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.año = año;
        this.idCliente = idCliente;
        this.tipo = tipo;
    }

    public VehiculoModel(String matricula, String marca, String modelo, String color, int año, int idCliente, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.año = año;
        this.idCliente = idCliente;
        this.tipo = tipo;
    }

    public VehiculoModel() {

    }

}
