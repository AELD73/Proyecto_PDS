package mx.uam.azc.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.uam.azc.Modelo.*;

@WebServlet(name = "ActualizarCarrito", urlPatterns = {"/ActualizarCarrito"})
public class ActualizarCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidadNueva = Integer.parseInt(request.getParameter("cantidad"));

        try (Connection conn = ConexionBD.getConexion()) {
            if (carrito == null) {
                carrito = new Carrito();
                carrito.addObserver(new InventarioObservador(conn));
                session.setAttribute("carrito", carrito);
            }

            if (cantidadNueva <= 0) {
                carrito.eliminarProducto(idProducto);
            } else {
                carrito.actualizarCantidad(idProducto, cantidadNueva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }
}



