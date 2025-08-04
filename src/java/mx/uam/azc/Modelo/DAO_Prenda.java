/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author lopez
 */
public interface DAO_Prenda <T>{
    
    Optional<T> get(int id_usuario);
    
    public PrendaBase obtenerPorId(int id_prenda)throws SQLException;
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
    
    public int obtenerIdColor(int idPrenda) throws SQLException;
    
    public void actualizarStock(int idPrenda, int idTalla, int idColor, int cantidad) throws SQLException;
}

