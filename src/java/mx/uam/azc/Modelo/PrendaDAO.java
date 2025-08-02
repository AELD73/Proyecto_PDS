/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.*;

/**
 *
 * @author lopez
 */
public class PrendaDAO implements DAO_Prenda<Prenda>{
    
    private final Connection conexion;
    private final String tipo_Usr;

    public PrendaDAO(Connection conexion, String tipo_Usr) {
        this.conexion = conexion;
        this.tipo_Usr = tipo_Usr;
    }

    
    
    @Override
    public Optional<Prenda> get(int id_usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Prenda> getAll() {
        List<Prenda> lista_prendas = new ArrayList<>();
        try {
            
            String sql = """
                                     SELECT p.id_prenda, tp.nombre_prenda, tp.tipo, c.nombre_color, t.nombre_talla,
                                            p.costo_personal, p.costo_empresarial
                                     FROM Prenda p
                                     JOIN TipoPrenda tp ON p.id_tipo_prenda = tp.id_tipo_prenda
                                     JOIN Color c ON p.id_color = c.id_color
                                     JOIN Talla t ON p.id_talla = t.id_talla
                                 """;
            
            PreparedStatement stm = conexion.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                PrendaBase prenda = new PrendaBase();
                prenda.setId_prenda(rs.getInt("id_prenda"));
                prenda.setTipo_prenda(rs.getString("nombre_prenda") + " - " + rs.getString("tipo"));
                prenda.setColor_prenda(rs.getString("nombre_color"));
                prenda.setTalla_prenda(rs.getString("nombre_talla"));

                double costo = tipo_Usr.equalsIgnoreCase("Empresarial")
                               ? rs.getDouble("costo_empresarial")
                               : rs.getDouble("costo_personal");

                prenda.setCosto(costo);
                lista_prendas.add(prenda);
            }
            
        } catch (SQLException ex) {
            System.getLogger(PrendaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lista_prendas;
    }

    @Override
    public void save(Prenda t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Prenda t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Prenda t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
