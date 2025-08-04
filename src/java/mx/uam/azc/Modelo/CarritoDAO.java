/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Victor
 */

public class CarritoDAO {
    private final Connection conn;

    public CarritoDAO(Connection conn) {
        this.conn = conn;
    }

    public int obtenerOCrearCarrito(int idUsuario) throws SQLException {
        String select = "SELECT id_carrito FROM Carrito WHERE id_usuario=?";
        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }

        String insert = "INSERT INTO Carrito(id_usuario) VALUES(?)";
        try (PreparedStatement ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public List<ItemCarrito> listarItems(int idCarrito) throws SQLException {
        List<ItemCarrito> items = new ArrayList<>();
        String sql = """
            SELECT ic.id_item, ic.cantidad, ic.subtotal,
                   tp.nombre_prenda, tp.tipo,
                   c.nombre_color,
                   d.id_disenio, d.nombre AS disenio,
                   t.id_talla, t.nombre_talla,
                   p.id_prenda, p.costo_personal, p.costo_empresarial
            FROM ItemCarrito ic
            JOIN Prenda p ON ic.id_prenda = p.id_prenda
            JOIN TipoPrenda tp ON p.id_tipo_prenda = tp.id_tipo_prenda
            JOIN Color c ON p.id_color = c.id_color
            JOIN Talla t ON ic.id_talla = t.id_talla
            LEFT JOIN Disenio d ON ic.id_disenio = d.id_disenio
            WHERE ic.id_carrito = ?;
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemCarrito item = new ItemCarrito();
                item.setIdItem(rs.getInt("id_item"));
                item.setIdCarrito(idCarrito);

                PrendaBase prenda = new PrendaBase();
                prenda.setId_prenda(rs.getInt("id_prenda"));
                prenda.setTipo_prenda(rs.getString("nombre_prenda") + " - " + rs.getString("tipo"));
                prenda.setColor_prenda(rs.getString("nombre_color"));
                // Por simplicidad, usamos costo personal (puedes ajustar según tipoUsr)
                prenda.setCosto(rs.getDouble("costo_personal"));
                item.setPrenda(prenda);

                item.setCantidad(rs.getInt("cantidad"));
                item.setSubtotal(rs.getDouble("subtotal"));

                Talla talla = new Talla();
                talla.setId(rs.getInt("id_talla"));
                talla.setNombre(rs.getString("nombre_talla"));
                item.setTalla(talla);

                Disenio disenio = new Disenio();
                disenio.setId(rs.getInt("id_disenio"));
                disenio.setNombre(rs.getString("disenio"));
                item.setDisenio(disenio);

                items.add(item);
            }
        }
        return items;
    }

    public void insertarItem(ItemCarrito item) throws SQLException {
        String sql = """
            INSERT INTO ItemCarrito (id_carrito,id_prenda,id_disenio,cantidad,id_talla,subtotal)
            VALUES (?,?,?,?,?,?)
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getIdCarrito());
            ps.setInt(2, item.getPrenda().getId_prenda());
            ps.setInt(3, item.getDisenio().getId());
            ps.setInt(4, item.getCantidad());
            ps.setInt(5, item.getTalla().getId());
            ps.setDouble(6, item.getSubtotal());
            ps.executeUpdate();
        }
        InventarioObserver.actualizarInventario(conn, item);
    }

    public void actualizarItem(ItemCarrito item) throws SQLException {
        String sql = "UPDATE ItemCarrito SET cantidad=?, id_talla=?, id_disenio=?, subtotal=? WHERE id_item=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getCantidad());
            ps.setInt(2, item.getTalla().getId());
            ps.setInt(3, item.getDisenio().getId());
            ps.setDouble(4, item.getSubtotal());
            ps.setInt(5, item.getIdItem());
            ps.executeUpdate();
        }
        InventarioObserver.actualizarInventario(conn, item);
    }

    public void eliminarItem(int idItem) throws SQLException {
        String sql = "DELETE FROM ItemCarrito WHERE id_item=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idItem);
            ps.executeUpdate();
        }
    }
}

