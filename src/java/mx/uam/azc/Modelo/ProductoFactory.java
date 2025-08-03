/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Factory para crear productos dinámicamente según tipo de usuario.
 */
public class ProductoFactory {

    /**
     * Crea un Producto a partir de un ResultSet.
     *
     * @param rs ResultSet de la consulta SQL con columnas:
     *           id_producto, nombre, stock, precio_personal, precio_empresarial
     * @param tipoUsuario "personal" o "empresarial"
     * @return Producto creado con el precio correcto
     * @throws SQLException si falla la lectura del ResultSet
     */
    public static Producto crearProducto(ResultSet rs, String tipoUsuario) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setStock(rs.getInt("stock"));

        double precio;
        if ("empresarial".equalsIgnoreCase(tipoUsuario)) {
            precio = rs.getDouble("precio_empresarial");
        } else {
            precio = rs.getDouble("precio_personal");
        }
        producto.setPrecio(precio);

        return producto;
    }
}

