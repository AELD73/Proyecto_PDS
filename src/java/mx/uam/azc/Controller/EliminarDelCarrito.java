package mx.uam.azc.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.uam.azc.Modelo.*;

@WebServlet(name = "EliminarDelCarrito", urlPatterns = {"/EliminarDelCarrito"})
public class EliminarDelCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        try (Connection conn = ConexionBD.getConexion()) {
            if (carrito == null) {
                carrito = new Carrito();
                carrito.addObserver(new InventarioObservador(conn));
                session.setAttribute("carrito", carrito);
            }

            carrito.eliminarProducto(idProducto);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }
}

