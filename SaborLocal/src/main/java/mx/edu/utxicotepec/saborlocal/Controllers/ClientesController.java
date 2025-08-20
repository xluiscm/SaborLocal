/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Controllers;

import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import mx.edu.utxicotepec.saborlocal.Model.ClientesModel;

/**
 *
 * @author xidon
 */
public class ClientesController {

    private static final Logger logger = Logger.getLogger(ClientesController.class.getName());

    public static boolean guardarCliente(ClientesModel cli) {
        String sql = "INSERT INTO Clientes(nombre, apellidoPaterno, apellidoMaterno, direccion, genero, fechaNacimiento, telefono, edad, idUsuario)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellidoPaterno());
            ps.setString(3, cli.getApellidoMaterno());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getGenero());
            ps.setString(6, cli.getFecha());
            ps.setString(7, cli.getTelefono());
            ps.setInt(8, cli.getEdad());
            ps.setInt(9, cli.getIdUsuario());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            logger.severe("Error al guardar cliente: " + ex.getMessage());
            return false;
        }
    }

    public static List<ClientesModel> mostrarTodos() {
        var lista = new ArrayList<ClientesModel>();
        String sql = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, direccion, genero, fechaNacimiento, telefono, edad, idUsuario FROM Clientes";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClientesModel client = new ClientesModel(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getString("direccion"),
                        rs.getString("genero"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getInt("idUsuario"));
                lista.add(client);
            }
        } catch (SQLException ex) {
            logger.severe("Error al mostrar clientes " + ex.getMessage());
        }
        return lista;
    }

    public static String obtenerNombreClientePorId(int idCliente) {
        String nombreCompleto = null;
        String sql = "SELECT nombre, apellidoPaterno, apellidoMaterno FROM Clientes WHERE idCliente = ?";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellidoPaterno = rs.getString("apellidoPaterno");
                    String apellidoMaterno = rs.getString("apellidoMaterno");
                    nombreCompleto = nombre + " " + apellidoPaterno + " " + apellidoMaterno;
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener nombre de cliente por ID: " + e.getMessage());
        }
        return nombreCompleto;
    }

    public static int obtenerIdClientePorNombreCompleto(String nombreCompleto) {
        String sql = "SELECT idCliente FROM Clientes WHERE REPLACE(CONCAT(nombre, ' ', apellidoPaterno, ' ', IFNULL(apellidoMaterno, '')), ' ', ' ') LIKE ?";
        int idCliente = -1;

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Se utiliza TRIM() para eliminar espacios al inicio y final
            // Se reemplazan los espacios multiples por uno solo
            String nombreNormalizado = nombreCompleto.trim().replaceAll("\\s+", " ");

            // Se utiliza LIKE con comodines '%' para buscar coincidencias parciales
            ps.setString(1, "%" + nombreNormalizado + "%");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idCliente = rs.getInt("idCliente");
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener ID del cliente por nombre completo: " + e.getMessage());
        }
        return idCliente;
    }

    public static List<ClientesModel> buscarClientesPorTermino(String termino) {
        List<ClientesModel> clientesEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM Clientes WHERE nombre LIKE ? OR apellidoPaterno LIKE ? OR apellidoMaterno LIKE ? OR direccion LIKE ? OR genero LIKE ? OR fechanacimiento LIKE ? OR telefono LIKE ? OR edad LIKE ? OR idUsuario LIKE ?";

        try (Connection conn = Conexion.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String cli = "%" + termino + "%";
            pstmt.setString(1, cli);
            pstmt.setString(2, cli);
            pstmt.setString(3, cli);
            pstmt.setString(4, cli);
            pstmt.setString(5, cli);
            pstmt.setString(6, cli);
            pstmt.setString(7, cli);
            pstmt.setString(8, cli);
            pstmt.setString(9, cli);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ClientesModel cliente = new ClientesModel(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("apellidoPaterno"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("direccion"),
                            rs.getString("genero"),
                            rs.getString("fechanacimiento"),
                            rs.getString("telefono"),
                            rs.getInt("edad"),
                            rs.getInt("idUsuario")
                    );
                    clientesEncontrados.add(cliente);
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al buscar clientes: " + e.getMessage());
        }
        return clientesEncontrados;
    }

    public static boolean modificarCliente(ClientesModel cliente) {
        String sql = "UPDATE clientes SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, direccion = ?, "
                + "genero = ?, fechaNacimiento = ?, telefono = ?, edad = ?, idUsuario = ? "
                + "WHERE idCliente = ?";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getGenero());
            ps.setString(6, cliente.getFecha());
            ps.setString(7, cliente.getTelefono());
            ps.setInt(8, cliente.getEdad());
            ps.setInt(9, cliente.getIdUsuario());
            ps.setInt(10, cliente.getIdCliente());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            logger.severe("Error al modificar cliente: " + ex.getMessage());
            return false;
        }
    }

    public static boolean insertarCliente(ClientesModel cliente) {
        String sql = "INSERT INTO Clientes (nombre, apellidoPaterno, apellidoMaterno, direccion, genero, fecha, telefono, edad, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellidoPaterno());
            pstmt.setString(3, cliente.getApellidoMaterno());
            pstmt.setString(4, cliente.getDireccion());
            pstmt.setString(5, cliente.getGenero());
            pstmt.setString(6, cliente.getFecha());
            pstmt.setString(7, cliente.getTelefono());
            pstmt.setInt(8, cliente.getEdad());
            pstmt.setInt(9, cliente.getIdUsuario());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.severe("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public static List<ClientesModel> obtenerTodosLosClientes() {
        List<ClientesModel> clientes = new ArrayList<>();
        // Esta función está vacía. Si no la usas, puedes eliminarla.
        // Si necesitas implementarla, puedes usar la lógica de 'mostrarTodos()'.
        return clientes;
    }

    public static boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM Clientes WHERE idCliente=?";

        try (Connection conn = Conexion.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.severe("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
