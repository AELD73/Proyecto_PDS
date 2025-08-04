<%-- 
    Document   : resumenPedido
    Created on : 4 ago 2025, 12:56:30 a.m.
    Author     : CASH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <title>Resumen del Pedido</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style_actualizado.css">

    </head>
    <body>
        <div class="container">
            <h1>Pedido Confirmado</h1>
            <p>¡Gracias por tu compra!</p>
            <p>Número de pedido: <c:out value="${idPedido}"/></p>
            <p>Total: $<c:out value="${pedido.total}"/></p>
            
            <h3>Detalles del Pedido</h3>
            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${pedido.detalles}">
                        <tr>
                            <td><c:out value="${item.prenda.tipo_prenda}"/> - <c:out value="${item.talla.nombre}"/></td>
                            <td><c:out value="${item.cantidad}"/></td>
                            <td>$<c:out value="${item.subtotal}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="MostrarPrendasServlet" method="get">
                <button type="submit">Volver a la página de inicio</button>
            </form>
        </div>
    </body>
</html>
