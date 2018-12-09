<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="modelo.Usuario, extra.Tag, modelo.Publicacion, java.util.ArrayList, extra.Constantes"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Alojamientos</title>
</head>
<body>

	<%
		// Variables necesarias
		ArrayList<Usuario> usuarios = null;
		ArrayList<Publicacion> publicaciones = null;
		// control de sesion
		if (request.getSession().getAttribute(Constantes.sessionUser) != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute(Constantes.sessionUser);
			if (usuario.isAdmin()) {

				usuarios = (ArrayList<Usuario>) request.getAttribute("usuariosBajoPuntaje");
				publicaciones = (ArrayList<Publicacion>) request.getAttribute("publicacionesBajoPuntaje");
				//Siempre deberiamos mostrar primer las publicaciones.

			} else {
				//Redireccionar
			}
		} else {
			//Redireccionar
		}
	%>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h3>Lista de Usuarios</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Publicacion</th>
					<th>idUsuario</th>
					<th>idTipoAlojamiento</th>
					<th>nombre</th>
					<th>descripcion</th>
					<th>domicilio</th>
					<th>idLocalidad</th>
					<th>CodPostal</th>
					<th>coordenadas</th>
					<th>precioNoche</th>
					<th>metrosCuadrados</th>
					<th>cantPersonas</th>
					<th>cantAmbientes</th>
					<th>cantBaños</th>
					<th>cantHabitaciones</th>
					<th>jardin</th>
					<th>cochera</th>
					<th>mascotas</th>
					<th>fumadores</th>
					<th>amoblada</th>
					<th>desayuno</th>
					<th>fechaAlta</th>
					<th>puntaje</th>
					<th>habilitado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${publicaciones}" var="obj">
					<tr>
						<td><c:out value="${obj.idPublicacion}" /></td>
						<td><c:out value="${obj.idUsuario}" /></td>
						<td><c:out value="${obj.idTipoAlojamiento}" /></td>
						<td><c:out value="${obj.nombre}" /></td>
						<td><c:out value="${obj.descripcion}" /></td>
						<td><c:out value="${obj.domicilio}" /></td>
						<td><c:out value="${obj.idLocalidad}" /></td>
						<td><c:out value="${obj.codPostal}" /></td>
						<td><c:out value="${obj.coordenadas}" /></td>
						<td><c:out value="${obj.precioNoche}" /></td>
						<td><c:out value="${obj.metrosCuadrados}" /></td>
						<td><c:out value="${obj.cantPersonas}" /></td>
						<td><c:out value="${obj.cantAmbientes}" /></td>
						<td><c:out value="${obj.cantBanios}" /></td>
						<td><c:out value="${obj.cantHabitaciones}" /></td>
						<td><c:out value="${obj.bitJardin}" /></td>
						<td><c:out value="${obj.bitCochera}" /></td>
						<td><c:out value="${obj.bitMascotas}" /></td>
						<td><c:out value="${obj.bitFumadores}" /></td>
						<td><c:out value="${obj.bitAmoblada}" /></td>
						<td><c:out value="${obj.bitDesayuno}" /></td>
						<td><c:out value="${obj.fechaAlta}" /></td>
						<td><c:out value="${obj.puntaje}" /></td>
						<td><c:out value="${obj.habilitado}" /></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>
</html>