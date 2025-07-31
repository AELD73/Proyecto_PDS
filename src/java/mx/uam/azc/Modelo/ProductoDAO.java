/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.*;

public class ProductoDAO {

    private final Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Producto getProductoPorId(int idProducto, String tipoUsuario) {
        String query = "SELECT id_producto, nombre, precio_personal, precio_empresarial FROM producto WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));

                double precio;
                if ("empresarial".equalsIgnoreCase(tipoUsuario)) {
                    precio = rs.getDouble("precio_empresarial");
                } else {
                    precio = rs.getDouble("precio_personal");
                }

                producto.setPrecio(precio);
                return producto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

