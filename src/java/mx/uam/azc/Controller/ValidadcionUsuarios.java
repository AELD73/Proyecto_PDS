/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mx.uam.azc.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.uam.azc.Modelo.ConexionBD;
import java.sql.*;
import java.sql.Connection;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import mx.uam.azc.Modelo.Usuario;
import mx.uam.azc.Modelo.UsuarioDAO;

/**
 *
 * @author lopez
 */
@WebServlet(name = "ValidadcionUsuarios", urlPatterns = {"/ValidadcionUsuarios"})
public class ValidadcionUsuarios extends HttpServlet {

     

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * El método POST se utiliza para generar y/o mandar respuestas que utilizaremos más adelante
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        try (Connection conn = ConexionBD.getConexion()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Optional<Usuario> usuario = usuarioDAO.get(correo, contrasena);

            if (usuario.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario.get());
                String base = request.getContextPath();
                response.sendRedirect(base+"/index.jsp"); // página de bienvenida
            } else {
                request.setAttribute("error", "Correo o contraseña inválidos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Error al conectar a la base de datos", e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
