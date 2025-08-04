/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Victor
 */


public class InventarioObserver {

    public static void actualizarInventario(Connection conn, ItemCarrito item) throws SQLException {
        // Podrías validar stock antes, si deseas
        String sqlUpdate = """
            UPDATE Inventario SET cantidad = cantidad - ?
            WHERE id_prenda=? AND id_talla=? 
              AND id_color=(SELECT id_color FROM Prenda WHERE id_prenda=?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
            ps.setInt(1, item.getCantidad());
            ps.setInt(2, item.getPrenda().getId_prenda());
            ps.setInt(3, item.getTalla().getId());
            ps.setInt(4, item.getPrenda().getId_prenda());
            ps.executeUpdate();
        }
    }
}




