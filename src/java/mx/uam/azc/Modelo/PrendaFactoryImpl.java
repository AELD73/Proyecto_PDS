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
    public Prenda crearPrenda(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "camisa" -> new Playera();
            case "sudadera" -> new Sudadera();
            case "chamarra" -> new Chamarra();
            default -> throw new IllegalArgumentException("Tipo de prenda desconocido: " + tipo);
        };
    }

    
    
}
    
