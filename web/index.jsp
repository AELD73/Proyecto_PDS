<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="mx.uam.azc.Modelo.Carrito" %>
<%@ page import="mx.uam.azc.Modelo.ItemCarrito" %>
<%@ page import="mx.uam.azc.Modelo.Producto" %>
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

                                    <c:choose>
                                        <c:when test="${not empty sessionScope.carrito.items}">
                                            <h2>🛒 Tu Carrito</h2>
                                            <table border="1">
                                                <tr>
                                                    <th>Producto</th>
                                                    <th>Precio</th>
                                                    <th>Cantidad</th>
                                                    <th>Subtotal</th>
                                                    <th>Acciones</th>
                                                </tr>

                                                <c:forEach var="item" items="${sessionScope.carrito.items}">
                                                    <tr>
                                                        <td>${item.producto.nombre}</td>
                                                        <td>$<fmt:formatNumber value="${item.producto.precio}" type="currency" currencySymbol=""/></td>
                                                        <td>
                                                            <form action="ActualizarCarrito" method="post" style="display:inline;">
                                                                <input type="hidden" name="idProducto" value="${item.producto.id}"/>
                                                                <input type="number" name="cantidad" value="${item.cantidad}" min="1"/>
                                                                <input type="submit" value="Actualizar"/>
                                                            </form>
                                                        </td>
                                                        <td>$<fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol=""/></td>
                                                        <td>
                                                            <form action="EliminarDelCarrito" method="post" style="display:inline;">
                                                                <input type="hidden" name="idProducto" value="${item.producto.id}"/>
                                                                <input type="submit" value="Eliminar"/>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                <tr>
                                                    <td colspan="3" style="text-align:right;"><strong>Total:</strong></td>
                                                    <td colspan="2">
                                                        $<fmt:formatNumber value="${sessionScope.carrito.total}" type="currency" currencySymbol=""/>
                                                    </td>
                                                </tr>
                                            </table>

                                            <form action="ConfirmarPedido" method="post">
                                                <input type="submit" class="buttons" value="Confirmar pedido"/>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <p>🛍️ Tu carrito está vacío.</p>
                                        </c:otherwise>
                                    </c:choose>

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
                            <h1 id="encabezado" class="encabezado">
                                Bienvenido ${sessionScope.usuario}, ¿En qué te podemos ayudar?
                            </h1>

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

            <div class="container">
                <div class="row prenda">
                    <c:forEach var="prenda" items="${listaPrendas}">
                        <div class="four columns">
                            <div class="card">
                                <img src="${prenda.imagen}" class="imagen-curso u-full-width" alt="Imagen de Prenda">
                                <div class="info-card">
                                    <h4>${prenda.tipoPrenda}</h4>
                                    <form action="AgregarCarritoServlet" method="post">
                                        <label for="talla_${prenda.idPrenda}">Talla:</label>
                                        <select name="talla" id="talla_${prenda.idPrenda}" class="u-full-width">
                                            <option value="XS">XS</option>
                                            <option value="S">S</option>
                                            <option value="M">M</option>
                                            <option value="L">L</option>
                                            <option value="XL">XL</option>
                                        </select>

                                        <label>Color:</label>
                                        <div class="color-buttons">
                                            <button type="button" class="color-btn" style="background-color: #000;" data-color="Negro"></button>
                                            <button type="button" class="color-btn" style="background-color: #FFF;" data-color="Blanco"></button>
                                            <button type="button" class="color-btn" style="background-color: #9c9c9c;" data-color="Gris"></button>
                                            <button type="button" class="color-btn" style="background-color: #FF0000;" data-color="Rojo"></button>
                                            <button type="button" class="color-btn" style="background-color: #3861ae;" data-color="Azul"></button>
                                            <button type="button" class="color-btn" style="background-color: #18a376;" data-color="Verde"></button>
                                        </div>

                                        <label for="disenio_${prenda.idPrenda}">Diseño:</label>
                                        <select name="disenio" id="disenio_${prenda.idPrenda}" class="u-full-width">
                                            <option value="Basico">Básico</option>
                                            <option value="Intermedio">Intermedio</option>
                                            <option value="Pro">Pro</option>
                                        </select>

                                        <p class="precio">
                                            <c:choose>
                                                <c:when test="${sessionScope.tipoUsuario == 'Empresarial'}">
                                                    Empresarial: $${prenda.costoEmpresarial}
                                                </c:when>
                                                <c:otherwise>
                                                    Personal: $${prenda.costoPersonal}
                                                </c:otherwise>
                                            </c:choose>
                                        </p>

                                        <input type="hidden" name="id_prenda" value="${prenda.idPrenda}">
                                        <input type="submit" class="u-full-width button-primary button input agregar-carrito" value="Agregar Al Carrito">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
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
