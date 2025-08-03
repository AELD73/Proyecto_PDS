package mx.uam.azc.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.uam.azc.Modelo.*;

@WebServlet(name = "GestionCarrito", urlPatterns = {"/GestionCarrito"})
public class GestionCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        try (Connection conn = ConexionBD.getConexion()) {
            CarritoDAO carritoDAO = new CarritoDAO(conn);

            if (carrito == null) {
                carrito = new Carrito();
                carrito.addObserver(new InventarioObservador(conn));
                session.setAttribute("carrito", carrito);
            }

            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String tipoUsuario = (usuario != null) ? usuario.getTipoUsr() : "personal";

            Producto producto = carritoDAO.getProductoPorId(idProducto, tipoUsuario);

            if (producto != null && producto.getStock() >= cantidad) {
                carrito.agregarProducto(producto, cantidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }
}

