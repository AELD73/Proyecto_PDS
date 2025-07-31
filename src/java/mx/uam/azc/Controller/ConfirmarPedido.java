/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mx.uam.azc.Controller;

import mx.uam.azc.Modelo.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

@WebServlet(name = "ConfirmarPedido", urlPatterns = {"/ConfirmarPedido"})
public class ConfirmarPedido extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (carrito == null || carrito.getItems().isEmpty() || usuario == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);

            String sqlPedido = "INSERT INTO pedido(id_usuario, fecha, total) VALUES (?, ?, ?)";
            try (PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                psPedido.setInt(1, usuario.getIdUsr());
                psPedido.setDate(2, Date.valueOf(LocalDate.now()));
                psPedido.setDouble(3, carrito.getTotal());
                psPedido.executeUpdate();

                ResultSet rs = psPedido.getGeneratedKeys();
                int idPedido = -1;
                if (rs.next()) {
                    idPedido = rs.getInt(1);
                }

                String sqlDetalle = "INSERT INTO detalle_pedido(id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
                try (PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle)) {
                    for (ItemCarrito item : carrito.getItems()) {
                        psDetalle.setInt(1, idPedido);
                        psDetalle.setInt(2, item.getProducto().getId());
                        psDetalle.setInt(3, item.getCantidad());
                        psDetalle.setDouble(4, item.getSubtotal());
                        psDetalle.addBatch();
                    }
                    psDetalle.executeBatch();
                }

                conn.commit();
                session.removeAttribute("carrito");
                response.sendRedirect("pedido_confirmado.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Confirma un pedido y lo guarda en la base de datos";
    }
}

