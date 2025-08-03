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
import java.util.List;

public class PedidoDAO {

    private final Connection conexion;

    public PedidoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int registrarPedido(Usuario usuario, Carrito carrito) throws SQLException {
        String sql = "INSERT INTO pedido (id_usuario, fecha_pedido, total) VALUES (?, NOW(), ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, usuario.getIdUsr());
            ps.setDouble(2, carrito.getTotal());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    public void registrarDetallePedido(int idPedido, List<ItemCarrito> items) throws SQLException {
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario, subtotal) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (ItemCarrito item : items) {
                ps.setInt(1, idPedido);
                ps.setInt(2, item.getProducto().getId());
                ps.setInt(3, item.getCantidad());
                ps.setDouble(4, item.getProducto().getPrecio());
                ps.setDouble(5, item.getSubtotal());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}


