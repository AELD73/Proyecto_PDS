<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Style360</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style_actualizado.css">
        
    </head>
    <body>
        <header id="header" class="header">
            <div class="container">
                <div class="row">
                    <div class="four columns">
                        <img src="img/logo_style360_horizontal.png" id="logo">
                    </div>
                    <div class="two columns u-pull-right">
                        <ul>
                            <li class="submenu">
                                <img src="img/cart.png" id="img-carrito">
                                <div id="carrito">

                                    <%@ page import="java.util.List" %>
                                    <%@ page import="mx.uam.azc.Modelo.Carrito" %>
                                    <%@ page import="mx.uam.azc.Modelo.ItemCarrito" %>
                                    <%@ page import="mx.uam.azc.Modelo.Producto" %>

                                    <%
                                        Carrito carrito = (Carrito) session.getAttribute("carrito");
                                        if (carrito != null && !carrito.getItems().isEmpty()) {
                                    %>

                                        <h2>🛒 Tu Carrito</h2>
                                        <table border="1">
                                            <tr>
                                                <th>Producto</th>
                                                <th>Precio</th>
                                                <th>Cantidad</th>
                                                <th>Subtotal</th>
                                                <th>Acciones</th>
                                            </tr>

                                            <% for (ItemCarrito item : carrito.getItems()) {
                                                Producto p = item.getProducto(); %>
                                            <tr>
                                                <td><%= p.getNombre() %></td>
                                                <td>$<%= p.getPrecio() %></td>
                                                <td>
                                                    <form action="ActualizarCarrito" method="post" style="display:inline;">
                                                        <input type="hidden" name="idProducto" value="<%= p.getId() %>"/>
                                                        <input type="number" name="cantidad" value="<%= item.getCantidad() %>" min="1" />
                                                        <input type="submit" value="Actualizar"/>
                                                    </form>
                                                </td>
                                                <td>$<%= item.getSubtotal() %></td>
                                                <td>
                                                    <form action="EliminarDelCarrito" method="post" style="display:inline;">
                                                    <input type="hidden" name="idProducto" value="<%= p.getId() %>"/>
                                                    <input type="submit" value="Eliminar"/>
                                                    </form>
                                                </td>
                                            </tr>
                                        <% } %>
                                        <tr>
                                            <td colspan="3" style="text-align:right;"><strong>Total:</strong></td>
                                            <td colspan="2">$<%= carrito.getTotal() %></td>
                                        </tr>
                                    </table>

                                    <form action="ConfirmarPedido" method="post">
                                        <input type="submit" class="buttons" value="Confirmar pedido"/>
                                    </form>

                                <%  } else { %>
                                    <p>🛍️ Tu carrito está vacío.</p>
                                <% } %>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div> 
            </div>
        </header>
        <div id="hero">
            <div class="container">
                <div class="row">
                    <div class="six columns">
                        <div class="contenido-hero">
                            <h2>Aprende algo nuevo</h2>
                            <p>Todos los cursos a $15</p>
                            <form action="#" id="busqueda" method="post" class="formulario">
                                <input class="u-full-width" type="text" placeholder="¿Que te gustaría Aprender?" id="buscador">
                                <input type="submit" id="submit-buscador" class="submit-buscador">
                            </form>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="barra">
            <div class="container">
                <div class="row">
                    <div class="four columns icono icono1">
                        <p>20,000 Cursos en línea <br>
                            Explora  los temas más recientes</p>
                    </div>
                    <div class="four columns icono icono2">
                        <p>Instructores Expertos <br>
                            Aprende con distintos estilos</p>
                    </div>
                    <div class="four columns icono icono3">
                        <p>Acceso de por vida <br>
                            Aprende a tu ritmo</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Aquí inician las cartas-->
        <div id="lista-cursos" class="container">
            <h1 id="encabezado" class="encabezado">Cursos En Línea</h1>
            <div class="row">
                <div class="four columns">
                    <div class="card">
                        <img src="img/curso1.jpg" class="imagen-curso u-full-width">
                        <div class="info-card">
                            <h4>HTML5, CSS3, JavaScript para Principiantes</h4>
                            <p>Juan Pedro</p>
                            <img src="img/estrellas.png">
                            <p class="precio">$200  <span class="u-pull-right ">$15</span></p>
                            <a href="#" class="u-full-width button-primary button input agregar-carrito" data-id="1">Agregar Al Carrito</a>
                        </div>
                    </div> <!--.card-->
                </div>
            </div> <!--.row-->
        </div>  
        
        <footer id="footer" class="footer">
            <div class="container">
                <div class="row">
                    <div class="four columns">
                        <nav id="principal" class="menu">
                            <a class="enlace" href="#">Para tu Negocio</a>
                            <a class="enlace" href="#">Conviertete en Instructor</a>
                            <a class="enlace" href="#">Aplicaciones Móviles</a>
                            <a class="enlace" href="#">Soporte</a>
                            <a class="enlace" href="#">Temas</a>
                        </nav>
                    </div>
                    <div class="four columns">
                        <nav id="secundaria" class="menu">
                            <a class="enlace" href="#">¿Quienes Somos?</a>
                            <a class="enlace" href="#">Empleo</a>
                            <a class="enlace" href="#">Blog</a>
                        </nav>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
