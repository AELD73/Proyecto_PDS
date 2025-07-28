/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopez
 */
public class UsuarioDAO implements DAO<Usuario>{
    private final Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }
    

    @Override
    public Optional<Usuario> get(String correo, String password) {
        String query = "SELECT * FROM Usuario WHERE correo = ? AND contraseña = ?";
        try (PreparedStatement smt = conexion.prepareStatement(query);){
            
            smt.setString(1, correo);
            smt.setString(2, password);
            ResultSet rs = smt.executeQuery();
            
            if(rs.next()){
                Usuario usr = new Usuario();
                usr.setIdUsr(rs.getInt("id_Usuario"));
                usr.setNombre(rs.getString("nombre"));
                usr.setCorreo(rs.getString("correo"));
                usr.setPassword(rs.getString("contraseña"));
                usr.setTipoUsr(rs.getString("tipo_usuario"));
                return Optional.of(usr);
                
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Usuario t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
