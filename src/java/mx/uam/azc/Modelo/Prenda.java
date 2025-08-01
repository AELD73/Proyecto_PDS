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
    protected  int id_tipo_prenda;
    protected  int id_color;
    protected  int id_talla;
    protected  String tipo_prenda;
    protected  String color_prenda;
    protected  String talla_prenda;
    protected  double costo_empresarial;
    protected  double costo_personal;

    
    
    
    public Prenda(){
        
    }

    public int getId_prenda() {
        return id_prenda;
    }

    public void setId_prenda(int id_prenda) {
        this.id_prenda = id_prenda;
    }

    public int getId_tipo_prenda() {
        return id_tipo_prenda;
    }

    public void setId_tipo_prenda(int id_tipo_prenda) {
        this.id_tipo_prenda = id_tipo_prenda;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public int getId_talla() {
        return id_talla;
    }

    public void setId_talla(int id_talla) {
        this.id_talla = id_talla;
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

    public double getCosto_empresarial() {
        return costo_empresarial;
    }

    public void setCosto_empresarial(double costo_empresarial) {
        this.costo_empresarial = costo_empresarial;
    }

    public double getCosto_personal() {
        return costo_personal;
    }

    public void setCosto_personal(double costo_personal) {
        this.costo_personal = costo_personal;
    }
    
    //Métodos adicionales para esta clase abstracta
    
    abstract public String getDescription();
    
    abstract public double getPrecio();
    
}
