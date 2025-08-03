/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */
public interface CarritoObservador {
    void onProductoAgregado(Producto producto, int cantidad);
    void onProductoEliminado(Producto producto, int cantidad);
    void onProductoActualizado(Producto producto, int cantidadAnterior, int cantidadNueva);
}
