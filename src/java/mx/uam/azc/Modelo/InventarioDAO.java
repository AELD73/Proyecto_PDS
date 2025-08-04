package mx.uam.azc.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Victor
 */
public class InventarioDAO {
    private final Connection conn;
    private final PrendaDAO prendaDAO;

    public InventarioDAO(Connection conn, PrendaDAO prendaDAO) {
        this.conn = conn;
        this.prendaDAO = prendaDAO;
    }

    public void actualizarInventario(List<ItemCarrito> items) throws SQLException {
        String sql = "UPDATE Inventario SET cantidad = cantidad - ? WHERE id_prenda = ? AND id_talla = ? AND id_color = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ItemCarrito item : items) {
                int idPrenda = item.getPrenda().getId_prenda();
                int idColor = prendaDAO.obtenerIdColor(idPrenda); // Obtenemos el id_color usando PrendaDAO
                
                ps.setInt(1, item.getCantidad());
                ps.setInt(2, idPrenda);
                ps.setInt(3, item.getTalla().getId());
                ps.setInt(4, idColor);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}