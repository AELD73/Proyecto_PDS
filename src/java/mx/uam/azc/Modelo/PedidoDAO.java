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
import java.time.LocalDate;
import java.util.List;

public class PedidoDAO {

    private final Connection conexion;

    public PedidoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrarPedido(Usuario usuario, Carrito carrito) throws SQLException {
        boolean exito = false;
        try {
            conexion.setAutoCommit(false);

            // Insertar en tabla pedido
            String sqlPedido = "INSERT INTO pedido(id_usuario, fecha, total) VALUES (?, ?, ?)";
            int idPedido;

            try (PreparedStatement psPedido = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                psPedido.setInt(1, usuario.getIdUsr());
                psPedido.setDate(2, Date.valueOf(LocalDate.now()));
                psPedido.setDouble(3, carrito.getTotal());
                psPedido.executeUpdate();

                ResultSet rs = psPedido.getGeneratedKeys();
                if (rs.next()) {
                    idPedido = rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del pedido.");
                }
            }

            // Insertar en tabla detalle_pedido
            String sqlDetalle = "INSERT INTO detalle_pedido(id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
            try (PreparedStatement psDetalle = conexion.prepareStatement(sqlDetalle)) {
                for (ItemCarrito item : carrito.getItems()) {
                    psDetalle.setInt(1, idPedido);
                    psDetalle.setInt(2, item.getProducto().getId());
                    psDetalle.setInt(3, item.getCantidad());
                    psDetalle.setDouble(4, item.getSubtotal());
                    psDetalle.addBatch();
                }
                psDetalle.executeBatch();
            }

            conexion.commit();
            exito = true;

        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }

        return exito;
    }
}

