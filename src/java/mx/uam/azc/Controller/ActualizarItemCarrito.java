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

@WebServlet(name = "ActualizarItemCarrito", urlPatterns = {"/ActualizarItemCarrito"})
public class ActualizarItemCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idItem = Integer.parseInt(request.getParameter("idItem"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int idTalla = Integer.parseInt(request.getParameter("idTalla"));
        int idDisenio = Integer.parseInt(request.getParameter("idDisenio"));
        double subtotal = Double.parseDouble(request.getParameter("subtotal"));

        try (Connection conn = ConexionBD.getConexion()) {
            CarritoDAO carritoDAO = new CarritoDAO(conn);

            ItemCarrito item = new ItemCarrito();
            item.setIdItem(idItem);

            Talla talla = new Talla();
            talla.setId(idTalla);
            item.setTalla(talla);

            Disenio disenio = new Disenio();
            disenio.setId(idDisenio);
            item.setDisenio(disenio);

            item.setCantidad(cantidad);
            item.setSubtotal(subtotal);

            carritoDAO.actualizarItem(item);

            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error al actualizar item del carrito", e);
        }
    }
}
