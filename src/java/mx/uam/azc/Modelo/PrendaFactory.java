/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public interface PrendaFactory {
    Prenda crearPrenda(String tipoPrenda, String color, String talla, String disenio, double baseCosto);
}
