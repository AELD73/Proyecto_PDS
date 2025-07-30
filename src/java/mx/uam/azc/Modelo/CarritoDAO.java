/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */
import java.sql.*;

public class CarritoDAO {
    private Connection conexion;

    public CarritoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Producto getProductoPorId(int id) throws SQLException {
        String query = "SELECT nombre, precio_personal FROM prenda WHERE id_prenda = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(id, rs.getString("nombre"), rs.getDouble("precio_personal"));
            }
        }
        return null;
    }
}

