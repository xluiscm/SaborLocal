/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.DAOS;

/**
 *
 * @author xidon
 */
public class UsuariosModel {

    private int idUsuario;
    private String nombreUsuario;
    private String password;
    private String rolUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;

    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public UsuariosModel(int idUsuario, String nombreUsuario, String password, String rolUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

    public UsuariosModel(String nombreUsuario, String password, String rolUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

    public UsuariosModel() {

    }
}
