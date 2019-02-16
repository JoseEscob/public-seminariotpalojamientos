<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="extra.ConstantesJSP"%>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=utf-8; charset=ISO-8859-1">
<script src="<%=ConstantesJSP.jspValidar_js%>"></script>

<!-- 1) css, 2)jquery, 3)bootstrap js -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="<%=ConstantesJSP.jspBs_jquery_js%>"></script>
<script src="<%=ConstantesJSP.jspBs_bootstrapMin_js%>"></script>
<link href="<%=ConstantesJSP.jspBs_bootstrapMin_css%>" rel="stylesheet">
<!--ESTO ES PARA QUE ANDEN LOS COMBOBOX CON EL CUATRO DE BUSQUEDA QUE LLEVAN INTEGRADO-->
<link rel="stylesheet" href="<%=ConstantesJSP.jspBs_selectCombo_css%>" />
<script src="<%=ConstantesJSP.jspBs_selectCombo_js%>"></script>
<title></title>
</head>
<body>
	<nav class="navbar navbar-inverse"> <!-- BEGIN: Menú Superior -->
	<div class="container-fluid">
		<!-- Image and text 
		<nav class="navbar navbar-light bg-light"> <a
			class="navbar-brand" href="Inicio.jsp"
			style="color: MEDIUMTURQUOISE;"> <img
			src="imagenes/icon-home.jpg" width="30" height="30"
			class="d-inline-block align-top" alt="Home"> OwnerRental
		</a> </nav>-->
		<div class="navbar-header">
			<c:choose>
				<c:when test="${sessionScope.sessionUser.admin}">
					<a class="navbar-brand" href="InicioAdministrador.jsp"
						title="Ir a la página de Inicio" style="color: MEDIUMTURQUOISE;">
						<span><img src="imagenes/icon-home.jpg" width="30"
							height="30" class="d-inline-block align-top" alt="Home"></span>&nbsp;OwnerRental
					</a>
				</c:when>
				<c:when test="${empty sessionScope.sessionUser}">
					<a class="navbar-brand" href="IniciarSesion.jsp"
						title="Ir a la página de Inicio" style="color: MEDIUMTURQUOISE;">
						<span><img src="imagenes/icon-home.jpg" width="30"
							height="30" class="d-inline-block align-top" alt="Home"></span>&nbsp;OwnerRental
					</a>
				</c:when>
				<c:otherwise>
					<a class="navbar-brand" href="Inicio.jsp"
						title="Ir a la página de Inicio" style="color: MEDIUMTURQUOISE;">
						<span><img src="imagenes/icon-home.jpg" width="30"
							height="30" class="d-inline-block align-top" alt="Home"></span>&nbsp;OwnerRental
					</a>
				</c:otherwise>

			</c:choose>
		</div>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown"><span
					class="glyphicon glyphicon-chevron-down"></span> Publicaciones</a>
				<ul class="dropdown-menu">
					<li><a href="PublicacionServlet?accionGET=VerPublicaciones"><span
							class="glyphicon glyphicon-check" /></span>&nbsp;Todas las
							publicaciones</a></li>
					<c:if test="${not empty sessionScope.sessionUser}">
						<c:if test="${not sessionScope.sessionUser.admin}">
							<li><a href="PublicacionServlet?accionGET=Nuevo"> <span
									class="glyphicon glyphicon-plus"></span>&nbsp;Nueva Publicación
							</a></li>
							<li><a
								href="PublicacionServlet?accionGET=verMisPublicaciones"><span
									class="glyphicon glyphicon glyphicon-th" /></span>&nbsp;Mis
									Publicaciones</a></li>
							<li><a
								href="PublicacionServlet?accionGET=verMisFavoritosPublicaciones"><span
									class="glyphicon glyphicon-star" /></span>&nbsp;Mis Publicaciones
									Favoritas </a></li>
							<!-- 
							<li><a href="#"></a></li>
							<li class="divider"></li>
							<li><a href="PublicacionServlet?accionGET=VerComentarios&idPublicacion=1">
								Test Comentario</a></li> 
							<li><a
								href="PublicacionServlet?accionGET=VerPublicacion&idPublicacion=1"><span
									class="glyphicon glyphicon-check" /></span> Ver publicacion</a></li>
							-->
						</c:if>
					</c:if>
				</ul></li>
			<c:if test="${not empty sessionScope.sessionUser}">
				<c:choose>
					<c:when test="${not sessionScope.sessionUser.admin}">

						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-chevron-down"></span> Solicitudes</a>
							<ul class="dropdown-menu">

								<li><c:url value="SolDeReservaServlet?"
										var="urlSolDeReserva">
										<c:param name="accionGET" value="verSolEnviadasRecibidas" />
									</c:url> <a href="${urlSolDeReserva}"> <span
										class="glyphicon glyphicon-send"></span>&nbsp;Solicitudes de
										Reserva
								</a></li>
								<li class="divider"></li>
								<li><c:url value="SolDeReservaServlet?"
										var="urlComprobanteDeReserva">
										<c:param name="accionGET"
											value="verListadoComprobanteDeReserva" />
									</c:url> <a
									href="SolDeReservaServlet?accionGET=verListadoComprobanteDeReserva">
										<span class="glyphicon glyphicon-briefcase"></span>&nbsp;Comprobantes
										de Reservas Aprobadas
								</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-chevron-down"></span> Admin</a>
							<ul class="dropdown-menu">
								<li><a href="UsuarioServlet?accionGET=admListaUsuarios"><span
										class="glyphicon glyphicon-check" /></span> Lista Usuarios</a></li>
								<li><a
									href="PublicacionServlet?accionGET=admListaPublicaciones"><span
										class="glyphicon glyphicon-check" /></span> Lista Publicaciones</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
		<!-- BEGIN: buscarPublicaciones -->
		<!--
		<form class="navbar-form navbar-left" action="/action_page.php">
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="Buscar alojamiento en..." name="txtBusquedaAlojamiento">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit"
						title="Te permite realizar una búsqueda por Zona">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
		
		glyphicon-bell
		glyphicon-comment
		-->
		<!-- END: buscarPublicaciones -->
		<div class="nav navbar-nav navbar-right">
			<ul class="nav navbar-nav">
				<c:choose>
					<c:when test="${empty sessionScope.sessionUser}">
						<li><a href="IniciarSesion.jsp"><span
								class="glyphicon glyphicon-log-in" /></span>&nbsp;Iniciar Sesión</a></li>
					</c:when>
					<c:otherwise>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
								${sessionScope.sessionUser.nombre}&nbsp;${sessionScope.sessionUser.apellido}
						</a>
							<ul class="dropdown-menu">
								<li><a
									href="PublicacionServlet?accionGET=verPerfilPublicoUsuarioLogueado"><span
										class="glyphicon glyphicon-globe" /></span> Ver mi Perfil Público</a></li>
								<li><a href="UsuarioServlet?accionGET=MiPerfil"><span
										class="glyphicon glyphicon-cog" /></span> Configurar mi Perfil</a></li>
								<li class="divider"></li>
								<li><a href="UsuarioServlet?accionGET=Logout"><span
										class="glyphicon glyphicon-off" /></span>&nbsp;Salir</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	</nav>
	<!-- END: Menú Superior -->

</body>
</html>