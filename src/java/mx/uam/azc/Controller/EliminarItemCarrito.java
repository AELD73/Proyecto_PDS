package mx.uam.azc.Controller;

import mx.uam.azc.Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Victor
 */

@WebServlet(name = "EliminarItemCarrito", urlPatterns = {"/EliminarItemCarrito"})
public class EliminarItemCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idItem = Integer.parseInt(request.getParameter("idItem"));

        try (Connection conn = ConexionBD.getConexion()) {
            HttpSession session = request.getSession();
            String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                
            // 1. Se crea la instancia de PrendaDAO primero.
            PrendaDAO prendaDAO = new PrendaDAO(conn, tipoUsuario);
            CarritoDAO carritoDAO = new CarritoDAO(conn,prendaDAO);
            
            // Aquí se adjunta el observador
            carritoDAO.attach(new InventarioObserver());
            carritoDAO.eliminarItem(idItem);

            response.sendRedirect("MostrarPrendasServlet");

        } catch (SQLException e) {
            throw new ServletException("Error al eliminar item del carrito", e);
        }
    }
}