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

        // Parámetros del formulario
        int idPrenda = Integer.parseInt(request.getParameter("id_prenda"));
        String nombreTalla = request.getParameter("talla");
        String nombreDisenio = request.getParameter("disenio");

        int idTalla = tallasMap.getOrDefault(nombreTalla, 0);
        int idDisenio = diseniosMap.getOrDefault(nombreDisenio, 0);
        int cantidad = 1;

        try (Connection conn = ConexionBD.getConexion()) {

            // Obtener tipo de usuario
            String tipoUsuario = usuario.getTipoUsr();

            // Instanciar DAO con Fábrica de prendas
            PrendaFactory factory = new PrendaFactoryImpl();
            PrendaDAO prendaDAO = new PrendaDAO(conn, tipoUsuario, factory);

            // Instanciar DAO de carrito
            CarritoDAO carritoDAO = new CarritoDAO(conn, prendaDAO);
            carritoDAO.attach(new InventarioObserver());

            // Obtener carrito del usuario
            int idCarrito = carritoDAO.obtenerOCrearCarrito(usuario.getIdUsr());

            // Obtener prenda desde DAO con Fábrica
            Prenda prenda = prendaDAO.obtenerPorId(idPrenda);
            if (prenda == null) {
                throw new ServletException("Prenda no encontrada con ID: " + idPrenda);
            }

            // Calcular subtotal
            double subtotal = prenda.getPrecio() * cantidad;

            // Crear item de carrito
            ItemCarrito item = new ItemCarrito();
            item.setIdCarrito(idCarrito);
            item.setPrenda((PrendaBase) (Prenda) prenda);

            Disenio disenio = new Disenio();
            disenio.setId(idDisenio);
            disenio.setNombre(nombreDisenio);
            item.setDisenio(disenio);

            Talla talla = new Talla();
            talla.setId(idTalla);
            talla.setNombre(nombreTalla);
            item.setTalla(talla);

            item.setCantidad(cantidad);
            item.setSubtotal(subtotal);

            // Insertar en BD
            carritoDAO.insertarItem(item);

            // Redirigir
            response.sendRedirect("MostrarPrendasServlet");

        } catch (SQLException e) {
            throw new ServletException("Error al agregar item al carrito", e);
        }
    }
}