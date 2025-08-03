/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.*;
import java.util.List;

/**
 *
 * @author Victor
 */

public class PedidoDAO {
    private final Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }

    public int registrarPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (id_usuario, total, estado) VALUES (?,?, 'Pendiente')";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedido.getIdUsuario());
            ps.setDouble(2, pedido.getTotal());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public void registrarDetallePedido(int idPedido, List<ItemCarrito> items) throws SQLException {
        String sql = """
            INSERT INTO DetallePedido (id_pedido, id_prenda, id_disenio, cantidad, subtotal)
            VALUES (?,?,?,?,?)
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ItemCarrito item : items) {
                ps.setInt(1, idPedido);
                ps.setInt(2, item.getPrenda().getId());
                ps.setInt(3, item.getDisenio().getId());
                ps.setInt(4, item.getCantidad());
                ps.setDouble(5, item.getSubtotal());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public void limpiarCarrito(int idCarrito) throws SQLException {
        String sql = "DELETE FROM ItemCarrito WHERE id_carrito=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            ps.executeUpdate();
        }
    }
}


