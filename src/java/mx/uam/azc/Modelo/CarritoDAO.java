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
    private final List<Observer> observers = new ArrayList<>();
    private final PrendaDAO prendaDAO;
    
    public CarritoDAO(Connection conn, PrendaDAO prendaDAO) {
        this.conn = conn;
        this.prendaDAO = prendaDAO;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    private void notifyObservers(int idPrenda, int idTalla, int idColor, int cantidad) {
        for (Observer observer : observers) {
            observer.update(idPrenda, idTalla, idColor, cantidad);
        }
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
        String select = "SELECT id_item, cantidad FROM ItemCarrito WHERE id_carrito = ? AND id_prenda = ? AND id_talla = ? AND id_disenio = ?";
        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, item.getIdCarrito());
            ps.setInt(2, item.getPrenda().getId_prenda());
            ps.setInt(3, item.getTalla().getId());
            ps.setInt(4, item.getDisenio().getId());
            ResultSet rs = ps.executeQuery();
            int idPrenda = item.getPrenda().getId_prenda();
            int idTalla = item.getTalla().getId();
            int idColor = prendaDAO.obtenerIdColor(idPrenda);
            int cantidad = item.getCantidad();
            
            if (rs.next()) {
                int idItemExistente = rs.getInt("id_item");
                int cantidadExistente = rs.getInt("cantidad");
                int nuevaCantidad = cantidadExistente + item.getCantidad();
                double nuevoSubtotal = nuevaCantidad * item.getPrenda().getCosto();
                String update = "UPDATE ItemCarrito SET cantidad = ?, subtotal = ? WHERE id_item = ?";
                try (PreparedStatement updatePs = conn.prepareStatement(update)) {
                    updatePs.setInt(1, nuevaCantidad);
                    updatePs.setDouble(2, nuevoSubtotal);
                    updatePs.setInt(3, idItemExistente);
                    updatePs.executeUpdate();
                }
                this.notifyObservers(idPrenda, idTalla, idColor, cantidad);
            } else {
                String insert = "INSERT INTO ItemCarrito (id_carrito,id_prenda,id_disenio,cantidad,id_talla,subtotal) VALUES (?,?,?,?,?,?)";
                try (PreparedStatement insertPs = conn.prepareStatement(insert)) {
                    insertPs.setInt(1, item.getIdCarrito());
                    insertPs.setInt(2, item.getPrenda().getId_prenda());
                    insertPs.setInt(3, item.getDisenio().getId());
                    insertPs.setInt(4, item.getCantidad());
                    insertPs.setInt(5, item.getTalla().getId());
                    insertPs.setDouble(6, item.getSubtotal());
                    insertPs.executeUpdate();
                }
                this.notifyObservers(idPrenda, idTalla, idColor, cantidad);
            }
        }
    }

    public void eliminarItem(int idItem) throws SQLException {
        String select = "SELECT p.id_prenda, p.id_color, ic.cantidad, ic.id_talla FROM ItemCarrito ic JOIN Prenda p ON ic.id_prenda = p.id_prenda WHERE ic.id_item = ?";
        int idPrenda = 0;
        int cantidad = 0;
        int idTalla = 0;
        int idColor = 0;
        
        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, idItem);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idPrenda = rs.getInt("id_prenda");
                cantidad = rs.getInt("cantidad");
                idTalla = rs.getInt("id_talla");
                idColor = rs.getInt("id_color");
            }
        }
        
        String sql = "DELETE FROM ItemCarrito WHERE id_item = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idItem);
            ps.executeUpdate();
        }
        
        if (idPrenda != 0) {
            this.notifyObservers(idPrenda, idTalla, idColor, cantidad);
        }
    }

    public void actualizarItem(ItemCarrito item) throws SQLException {
        // Obtener la cantidad anterior y los IDs del item antes de la actualización
        String select = "SELECT p.id_prenda, p.id_color, ic.cantidad, ic.id_talla FROM ItemCarrito ic JOIN Prenda p ON ic.id_prenda = p.id_prenda WHERE ic.id_item = ?";
        int cantidadAnterior = 0;
        int idPrenda = 0;
        int idTallaAnterior = 0;
        int idColorAnterior = 0;
        
        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, item.getIdItem());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cantidadAnterior = rs.getInt("cantidad");
                idPrenda = rs.getInt("id_prenda");
                idTallaAnterior = rs.getInt("id_talla");
                idColorAnterior = rs.getInt("id_color");
            }
        }
        
        // Actualizar el item en la base de datos
        String sql = "UPDATE ItemCarrito SET cantidad=?, id_talla=?, id_disenio=?, subtotal=? WHERE id_item=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getCantidad());
            ps.setInt(2, item.getTalla().getId());
            ps.setInt(3, item.getDisenio().getId());
            ps.setDouble(4, item.getSubtotal());
            ps.setInt(5, item.getIdItem());
            ps.executeUpdate();
        }
        
        // Notificar al observador para actualizar el inventario
        // Primero, se regresa la cantidad anterior al stock
        if (idPrenda != 0) {
            this.notifyObservers(idPrenda, idTallaAnterior, idColorAnterior, cantidadAnterior);
        }
        
        // Segundo, se resta la nueva cantidad del stock
        if (idPrenda != 0) {
            this.notifyObservers(idPrenda, item.getTalla().getId(), idColorAnterior, -item.getCantidad());
        }
    }
}