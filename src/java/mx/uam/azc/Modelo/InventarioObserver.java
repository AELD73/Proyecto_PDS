/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventarioObserver {

    public static void actualizarInventario(Connection conn, ItemCarrito item) throws SQLException {
        // Verificar stock disponible
        String sqlStock = """
            SELECT cantidad FROM Inventario
            WHERE id_prenda=? AND id_talla=? AND id_color=(SELECT id_color FROM Prenda WHERE id_prenda=?)
        """;
        // Si no hay suficiente, deberías lanzar mensaje al JSP (via atributo request)
        // Si hay suficiente, actualizar restando cantidad reservada
        String sqlUpdate = """
            UPDATE Inventario SET cantidad = cantidad - ?
            WHERE id_prenda=? AND id_talla=? AND id_color=(SELECT id_color FROM Prenda WHERE id_prenda=?)
        """;
        try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
            ps.setInt(1, item.getCantidad());
            ps.setInt(2, item.getPrenda().getId());
            ps.setInt(3, item.getTalla().getId());
            ps.setInt(4, item.getPrenda().getId());
            ps.executeUpdate();
        }
    }
}



