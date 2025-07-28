/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

import java.sql.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopez
 */
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/carrito_compras?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // cambia esto
    private static final String PASSWORD = "root"; // cambia esto

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
