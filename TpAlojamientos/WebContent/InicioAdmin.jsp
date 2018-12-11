<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page import="modelo.Usuario, extra.Tag, modelo.Publicacion, java.util.ArrayList"%>
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
		if (request.getSession().getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if(usuario.isAdmin()){
				
				usuarios = (ArrayList<Usuario>) request.getAttribute("usuariosBajoPuntaje");
				publicaciones = (ArrayList<Publicacion>) request.getAttribute("publicacionesBajoPuntaje");
				//Siempre deberiamos mostrar primer las publicaciones.
				
			}else{
				//Redireccionar
			}
		}else{
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
					<th>puntaje</th>
					<th>precioNoche</th>
					<% 
					//imagenes
					//usuario: nombre, imagen de perfil, puntaje
					%>
					
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
						<td><c:out value="${obj.precioNoche}" /></td>
						<td><c:out value="${obj.puntaje}" /></td>
						<td><c:out value="${obj.habilitado}" /></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>
</html>