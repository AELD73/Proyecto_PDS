/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public abstract class Prenda {
    protected  int id_prenda;
    protected  String tipo_prenda;
    protected  String color_prenda;
    protected  String talla_prenda;
    protected  double costo;

    public int getId_prenda() {
        return id_prenda;
    }

    public void setId_prenda(int id_prenda) {
        this.id_prenda = id_prenda;
    }
    public String getTipo_prenda() {
        return tipo_prenda;
    }

    public void setTipo_prenda(String tipo_prenda) {
        this.tipo_prenda = tipo_prenda;
    }

    public String getColor_prenda() {
        return color_prenda;
    }

    public void setColor_prenda(String color_prenda) {
        this.color_prenda = color_prenda;
    }

    public String getTalla_prenda() {
        return talla_prenda;
    }

    public void setTalla_prenda(String talla_prenda) {
        this.talla_prenda = talla_prenda;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    //Métodos adicionales para esta clase abstracta
    
    abstract public String getDescription();
    
    abstract public double getPrecio();
    
}
