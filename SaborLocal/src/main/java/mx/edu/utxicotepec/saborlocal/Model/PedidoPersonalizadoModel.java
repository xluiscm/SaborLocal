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
    private String estado;

    public PedidoPersonalizadoModel() {
    }

    public PedidoPersonalizadoModel(int idPedido, int idCliente, String ocasion, String tipoPan, String sabor, String cubierta, String forma, String tamanio, String decoracion, String ingredientes, String estado) {
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
        this.estado = estado;
    }

    // Getters y Setters
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
