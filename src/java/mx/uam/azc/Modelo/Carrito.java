/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */
import java.util.*;

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

    public void eliminarProducto(int idProducto) {
        items.removeIf(item -> item.getProducto().getId() == idProducto);
    }

    public void actualizarCantidad(int idProducto, int nuevaCantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == idProducto) {
                item.setCantidad(nuevaCantidad);
                break;
            }
        }
    }

    public double getTotal() {
        return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }

    public List<ItemCarrito> getItems() {
        return items;
    }
}

