/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CASH
 */
public interface DAO_Inventario {
    
    void actualizarInventario(List<ItemCarrito> items) throws SQLException;
}
