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
        double total = items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        pedido.setTotal(total);
        pedido.setDetalles(items);
        return pedido;
    }
}




