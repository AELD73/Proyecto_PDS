package mx.uam.azc.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.uam.azc.Modelo.*;
import java.util.List;

@WebServlet(name = "ConfirmarPedido", urlPatterns = {"/ConfirmarPedido"})
public class ConfirmarPedido extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (usuario == null || carrito == null || carrito.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            PedidoDAO pedidoDAO = new PedidoDAO(conn);

            int idPedido = pedidoDAO.registrarPedido(usuario, carrito);

            if (idPedido > 0) {
                pedidoDAO.registrarDetallePedido(idPedido, carrito.getItems());
                session.removeAttribute("carrito"); // Vaciar carrito tras confirmar
                response.sendRedirect("pedido_confirmado.jsp?idPedido=" + idPedido);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

