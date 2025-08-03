/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private final Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene un producto por ID y tipo de usuario (personal/empresarial)
     */
    public Producto getProductoPorId(int idProducto, String tipoUsuario) throws SQLException {
        String sql = "SELECT id_producto, nombre, stock, precio_personal, precio_empresarial " +
                     "FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setStock(rs.getInt("stock"));
                double precio = tipoUsuario.equalsIgnoreCase("empresarial") ?
                        rs.getDouble("precio_empresarial") :
                        rs.getDouble("precio_personal");
                p.setPrecio(precio);
                return p;
            }
        }
        return null;
    }

    /**
     * Actualiza el stock de un producto sumando o restando cantidad
     * Puede usarse por el observador directamente
     */
    public void actualizarStock(int idProducto, int cambio) throws SQLException {
        String sql = "UPDATE producto SET stock = stock + ? WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cambio);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos los productos para mostrar un catálogo dinámico
     */
    public List<Producto> getAllProductos(String tipoUsuario) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, stock, precio_personal, precio_empresarial FROM producto";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setStock(rs.getInt("stock"));
                double precio = tipoUsuario.equalsIgnoreCase("empresarial") ?
                        rs.getDouble("precio_empresarial") :
                        rs.getDouble("precio_personal");
                p.setPrecio(precio);
                productos.add(p);
            }
        }
        return productos;
    }
}

