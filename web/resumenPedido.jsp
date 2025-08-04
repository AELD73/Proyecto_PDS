<%-- 
    Document   : resumenPedido
    Created on : 4 ago 2025, 12:56:30 a.m.
    Author     : CASH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Resumen del Pedido</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style_actualizado.css">
        <style>
            .resumen-container {
                text-align: center;
                margin-top: 2rem;
            }
            .resumen-container h1, .resumen-container h3, .resumen-container p {
                font-size: 1.5rem; /* Aumenta el tamaño de la fuente para el texto principal */
            }
            .resumen-container table {
                margin: 0 auto;
                width: 80%;
                border-collapse: collapse;
                font-size: 1.2rem;
            }
            .resumen-container table, .resumen-container th, .resumen-container td {
                border: 1px solid #ddd;
                padding: 10px;
            }
            .resumen-container th {
                background-color: #f2f2f2;
            }
            .resumen-container button {
                background-color: #3861ae; /* Color azul */
                color: white;
                border: none;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin-top: 20px;
                cursor: pointer;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <header id="header" class="header">
            <div class="container">
                <div class="row">
                    <div class="four columns">
                        <img src="img/logo_style360_horizontal.png" id="logo">
                    </div>
                </div>
            </div>
        </header>
        <div id="hero">
            <div class="container">
                <div class="row">
                    <div class="six columns">
                        <div class="contenido-hero">
                            <h1 id="encabezado" class="encabezado">
                                ¡Pedido Confirmado!
                            </h1>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="container resumen-container">
            
            <p>¡Gracias por tu compra!</p>
            <p>Número de pedido: <c:out value="${pedidoConfirmado.idPedido}"/></p>
            <p>Total: $<c:out value="${pedidoConfirmado.total}"/></p>
            
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
                    <c:forEach var="item" items="${pedidoConfirmado.detalles}">
                        <tr>
                            <td>
                                <c:out value="${item.prenda.tipo_prenda}"/> - <c:out value="${item.prenda.color_prenda}"/> - <c:out value="${item.talla.nombre}"/>
                            </td>
                            <td><c:out value="${item.cantidad}"/></td>
                            <td>$<c:out value="${item.prenda.costo}"/></td>
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