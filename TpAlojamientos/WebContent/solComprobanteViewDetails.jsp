<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprobante - Detalle</title>
</head>
<body>

	<%@ include file="Banner.jsp"%>


	<div class="container">
		<h2>Comprobante - Detalle</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container">
		<div class="col-12">
			<div class="row">
				<div class="col-md-6">
					<img src="imagenes/icon-home-logo.jpg" alt="logoEmpresa">
				</div>

				<div class="col-md-6 text-right">
					<div class="row">
						<label>ID Comprobante: ${objComprobante.idComprobante}</label>
					</div>
					<div class="row">
						<label class="text-muted">Fecha Alta:
							${objComprobante.fechaAlta}</label>
					</div>
				</div>
			</div>
			<hr />

			<div class="row">
				<div class="col-md-6">
					<label class="h4">Información del Huésped</label> <br>
					<c:url value="PublicacionServlet?" var="urlPerfilUsuarioHuesped">
						<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
						<c:param name="idUsuario" value="${objUsuarioHuesped.idUsuario}" />
					</c:url>

					<a href="${urlPerfilUsuarioHuesped}">${objUsuarioHuesped.nombre}&nbsp;${objUsuarioHuesped.apellido}</a>
					<p>DNI: ${objUsuarioHuesped.dni}</p>
					<p>
						Mail: <a>${objUsuarioHuesped.mail}</a>
					</p>
					<p>
						<c:choose>
							<c:when test="${empty objUsuarioHuesped.nroTelefono}">
							Teléfono: (No tiene)</c:when>
							<c:otherwise>Teléfono: ${objUsuarioHuesped.nroTelefono}</c:otherwise>
						</c:choose>
					</p>
				</div>
				<div class="col-md-6">
					<label class="h4">Información del Propietario</label> <br>
					<c:url value="PublicacionServlet?" var="urlPerfilUsuarioProp">
						<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
						<c:param name="idUsuario" value="${objUsuarioProp.idUsuario}" />
					</c:url>

					<a href="${urlPerfilUsuarioProp}">${objUsuarioProp.nombre}&nbsp;${objUsuarioProp.apellido}</a>
					<p>DNI: ${objUsuarioProp.dni}</p>
					<p>
						Mail: <a>${objUsuarioProp.mail}</a>
					</p>
					<p>
						<c:choose>
							<c:when test="${empty objUsuarioProp.nroTelefono}">
							Teléfono: (No tiene)</c:when>
							<c:otherwise>Teléfono: ${objUsuarioProp.nroTelefono}</c:otherwise>
						</c:choose>
					</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="h4">Información de la solicitud</div>
					<div>
						<ul class="list-inline">
							<li>ID Solicitud:</li>
							<li><b>${objComprobante.idSolicitud}</b></li>
						</ul>
					</div>
					<div class="row">
						<div class="col-xs-12 col-xs-6">
							<c:choose>
								<c:when test="${objComprobante.cantDiasReserva eq 1}">
									<p>
										<b>${objComprobante.cantDiasReserva} día</b>
									</p>
								</c:when>
								<c:otherwise>
									<p>
										<b>${objComprobante.cantDiasReserva} días</b>
									</p>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-xs-12 col-xs-6">
							<c:choose>
								<c:when test="${objComprobante.cantPersonas eq 1}">
									<p>
										<b>${objComprobante.cantPersonas} persona</b>
									</p>
								</c:when>
								<c:otherwise>
									<p>
										<b>${objComprobante.cantPersonas} personas</b>
									</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>


					<div>
						<label class="control-label">Desde: <fmt:formatDate
								value="${objComprobante.fechaReservaInicio}" type="date"
								pattern="dd/MM/yyyy" />
						</label>
					</div>
					<div>
						<label class="control-label">Hasta: <fmt:formatDate
								value="${objComprobante.fechaReservaFin}" type="date"
								pattern="dd/MM/yyyy" />
						</label>
					</div>

					<div class="row">
						<br>
					</div>

				</div>
			</div>

		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h3>${objPublicacion.nombre}</h3>
				<div class="h4" style="color: SLATEGRAY;">
					<ul class="list-inline">
						<li>Zona:</li>
						<li><b>${objPublicacion.objPublicacionInfo.publicacionPartidoLocalidad}</b></li>
					</ul>
				</div>
			</div>
			<div class="col-md-2">
				<c:url value="PublicacionServlet?" var="urlPublicacionVer">
					<c:param name="accionGET" value="VerPublicacion" />
					<c:param name="idPublicacion"
						value="${objComprobante.idPublicacion}" />
				</c:url>
				<a class="btn btn-primary" href="${urlPublicacionVer}">Ver
					Publicación</a>
			</div>
		</div>
	</div>
</body>
</html>