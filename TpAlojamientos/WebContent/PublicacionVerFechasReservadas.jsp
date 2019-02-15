<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Fechas ya reservadas</h2>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>

	<div class="container">
		<c:choose>
			<c:when test="${empty listaFechasReservadasDesdeHoy}">
				<div class="alert alert-info">
					<h4>No se encontraron fechas de reserva para la publicación</h4>
				</div>
			</c:when>
			<c:otherwise>
				<h4>Cant. de fechas reservadas:
					${fn:length(listaFechasReservadasDesdeHoy)}</h4>
				<br>
				<table class="table table-hover table-responsive">
					<thead>
						<tr>
							<th>ID Publicacion</th>
							<th>Reservada Desde</th>
							<th>Reservada Hasta</th>
							<th>Cant. de Días</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaFechasReservadasDesdeHoy}"
							var="objPublicacionReserva">
							<tr>
								<td>${objPublicacionReserva.idPublicacion}</td>
								<td>${objPublicacionReserva.fechaReservaInicio}</td>
								<td>${objPublicacionReserva.fechaReservaFin}</td>
								<td>${objPublicacionReserva.cantDiasReserva}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>