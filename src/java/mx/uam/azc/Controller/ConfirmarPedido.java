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

@WebServlet(name = "ConfirmarPedido", urlPatterns = {"/ConfirmarPedido"})
public class ConfirmarPedido extends HttpServlet {

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
            PedidoDAO pedidoDAO = new PedidoDAO(conn);

            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());
            List<ItemCarrito> items = carritoDAO.listarItems(idCarrito);

            if (items.isEmpty()) {
                response.sendRedirect("index.jsp");
                return;
            }

            // Crear pedido con Factory
            Pedido pedido = PedidoFactory.crearPedido(usuario.getIdUsr(), items);

            // Guardar pedido y detalles
            int idPedido = pedidoDAO.registrarPedido(pedido);
            pedidoDAO.registrarDetallePedido(idPedido, items);

            // Limpiar carrito
            pedidoDAO.limpiarCarrito(idCarrito);

            // Guardar detalles en sesión para mostrar en resumen
            session.setAttribute("pedidoConfirmado", pedido);
            response.sendRedirect("resumenPedido.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error al confirmar pedido", e);
        }
    }
}
