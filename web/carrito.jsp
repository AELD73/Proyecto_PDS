<%-- 
    Document   : carrito
    Created on : 29 jul 2025, 8:43:28 p.m.
    Author     : CASH
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, mx.uam.azc.Modelo.*" %>
<%
    HttpSession sesion = request.getSession();
    Carrito carrito = (Carrito) sesion.getAttribute("carrito");
    if (carrito == null) {
        carrito = new Carrito();
        sesion.setAttribute("carrito", carrito);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Carrito de Compras</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/skeleton.css">
        <link rel="stylesheet" href="css/custom.css">
    </head>
    <body>
        <div class="container">
            <h2>Tu Carrito</h2>

            <%
                if (carrito.getItems().isEmpty()) {
            %>
                <p>No has agregado productos al carrito.</p>
            <%
                } else {
            %>
            <table class="table">
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    for (ItemCarrito item : carrito.getItems()) {
                %>
                    <tr>
                        <td><%= item.getProducto().getNombre() %></td>
                        <td><%= item.getCantidad() %></td>
                        <td>$<%= item.getProducto().getPrecio() %></td>
                        <td>$<%= item.getSubtotal() %></td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <h3>Total: $<%= carrito.getTotal() %></h3>
            <%
                }
            %>

            <a href="index.jsp">Seguir comprando</a>
        </div>
    </body>
</html>

