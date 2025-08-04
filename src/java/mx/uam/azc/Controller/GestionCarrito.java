package mx.uam.azc.Controller;

import mx.uam.azc.Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor
 */

@WebServlet(name = "GestionCarrito", urlPatterns = {"/GestionCarrito"})
public class GestionCarrito extends HttpServlet {

    private static final Map<String, Integer> tallasMap = new HashMap<>();
    private static final Map<String, Integer> diseniosMap = new HashMap<>();
    
    static {
        tallasMap.put("XS", 1);
        tallasMap.put("S", 2);
        tallasMap.put("M", 3);
        tallasMap.put("L", 4);
        tallasMap.put("XL", 5);

        diseniosMap.put("Basico", 1); 
        diseniosMap.put("Intermedio", 2);
        diseniosMap.put("Pro", 3);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idPrenda = Integer.parseInt(request.getParameter("id_prenda"));
        String nombreTalla = request.getParameter("talla");
        String nombreDisenio = request.getParameter("disenio");
        
        int idTalla = tallasMap.getOrDefault(nombreTalla, 0);
        int idDisenio = diseniosMap.getOrDefault(nombreDisenio, 0);
        
        int cantidad = 1;
        double subtotal;

        try (Connection conn = ConexionBD.getConexion()) {

            String tipoUsuario = (String) session.getAttribute("tipoUsuario");

            PrendaDAO prendaDAO = new PrendaDAO(conn, tipoUsuario);
            CarritoDAO carritoDAO = new CarritoDAO(conn,prendaDAO);
            // Aquí se adjunta el observador
            carritoDAO.attach(new InventarioObserver());
            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());
            
            // Llama al método recién implementado
            PrendaBase prenda = prendaDAO.obtenerPorId(idPrenda);

            if (prenda == null) {
                throw new ServletException("No se encontró la prenda con ID: " + idPrenda);
            }

            subtotal = prenda.getCosto() * cantidad;

            ItemCarrito item = new ItemCarrito();
            item.setIdCarrito(idCarrito);
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

            response.sendRedirect("MostrarPrendasServlet");

        } catch (SQLException e) {
            throw new ServletException("Error al agregar item al carrito", e);
        }
    }
}