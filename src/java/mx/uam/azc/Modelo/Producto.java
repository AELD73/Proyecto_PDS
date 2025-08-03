/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */

public class Producto {
    private int id;
    private String tipoPrenda; // Camisa, Sudadera
    private String tipo;       // Oversize, Con gorro...
    private String color;
    private double costoPersonal;
    private double costoEmpresarial;

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public String getTipoPrenda() { 
        return tipoPrenda; 
    }
    public void setTipoPrenda(String tipoPrenda) { 
        this.tipoPrenda = tipoPrenda; 
    }
    public String getTipo() {
        return tipo; 
    }
    public void setTipo(String tipo) {
        this.tipo = tipo; 
    }
    public String getColor() {
        return color; 
    }
    public void setColor(String color) { 
        this.color = color; 
    }
    public double getCostoPersonal() {
        return costoPersonal; 
    }
    public void setCostoPersonal(double costoPersonal) {
        this.costoPersonal = costoPersonal; 
    }
    public double getCostoEmpresarial() { 
        return costoEmpresarial; 
    }
    public void setCostoEmpresarial(double costoEmpresarial) { 
        this.costoEmpresarial = costoEmpresarial; 
    }
}