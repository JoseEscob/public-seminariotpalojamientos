<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Listado</title>
<%@ include file="Bootstrap.jsp"%>
</head>
<body>
	<!-- Al incluir el banner me parece q ya incluye lo del HEAD y los script del final -->
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<h3>Lista de Usuarios</h3>
		<table class="table">
			<thead>
				<tr>
					<th>idUsuario</th>
					<th>nombre</th>
					<th>apellido</th>
					<th>DNI</th>
					<th>mail</th>
					<th>fechaNac</th>
					<th>nroUsuario</th>
					<th>claveUsuario</th>
					<th>puntaje</th>
					<th>habilitado</th>
					<th>sexo</th>
					<!--Masculino, Femenino-->
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${lista}" var="obj">
					<tr>
						<td><c:out value="${obj.idUsuario}" />
						<td>
						<td><c:out value="${obj.nombre}" />
						<td>
						<td><c:out value="${obj.apellido}" />
						<td>
						<td><c:out value="${obj.dni}" />
						<td>
						<td><c:out value="${obj.mail}" />
						<td>
						<td><c:out value="${obj.fechaNac}" />
						<td>
						<td><c:out value="${obj.nroUsuario}" />
						<td>
						<td><c:out value="${obj.claveUsuario}" />
						<td>
						<td><c:out value="${obj.claveUsuario}" />
						<td>
						<td><c:out value="${obj.claveUsuario}" />
						<td>
						<td><c:out value="${obj.claveUsuario}" />
						<td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>



</body>
</html>