/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public class Playera extends PrendaBase {

    private String descripcion;
    private double precio;

    public Playera(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    Playera(String color, String talla, double baseCosto) {
         this.tipo_prenda = "Camisa";
        this.color_prenda = color;
        this.talla_prenda = talla;
        this.costo = costo;
        
    }

    Playera() {
       
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
