/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public class Sudadera extends PrendaBase{

    private String descripcion;
    private double precio;

    public Sudadera(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    Sudadera(String color, String talla, double baseCosto) {
        this.tipo_prenda = "Sudadera";
        this.color_prenda = color;
        this.talla_prenda = talla;
        this.costo = costo;
    }

    Sudadera() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        return tipo_prenda + " " + color_prenda + " Talla " + talla_prenda;
    }

    @Override
    public double getPrecio() {
        return costo;
    }
    
}
