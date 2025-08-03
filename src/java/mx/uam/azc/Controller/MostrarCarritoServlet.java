package mx.uam.azc.Controller;

import mx.uam.azc.Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Victor
 */

@WebServlet(name = "MostrarCarritoServlet", urlPatterns = {"/MostrarCarrito"})
public class MostrarCarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Si no hay sesión, redirigir a login
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            CarritoDAO carritoDAO = new CarritoDAO(conn);

            // Obtener o crear el carrito del usuario
            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());

            // Listar items del carrito
            List<ItemCarrito> items = carritoDAO.listarItems(idCarrito);

            // Enviar lista al JSP
            request.setAttribute("carritoItems", items);

            // Redirigir al index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al mostrar carrito", e);
        }
    }
}
