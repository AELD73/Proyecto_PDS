/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */

public class ItemCarrito {
    private int idItem;
    private int idCarrito;
    private Producto prenda;
    private Disenio disenio;
    private Talla talla;
    private int cantidad;
    private double subtotal;

    public int getIdItem() { return idItem; }
    public void setIdItem(int idItem) { this.idItem = idItem; }

    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }

    public Producto getPrenda() { return prenda; }
    public void setPrenda(Producto prenda) { this.prenda = prenda; }

    public Disenio getDisenio() { return disenio; }
    public void setDisenio(Disenio disenio) { this.disenio = disenio; }

    public Talla getTalla() { return talla; }
    public void setTalla(Talla talla) { this.talla = talla; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
