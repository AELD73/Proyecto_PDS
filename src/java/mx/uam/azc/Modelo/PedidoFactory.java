/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */

import java.util.List;

public class PedidoFactory {

    public static Pedido crearPedido(int idUsuario, List<ItemCarrito> items) {
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(idUsuario);
        
        double total = 0.0;
        
        // Se recalcula el subtotal de cada ítem y se suma al total del pedido
        for (ItemCarrito item : items) {
            double precioUnitario = item.getPrenda().getCosto();
            double subtotalCalculado = precioUnitario * item.getCantidad();
            
            // Asignamos el nuevo subtotal calculado al ítem para que se muestre correctamente en el JSP
            item.setSubtotal(subtotalCalculado);
            total += subtotalCalculado;
        }
        
        pedido.setTotal(total);
        pedido.setDetalles(items);
        
        return pedido;
    }
}




