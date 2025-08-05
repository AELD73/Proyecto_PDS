/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public class DisenioPequenio extends PrendaDecorador{

    public DisenioPequenio(Prenda prenda) {
        super(prenda);
    }
    
    
    
    @Override
    public String getDescription() {
        return prenda.getDescription() + " + Diseño Básico";
    }

    @Override
    public double getPrecio() {
        return prenda.getPrecio() + 20.0; // costo adicional
    }
}
