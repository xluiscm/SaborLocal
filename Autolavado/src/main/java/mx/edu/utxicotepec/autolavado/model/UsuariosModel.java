/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.model;

import java.sql.Date;

/**
 *
 * @author xLuisCM
 */
public class UsuariosModel {

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdRol() {
        return idRol;
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

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    private int idUsuario;
    private int idRol;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccion;
    private String telefono;
    private String correo;
    private String usuario;
    private String password;
    private String fechaNacimiento;
    private String nombreRolDisplay;

    public String getNombreRolDisplay() {
        return nombreRolDisplay;
    }

    public void setNombreRolDisplay(String nombreRolDisplay) {
        this.nombreRolDisplay = nombreRolDisplay;
    }

    public UsuariosModel(int idUsuario, int idRol, String nombre, String primerApellido, String segundoApellido,
            String direccion, String telefono, String correo, String usuario, String password, String fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    public UsuariosModel(int idRol, String nombre, String primerApellido, String segundoApellido,
            String direccion, String telefono, String correo, String usuario, String password, String fechaNacimiento) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        // Asume que tu clase tiene un campo llamado 'nombre'
        return nombre;
    }
    

    public UsuariosModel() {

    }

}
