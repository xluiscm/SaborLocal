/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.autolavado.model;

/**
 *
 * @author xLuisCM
 */

public class RolModel {

    private int idRol;
    private String nombreRol;

    public RolModel (int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public RolModel(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public RolModel () {
        
    }

    public int getIdRol() {
        return this.idRol; 
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() { 
        return this.nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
   @Override
   public String toString() {
       return nombreRol;
   }
}
