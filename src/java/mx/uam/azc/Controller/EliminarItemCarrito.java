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
            CarritoDAO carritoDAO = new CarritoDAO(conn);
            carritoDAO.eliminarItem(idItem);

            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error al eliminar item del carrito", e);
        }
    }
}
