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
			<c:out value="${sessionScope.sessionUser.apellido}" />
		</h4>
		<h4>
			<c:out value="${sessionUser.fechaUltConexion}" />
		</h4>
		<c:choose>
			<c:when test="${fn:length(listadoDePublicaciones) gt 0}">
				<h4>Cant. de Publicaciones:
					${fn:length(listadoDePublicaciones)}</h4>
			</c:when>
			<c:otherwise>
				<h4>No se encontraron nuevas Publicaciones</h4>
			</c:otherwise>
		</c:choose>
	</div>


	<div class="row">
		<div class="row col-md-12">
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#ListadoPublicaciones"
					data-toggle="tab"><h4>
							Listado Publicaciones&nbsp;<span class="badge">${fn:length(listadoDePublicaciones)}</span>
						</h4></a></li>
				<li><a href="#CuadriculaPublicaciones" data-toggle="tab"><h4>Cuadricula
							Publicaciones</h4></a></li>
			</ul>
		</div>
		<div class="row col-md-12">
			<div class="tab-content">

				<div class="tab-pane" id="CuadriculaPublicaciones">
					<br>
					<%@ include file="mostrarListadoDePublicaciones.jsp"%>
				</div>

				<div class="tab-pane active" id="ListadoPublicaciones">


					<div class="container">
						<h3>Lista de Publicaciones</h3>
						<table class="table table-hover table-responsive">
							<thead>
								<tr>
									<th>idPublicacion</th>
									<th>idUsuario</th>
									<th>idTipoAlojamiento</th>
									<th>descripcion</th>
									<th>idLocalidad</th>
									<th>codPostal</th>
									<th>coordenadas</th>
									<th>calle</th>
									<th>altura</th>
									<th>piso</th>
									<th>dpto</th>
									<th>supCubierta</th>
									<th>supDescubierta</th>
									<th>precioExpensas</th>
									<th>precioNoche</th>
									<th>chkPuedeVariarCantPersonas</th>
									<th>cantPersonas</th>
									<th>cantAmbientes</th>
									<th>cantBanios</th>
									<th>cantHabitaciones</th>
									<th>aniosAntiguedad</th>
									<th>fechaAlta</th>
									<th>fechaUltModificado</th>
									<th>puntaje</th>
									<th>verificado</th>
									<th>habilitado</th>
									<th scope="col">ACCIONES</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listadoDePublicaciones}"
									var="objPublicacion">
									<tr class="info">
										<td>${objPublicacion.idPublicacion}</td>
										<td>${objPublicacion.idUsuario}</td>
										<td>${objPublicacion.idTipoAlojamiento}</td>
										<td>${objPublicacion.descripcion}</td>
										<td>${objPublicacion.idLocalidad}</td>
										<td>${objPublicacion.codPostal}</td>
										<td>${objPublicacion.coordenadas}</td>
										<td>${objPublicacion.calle}</td>
										<td>${objPublicacion.altura}</td>
										<td>${objPublicacion.piso}</td>
										<td>${objPublicacion.dpto}</td>
										<td>${objPublicacion.supCubierta}</td>
										<td>${objPublicacion.supDescubierta}</td>
										<td>${objPublicacion.precioExpensas}</td>
										<td>${objPublicacion.precioNoche}</td>
										<td>${objPublicacion.chkPuedeVariarCantPersonas}</td>
										<td>${objPublicacion.cantPersonas}</td>
										<td>${objPublicacion.cantAmbientes}</td>
										<td>${objPublicacion.cantBanios}</td>
										<td>${objPublicacion.cantHabitaciones}</td>
										<td>${objPublicacion.aniosAntiguedad}</td>
										<td>${objPublicacion.fechaAlta}</td>
										<td>${objPublicacion.fechaUltModificado}</td>
										<td>${objPublicacion.puntaje}</td>
										<td><c:choose>
												<c:when test="${objPublicacion.verificado}">SI</c:when>
												<c:otherwise>NO</c:otherwise>
											</c:choose></td>
										<td>
										<td><c:choose>
												<c:when test="${objPublicacion.habilitado}">SI</c:when>
												<c:otherwise>NO</c:otherwise>
											</c:choose></td>
										<td>
											<div>
												<c:url value="PublicacionServlet?"
													var="urlPerfilPublicoUsuario">
													<c:param name="accionGET"
														value="verPerfilPublicoOtroUsuario" />
													<c:param name="idUsuario" value="${objUsuario.idUsuario}" />
												</c:url>

												<a href="${urlPerfilPublicoUsuario}" class="btn btn-default"
													data-toggle="tooltip" title="Ver Perfil"> <span
													class="glyphicon glyphicon-eye-open"></span>
												</a>
											</div>
											<div>
												<a href="#" class="btn btn-primary"> <span
													class="glyphicon glyphicon-edit"></span>
												</a>
											</div>

											<div>
												<a href="#" class="btn btn-danger"> <span
													class="glyphicon glyphicon-remove"></span>
												</a>
											</div>
										</td>
									</tr>
								</c:forEach>
								<c:forEach items="${listadoDePublicaciones}"
									var="objPublicacion">
									<tr>

									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>



				</div>
			</div>
		</div>
	</div>



</body>
</html>