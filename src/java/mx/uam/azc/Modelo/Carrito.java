/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void actualizarCantidad(int idProducto, int nuevaCantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == idProducto) {
                item.setCantidad(nuevaCantidad);
                return;
            }
        }
    }

    public void eliminarProducto(int idProducto) {
        Iterator<ItemCarrito> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getProducto().getId() == idProducto) {
                iterator.remove();
                return;
            }
        }
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0.0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}

