<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Perfil público del Usuario</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>


	<div class="container">
		<h2>Perfil del Usuario</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>


	<div class="container">
		<div class="row well">
			<div class="col-md-3">
				<div id="fotoPerfil" class="form-group" align="center">
					<c:if test="${not empty objUsuario.rutaFotoPerfil}">
						<img class="img-rounded" alt=" " width="200" height="200"
							src="${objUsuario.rutaFotoPerfil}" />
					</c:if>

					<c:if test="${empty objUsuario.rutaFotoPerfil}">
						<!-- <img class="img-rounded" alt=" " width="60" height="60"
								src="<c:url value='${url.currentModule}/${rutaDefaultFoto}'/>" />
							 -->
						<img class="img-rounded" alt=" " width="200" height="200"
							src="${rutaDefaultFoto}" />

					</c:if>
				</div>
			</div>
			<div class="col-md-9">
				<div class="row col-md-12">
					<h2>${objUsuario.nombre}&nbsp;${objUsuario.apellido}

						<c:if test="${objUsuario.isVerificado() eq true}">
							<label class="control-label" style="color: ROYALBLUE;"
								data-toggle="tooltip"
								title="La cuenta del usuario fue verificada por un administrador">
								<span class="glyphicon glyphicon-ok-circle"></span>
							</label>
						</c:if>

					</h2>
				</div>
				<div class="row col-md-12"></div>
				<div class="row col-md-12">
					<div class="col-md-12 col-md-7">
						<h4>
							<label class="control-label"> <span
								class="glyphicon glyphicon-stats" /></span> Puntuación Gral.
								${objUsuario.puntaje}&nbsp;/5
							</label>
						</h4>
						<div class="form-group"></div>
						<div class="row col-md-12">
							<label class="control-label">Contacto</label>
						</div>
						<div class="row col-md-12">
							<label class="control-label">Correo Electrónico: <a>${objUsuario.mail}</a></label>
						</div>
						<div class="row col-md-12">
							<label class="control-label">Número de Teléfono:
								${objUsuario.nroTelefono}</label>
						</div>
					</div>
					<div class="col-md-12 col-md-5">
						<div class="row col-md-12">
							<label class="control-label">ID: <a>${objUsuario.idUsuario}</a></label>
						</div>
						<div class="row col-md-12">
							<label class="control-label">Última vez conectado:
								${objUsuario.fechaUltConexion}</label>
						</div>

						<div class="row col-md-12">
							<label class="control-label">Registrado desde:
								${objUsuario.fechaAlta}</label>
						</div>
					</div>
				</div>

				<div class="row col-md-12"></div>
			</div>
		</div>

		<div class="row"></div>

		<div class="row">
			<div class="row col-md-12">
				<ul class="nav nav-tabs nav-justified">
					<li class="active"><a href="#PublicacionesDelUsuario"
						data-toggle="tab"><h4>
								Publicaciones&nbsp;<span class="badge">${fn:length(listadoDePublicaciones)}</span>
							</h4></a></li>
					<!-- <li><a href="#ComentariosDelUsuario" data-toggle="tab"><h4>Comentarios
								del usuario</h4></a></li>  -->
					<li><a href="#OpinionesDeOtrosUsuarios" data-toggle="tab"><h4>Opiniones
								de otros Usuarios</h4></a></li>
				</ul>
			</div>
			<div class="row col-md-12">
				<div class="tab-content">
					<div class="tab-pane active" id="PublicacionesDelUsuario">
						<br>
						<%@ include file="mostrarListadoDePublicaciones.jsp"%>
					</div>
					<div class="tab-pane" id="ComentariosDelUsuario"></div>
					<div class="tab-pane" id="OpinionesDeOtrosUsuarios"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>