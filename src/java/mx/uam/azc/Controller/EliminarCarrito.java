/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mx.uam.azc.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.uam.azc.Modelo.Carrito;

/**
 * Servlet para eliminar un producto del carrito de compras.
 */
@WebServlet(name = "EliminarDelCarrito", urlPatterns = {"/EliminarDelCarrito"})
public class EliminarCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito != null) {
            try {
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                carrito.eliminarProducto(idProducto);
                session.setAttribute("carrito", carrito);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "ID de producto inválido.");
            }
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Elimina un producto del carrito de compras";
    }
}
