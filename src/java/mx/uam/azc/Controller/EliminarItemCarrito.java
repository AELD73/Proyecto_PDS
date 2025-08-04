package mx.uam.azc.Controller;

import mx.uam.azc.Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.uam.azc.Modelo.Usuario;

/**
 *
 * @author Victor
 */

@WebServlet(name = "EliminarItemCarrito", urlPatterns = {"/EliminarItemCarrito"})
public class EliminarItemCarrito extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(EliminarItemCarrito.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            String tipoUsuario = (String) session.getAttribute("tipoUsuario");
            PrendaDAO prendaDAO = new PrendaDAO(conn, tipoUsuario);
            CarritoDAO carritoDAO = new CarritoDAO(conn);
            
            int idItem = Integer.parseInt(request.getParameter("id_item"));
            carritoDAO.eliminarItem(idItem);
            
            response.sendRedirect("MostrarCarritoServlet");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar item del carrito", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el item del carrito.");
        }
    }
}