/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAO {

    private final Connection conexion;

    public CarritoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Producto getProductoPorId(int idProducto, String tipoUsuario) throws SQLException {
    String sql = "SELECT id_producto, nombre, stock, precio_personal, precio_empresarial " +
                 "FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return ProductoFactory.crearProducto(rs, tipoUsuario);
            }
        }
        return null;
    }


    public void actualizarStock(int idProducto, int cambio) throws SQLException {
        String sql = "UPDATE producto SET stock = stock + ? WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cambio);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        }
    }

    // Futuro: Persistir carrito en BD si se desea
    public List<ItemCarrito> getCarritoFromDB(int idUsuario, String tipoUsuario) throws SQLException {
        List<ItemCarrito> items = new ArrayList<>();
        String sql = "SELECT c.id_producto, c.cantidad, p.nombre, p.stock, p.precio_personal, p.precio_empresarial " +
                     "FROM carrito_temp c JOIN producto p ON c.id_producto = p.id_producto " +
                     "WHERE c.id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
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

                ItemCarrito item = new ItemCarrito(p, rs.getInt("cantidad"));
                items.add(item);
            }
        }
        return items;
    }
}


