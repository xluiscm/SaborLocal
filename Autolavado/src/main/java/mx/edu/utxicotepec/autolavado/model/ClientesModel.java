/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.model;

/**
 *
 * @author xLuisCM
 */
public class ClientesModel {

    private int idCliente;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccion;
    private String telefono;
    private String correo;
    private String password;

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public ClientesModel(int idCliente, String nombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correo, String password) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
    }

    public ClientesModel(String nombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correo, String password) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;

    }

    public ClientesModel() {

    }

    @Override
    public String toString() {
        return nombre + " "  + primerApellido + " " + segundoApellido;
    }
}
