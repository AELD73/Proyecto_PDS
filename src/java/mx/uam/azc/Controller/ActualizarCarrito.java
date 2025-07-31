/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mx.uam.azc.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.uam.azc.Modelo.*;

/**
 * Servlet que actualiza la cantidad de un producto en el carrito.
 * @author Victor
 */
@WebServlet(name = "ActualizarCarrito", urlPatterns = {"/ActualizarCarrito"})
public class ActualizarCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new Carrito();
        }

        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            if (cantidad <= 0) {
                carrito.eliminarProducto(idProducto);
            } else {
                carrito.actualizarCantidad(idProducto, cantidad);
            }

            session.setAttribute("carrito", carrito);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Datos inválidos al actualizar el carrito.");
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Actualiza la cantidad de productos en el carrito";
    }
}


