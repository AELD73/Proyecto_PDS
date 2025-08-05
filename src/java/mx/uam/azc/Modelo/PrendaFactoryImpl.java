/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;
import mx.uam.azc.Modelo.*;

/**
 *
 * @author lopez
 */
public class PrendaFactoryImpl implements PrendaFactory{

    @Override
    public Prenda crearPrenda(String tipoPrenda, String color, String talla, String disenio, double baseCosto) {
        
        Prenda prendaBase;

        switch (tipoPrenda.toLowerCase()) {
            case "playera":
                prendaBase = new Playera(color, talla, baseCosto);
                break;
            case "sudadera":
                prendaBase = new Sudadera(color, talla, baseCosto);
                break;
            case "chamarra":
                prendaBase = new Chamarra(color, talla, baseCosto);
                break;
            default:
                throw new IllegalArgumentException("Tipo de prenda no válido: " + tipoPrenda);
        }

        // Aplica diseño (Decorador)
        switch (disenio.toLowerCase()) {
            case "intermedio":
                return new DisenioGrande(prendaBase);
            case "pro":
                return new DisenioPro(prendaBase);
            case "basico":
            default:
                return new DisenioPequenio(prendaBase);
        }

    }

    
    
}
    
