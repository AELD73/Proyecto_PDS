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

@WebServlet(name = "GestionCarrito", urlPatterns = {"/GestionCarrito"})
public class GestionCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idPrenda = Integer.parseInt(request.getParameter("idPrenda"));
        int idDisenio = Integer.parseInt(request.getParameter("idDisenio"));
        int idTalla = Integer.parseInt(request.getParameter("idTalla"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double subtotal = Double.parseDouble(request.getParameter("subtotal"));

        try (Connection conn = ConexionBD.getConexion()) {
            CarritoDAO carritoDAO = new CarritoDAO(conn);
            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());

            ItemCarrito item = new ItemCarrito();
            item.setIdCarrito(idCarrito);

            Producto prenda = new Producto();
            prenda.setId(idPrenda);
            item.setPrenda(prenda);

            Disenio disenio = new Disenio();
            disenio.setId(idDisenio);
            item.setDisenio(disenio);

            Talla talla = new Talla();
            talla.setId(idTalla);
            item.setTalla(talla);

            item.setCantidad(cantidad);
            item.setSubtotal(subtotal);

            carritoDAO.insertarItem(item);

            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error al agregar item al carrito", e);
        }
    }
}
