<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Listado</title>
</head>
<body>
	<!-- Al incluir el banner me parece q ya incluye lo del HEAD y los script del final -->
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<h4>
			<c:out value="${sessionUser.fechaUltConexion}" />
		</h4>
		<c:choose>
			<c:when test="${fn:length(listaUsuariosNuevos) gt 0}">
				<h4>Cant. de Usuarios nuevos: ${fn:length(listaUsuariosNuevos)}</h4>
			</c:when>
			<c:otherwise>
				<h4>No se encontraron nuevos usuario</h4>
			</c:otherwise>
		</c:choose>

		<h4>
			<c:out value="${sessionScope.sessionUser.apellido}" />
		</h4>
		<table class="table table-hover table-responsive">
			<c:forEach items="${listaUsuarios}" var="objUsuario">
				<tr>
					<td>${objUsuario.idUsuario}</td>
					<td><c:choose>
							<c:when
								test="${sessionUser.fechaUltConexion lt objUsuario.fechaAlta}">
								<h4>Mayor</h4>
							</c:when>
							<c:otherwise>
								<h4>menor</h4>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="container">
		<h3>Lista de Usuarios</h3>
		<table class="table table-hover table-responsive">
			<thead>
				<tr>
					<th>idUsuario</th>
					<th>nombre</th>
					<th>apellido</th>
					<th>dni</th>
					<th>mail</th>
					<th>Fecha Nacimiento</th>
					<th>usuario</th>
					<!--<th>clave</th>-->
					<th>sexo</th>
					<!--<th>rutaFotoPerfil</th>-->
					<th>admin</th>
					<th>puntaje</th>
					<th>habilitado</th>
					<th>Fecha Alta</th>
					<th>fechaUltConexion</th>
					<th>verificado</th>
					<!--Masculino, Femenino-->
					<th scope="col">ACCIONES</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaUsuariosNuevos}" var="objUsuario">
					<tr class="info">
						<td>${objUsuario.idUsuario}</td>
						<td>${objUsuario.nombre}</td>
						<td>${objUsuario.apellido}</td>
						<td>${objUsuario.dni}</td>
						<td>${objUsuario.mail}</td>
						<td>${objUsuario.fechaNac}</td>
						<td>${objUsuario.usuario}</td>
						<%--<td>${objUsuario.clave}</td> --%>
						<td><c:choose>
								<c:when test="${objUsuario.sexo}">
						Masculino
						</c:when>
								<c:otherwise>Femenino</c:otherwise>
							</c:choose></td>
						<!--<td>${objUsuario.rutaFotoPerfil}</td>-->
						<td>${objUsuario.admin}</td>
						<td>${objUsuario.puntaje}</td>
						<td><c:choose>
								<c:when test="${objUsuario.habilitado}">SI</c:when>
								<c:otherwise>NO</c:otherwise>
							</c:choose></td>
						<td>${objUsuario.fechaAlta}</td>
						<td>${objUsuario.fechaUltConexion}</td>
						<td><c:choose>
								<c:when test="${objUsuario.verificado}">SI</c:when>
								<c:otherwise>NO</c:otherwise>
							</c:choose></td>
						<td><a href="#" class="btn btn-primary"> <span
								class="glyphicon glyphicon-edit"></span>
						</a> <a href="#" class="btn btn-danger"> <span
								class="glyphicon glyphicon-remove"></span>
						</a></td>
					</tr>
				</c:forEach>
				<c:forEach items="${listaUsuarios}" var="objUsuario">
					<tr>
						<td>${objUsuario.idUsuario}</td>
						<td>${objUsuario.nombre}</td>
						<td>${objUsuario.apellido}</td>
						<td>${objUsuario.dni}</td>
						<td>${objUsuario.mail}</td>
						<td>${objUsuario.fechaNac}</td>
						<td>${objUsuario.usuario}</td>
						<%--<td>${objUsuario.clave}</td> --%>
						<td><c:choose>
								<c:when test="${objUsuario.sexo}">
						Masculino
						</c:when>
								<c:otherwise>Femenino</c:otherwise>
							</c:choose></td>
						<!--<td>${objUsuario.rutaFotoPerfil}</td>-->
						<td>${objUsuario.admin}</td>
						<td>${objUsuario.puntaje}</td>
						<td><c:choose>
								<c:when test="${objUsuario.habilitado}">SI</c:when>
								<c:otherwise>NO</c:otherwise>
							</c:choose></td>
						<td>${objUsuario.fechaAlta}</td>
						<td>${objUsuario.fechaUltConexion}</td>
						<td><c:choose>
								<c:when test="${objUsuario.verificado}">SI</c:when>
								<c:otherwise>NO</c:otherwise>
							</c:choose></td>
						<td><a href="#" class="btn btn-primary"> <span
								class="glyphicon glyphicon-edit"></span>
						</a> <a href="#" class="btn btn-danger"> <span
								class="glyphicon glyphicon-remove"></span>
						</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>



</body>
</html>