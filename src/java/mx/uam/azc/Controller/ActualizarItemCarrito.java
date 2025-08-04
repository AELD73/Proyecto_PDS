package mx.uam.azc.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.uam.azc.Modelo.*;

/**
 *
 * @author Victor
 */

@WebServlet(name = "ActualizarItemCarrito", urlPatterns = {"/ActualizarItemCarrito"})
public class ActualizarItemCarrito extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(ActualizarItemCarrito.class.getName());

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
            int nuevaCantidad = Integer.parseInt(request.getParameter("cantidad"));
            int idTalla = Integer.parseInt(request.getParameter("id_talla"));
            int idDisenio = Integer.parseInt(request.getParameter("id_disenio"));

            // Obtenemos el item del carrito para obtener los datos completos
            List<ItemCarrito> items = carritoDAO.listarItems(carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr()));
            ItemCarrito itemAActualizar = items.stream()
                .filter(i -> i.getIdItem() == idItem)
                .findFirst()
                .orElse(null);

            if (itemAActualizar != null) {
                itemAActualizar.setCantidad(nuevaCantidad);
                itemAActualizar.setSubtotal(nuevaCantidad * itemAActualizar.getPrenda().getCosto());
                
                Talla talla = new Talla();
                talla.setId(idTalla);
                itemAActualizar.setTalla(talla);
                
                Disenio disenio = new Disenio();
                disenio.setId(idDisenio);
                itemAActualizar.setDisenio(disenio);
                
                carritoDAO.actualizarItem(itemAActualizar);
            }

            response.sendRedirect("MostrarCarritoServlet");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar item del carrito", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el item del carrito.");
        }
    }
}