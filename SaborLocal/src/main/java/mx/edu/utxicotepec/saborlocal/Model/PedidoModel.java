package mx.edu.utxicotepec.saborlocal.Model;

public class PedidoModel {

    private int idPedido;
    private int idCliente;
    private String nombreCliente;   // 🔹 nuevo campo
    private String fechaPedido;
    private String estado;
    private double totalPedido;
    private String mensaje;
    private String fechaEntregaEstimada;
    private String pastelSeleccionado;
    private String tamanioSeleccionado;

    // 1. Constructor para crear un nuevo pedido (sin ID)
    public PedidoModel(int idCliente, String fechaPedido, String estado, double totalPedido, String mensaje, String fechaEntregaEstimada, String pastelSeleccionado, String tamanioSeleccionado) {
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.totalPedido = totalPedido;
        this.mensaje = mensaje;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.pastelSeleccionado = pastelSeleccionado;
        this.tamanioSeleccionado = tamanioSeleccionado;
    }

    // 2. Constructor para un pedido existente (con ID y nombre cliente)
    public PedidoModel(int idPedido, int idCliente, String nombreCliente, String fechaPedido, String estado, double totalPedido, String mensaje, String fechaEntregaEstimada, String pastelSeleccionado, String tamanioSeleccionado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.totalPedido = totalPedido;
        this.mensaje = mensaje;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.pastelSeleccionado = pastelSeleccionado;
        this.tamanioSeleccionado = tamanioSeleccionado;
    }

    // 3. Constructor simplificado para mostrar datos en tabla
    public PedidoModel(int idPedido, String pastelSeleccionado, String tamanioSeleccionado, String nombreCliente, double totalPedido, String mensaje, String fechaEntregaEstimada, String estado) {
        this.idPedido = idPedido;
        this.pastelSeleccionado = pastelSeleccionado;
        this.tamanioSeleccionado = tamanioSeleccionado;
        this.nombreCliente = nombreCliente;
        this.totalPedido = totalPedido;
        this.mensaje = mensaje;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.estado = estado;
    }

    // GETTERS Y SETTERS
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(String fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public String getPastelSeleccionado() {
        return pastelSeleccionado;
    }

    public void setPastelSeleccionado(String pastelSeleccionado) {
        this.pastelSeleccionado = pastelSeleccionado;
    }

    public String getTamanioSeleccionado() {
        return tamanioSeleccionado;
    }

    public void setTamanioSeleccionado(String tamanioSeleccionado) {
        this.tamanioSeleccionado = tamanioSeleccionado;
    }
}
