/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */

public class InventarioObserver implements Observer {
    
    @Override
    public void update(int idPrenda, int idTalla, int idColor, int cantidad) {
        try (Connection conn = ConexionBD.getConexion()) {
            PrendaDAO prendaDAO = new PrendaDAO(conn, null);
            prendaDAO.actualizarStock(idPrenda, idTalla, idColor, cantidad);
        } catch (SQLException ex) {
            Logger.getLogger(InventarioObserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




