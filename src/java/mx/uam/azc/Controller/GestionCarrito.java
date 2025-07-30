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

        if (carrito == null) {
            carrito = new Carrito();
        }

        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            try (Connection conn = ConexionBD.getConexion()) {
                CarritoDAO dao = new CarritoDAO(conn);
                Producto producto = dao.getProductoPorId(idProducto);

                if (producto != null) {
                    carrito.agregarProducto(producto, cantidad);
                    session.setAttribute("carrito", carrito);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("carrito.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la gestión del carrito de compras";
    }
}
