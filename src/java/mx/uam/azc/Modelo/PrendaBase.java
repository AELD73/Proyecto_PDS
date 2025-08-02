/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public class PrendaBase extends Prenda{

     public PrendaBase(int id, String tipoPrenda, String color, String talla, int cantidadDisponible, double costo) {
        this.id_prenda = id;
        this.tipo_prenda = tipoPrenda;
        this.color_prenda = color;
        this.talla_prenda = talla;
        this.costo = costo;
        

    }
     public PrendaBase(){
         
     }

    @Override
    public String getDescription() {
        return tipo_prenda + "- Color: " + color_prenda + ", Talla: " + talla_prenda;
    }

    @Override
    public double getPrecio() {
        return this.costo;
    }
    
}
