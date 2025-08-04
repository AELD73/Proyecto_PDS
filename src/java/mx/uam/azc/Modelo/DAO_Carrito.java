/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CASH
 */
public interface  DAO_Carrito {

     void attach(Observer observer);
    
     void notifyObservers(int idPrenda, int idTalla, int idColor, int cantidad);
    
     int obtenerOCrearCarrito(int idUsuario) throws SQLException;

     List<ItemCarrito> listarItems(int idCarrito) throws SQLException;

     void insertarItem(ItemCarrito item) throws SQLException;

     void eliminarItem(int idItem) throws SQLException;

     void actualizarItem(ItemCarrito item) throws SQLException;
        
}
