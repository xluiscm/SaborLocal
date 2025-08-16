package mx.edu.utxicotepec.autolavado.model;

/**
 *
 * @author xLuisCM
 */
public class ServiciosModel {

    private int idServiicios;
    private String nombreServicio;
    private String descripcion;
    private String precio;
    private String estatus;

    public void setIdServiicios(int idServiicios) {
        this.idServiicios = idServiicios;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdServiicios() {
        return idServiicios;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getEstatus() {
        return estatus;
    }

    public ServiciosModel(int idServiicios, String nombreServicio, String descripcion, String precio, String estatus) {
        this.idServiicios = idServiicios;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estatus = estatus;
    }

    public ServiciosModel(String nombreServicio, String descripcion, String precio, String estatus) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        // Asume que tu clase tiene un campo llamado 'nombreServicio'
        return this.nombreServicio;
    }

    public ServiciosModel() {

    }

    public int getIdUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
