/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.utxicotepec.saborlocal.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utxicotepec.saborlocal.Conexion.Conexion;

/**
 *
 * @author xidon
 */
public class InventarioModel {

    private int idIngrediente;
    private String ingrediente;
    private String tipoUso;
    private int cantidad;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public InventarioModel(int idIngrediente, String ingrediente, String tipoUso, int cantidad) {
        this.idIngrediente = idIngrediente;
        this.ingrediente = ingrediente;
        this.tipoUso = tipoUso;
        this.cantidad = cantidad;
    }

    public InventarioModel(String ingrediente, String tipoUso, int cantidad) {
        this.ingrediente = ingrediente;
        this.tipoUso = tipoUso;
        this.cantidad = cantidad;
    }

    // --- MÉTODOS AGREGADOS ---
    /**
     * Trae todos los ingredientes de la base de datos.
     *
     * @return Una lista de objetos InventarioModel.
     */
    public List<InventarioModel> getIngredientes() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<InventarioModel> lista = new ArrayList<>();

        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT idIngrediente, ingrediente, tipo_uso, cantidad FROM inventario";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new InventarioModel(
                        rs.getInt("idIngrediente"),
                        rs.getString("ingrediente"),
                        rs.getString("tipo_uso"),
                        rs.getInt("cantidad")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return lista;
    }

    /**
     * Obtiene la cantidad de un ingrediente por su nombre.
     *
     * @param nombreIngrediente El nombre del ingrediente.
     * @return La cantidad disponible como un int, o 0 si no se encuentra.
     */
    public int obtenerCantidadPorNombre(String nombreIngrediente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cantidad = 0; // Se inicializa como int
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT cantidad FROM inventario WHERE ingrediente = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreIngrediente);
            rs = ps.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt("cantidad"); // Se lee como int
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return cantidad;
    }

    public InventarioModel() {
        
    }
}
