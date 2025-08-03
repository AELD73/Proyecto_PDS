/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */

import java.sql.Connection;
import java.sql.SQLException;

public class InventarioObservador implements CarritoObservador {

    private final CarritoDAO carritoDAO;

    public InventarioObservador(Connection conexion) {
        this.carritoDAO = new CarritoDAO(conexion);
    }

    @Override
    public void onProductoAgregado(Producto producto, int cantidad) {
        try {
            carritoDAO.actualizarStock(producto.getId(), -cantidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProductoEliminado(Producto producto, int cantidad) {
        try {
            carritoDAO.actualizarStock(producto.getId(), cantidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProductoActualizado(Producto producto, int cantidadAnterior, int cantidadNueva) {
        int diferencia = cantidadAnterior - cantidadNueva;
        try {
            carritoDAO.actualizarStock(producto.getId(), diferencia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


