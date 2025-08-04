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
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.List;
import mx.uam.azc.Modelo.*;

/**
 *
 * @author lopez
 */
@WebServlet(name = "MostrarPrendasServlet", urlPatterns = {"/MostrarPrendasServlet"})
public class MostrarPrendasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MostrarPrendasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MostrarPrendasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
          HttpSession session = request.getSession();
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Recuperar tipo de usuario
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipoUsuario = usuario.getTipoUsr();

        try (Connection conn = ConexionBD.getConexion()) {
            // DAO con lógica de precios por tipo de usuario
            PrendaDAO dao = new PrendaDAO(conn, tipoUsuario);
            List<Prenda> listaPrendas = dao.getAll();
            
            // Lógica para CARGAR EL CARRITO
            CarritoDAO carritoDAO = new CarritoDAO(conn);
            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());
            List<ItemCarrito> items = carritoDAO.listarItems(idCarrito);
            
            // Pasar datos a la vista
            request.setAttribute("listaPrendas", listaPrendas);
            request.setAttribute("carritoItems", items);
            session.setAttribute("tipoUsuario", tipoUsuario); // útil para JSTL

            // Redirigir a index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al recuperar las prendas del inventario", e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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

}
