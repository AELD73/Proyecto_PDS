/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public abstract class PrendaDecorador extends Prenda{
    
    protected Prenda prenda;
    
     public PrendaDecorador(Prenda prenda) {
        this.prenda = prenda;
    }

    @Override
    public String getDescription() {
        return prenda.getDescription();
    }

    @Override
    public double getPrecio() {
        return prenda.getPrecio();
    }

    
}
