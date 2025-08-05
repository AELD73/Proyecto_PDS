/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public class DisenioPro extends PrendaDecorador {
    
    public DisenioPro(Prenda prenda) {
        super(prenda);
    }
    

    @Override
    public String getDescription() {
        return prenda.getDescription() + " + Diseño Pro";
    }

    @Override
    public double getPrecio() {
        return prenda.getPrecio() + 50.0;
    }
}
