/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */

public interface Observer {
    void update(int idPrenda, int idTalla, int idColor, int cantidad);
}