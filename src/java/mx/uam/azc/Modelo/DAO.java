/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author lopez
 */
public interface DAO <T>{
    Optional<T> get(String correo, String password);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}
