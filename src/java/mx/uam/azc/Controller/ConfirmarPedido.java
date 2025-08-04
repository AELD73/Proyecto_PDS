package mx.uam.azc.Controller;

import mx.uam.azc.Modelo.*;
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

/**
 *
 * @author Victor
 */
@WebServlet(name = "ConfirmarPedido", urlPatterns = {"/ConfirmarPedido"})
public class ConfirmarPedido extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConfirmarPedido.class.getName());
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
            CarritoDAO carritoDAO = new CarritoDAO(conn);
            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());
            List<ItemCarrito> items = carritoDAO.listarItems(idCarrito);

            if (items.isEmpty()) {
                response.sendRedirect("MostrarPrendasServlet");
                return;
            }

            // 1. Usar el Factory para crear el objeto Pedido
            Pedido pedido = PedidoFactory.crearPedido(usuario.getIdUsr(), items);
            
            PedidoDAO pedidoDAO = new PedidoDAO(conn);

            // 2. Registrar el pedido y obtener el ID
            int idPedido = pedidoDAO.registrarPedido(pedido);

            if (idPedido != -1) {
                // 3. Registrar los detalles del pedido
                pedidoDAO.registrarDetallePedido(idPedido, items);

                // 4. Descontar del inventario y limpiar el carrito
                PrendaDAO prendaDAO = new PrendaDAO(conn, null);
                for (ItemCarrito item : items) {
                    int idColor = prendaDAO.obtenerIdColor(item.getPrenda().getId_prenda());
                    prendaDAO.actualizarStock(item.getPrenda().getId_prenda(), item.getTalla().getId(), idColor, -item.getCantidad());
                }

                pedidoDAO.limpiarCarrito(idCarrito);
                
                // 5. Establecer atributos para el resumen y redirigir
                request.setAttribute("pedido", pedido);
                request.setAttribute("idPedido", idPedido);
                
                request.getRequestDispatcher("resumenPedido.jsp").forward(request, response);
                
            } else {
                throw new ServletException("Error al registrar el pedido.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error en el proceso de pedido", e);
            throw new ServletException("Error en el proceso de pedido: " + e.getMessage(), e);
        }
    }
}
