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

public class Pedido {
    private int idPedido;
    private int idUsuario;
    private double total;
    private List<ItemCarrito> detalles;

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<ItemCarrito> getDetalles() { return detalles; }
    public void setDetalles(List<ItemCarrito> detalles) { this.detalles = detalles; }
}
