<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Perfil del Usuario</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Perfil del Usuario</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h3>Datos Personales</h3>
				<form class="form-horizontal" action="UsuarioServlet" method="post">
					<div class="form-group ">
						<div class="col-xs-8">
							<label class="control-label col-xs-7">Fecha última
								Conexión: </label>
							<div class="col-xs-5">
								<label class="control-label"> <c:out
										value="${objUsuario.anteriorFechaUltConexion}"></c:out>
								</label>
							</div>
						</div>
						<c:if test="${objUsuario.isVerificado() eq true}">
							<div class="col-xs-4">
								<label class="control-label pull-right"
									style="color: ROYALBLUE;" data-toggle="tooltip"
									title="Tu cuenta fue verificada por un administrador">
									<span class="glyphicon glyphicon-ok-circle"></span> Usuario
									Verificado
								</label>
							</div>
						</c:if>

					</div>


					<div class="form-group">
						<div class="pull-left col-xs-9">
							<label class="control-label col-xs-5">Usuario desde: </label>
							<div class="col-xs-7">
								<label class="control-label"> <c:out
										value="${objUsuario.fechaAlta}"></c:out>
								</label>
							</div>
						</div>


						<div class="pull-right col-xs-3">
							<label class="control-label"> <span
								class="glyphicon glyphicon-stats"></span> Puntaje:
								${objUsuario.puntaje}
							</label>
						</div>

					</div>


					<div class="form-group">
						<label class="control-label col-xs-3">Nombre: </label>
						<div class="col-xs-9">
							<input type="text" name="nombre" class="form-control"
								value="${objUsuario.nombre}"
								onkeypress="return soloLetras(event)" maxlength="50" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-3">Apellido: </label>
						<div class="col-xs-9">
							<input type="text" name="apellido" class="form-control"
								onkeypress="return soloLetras(event)"
								value="${objUsuario.apellido}" maxlength="50" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">DNI: </label>
						<div class="col-sm-9">
							<input type="text" name="dni" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								value="${objUsuario.dni}" maxlength="10" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-3">Fecha de Nacimiento:
						</label>
						<div class="col-xs-9">
							<input type="date" name="fechaNac" class="form-control"
								value="${objUsuario.fechaNac}" pattern="^\d{2}-\d{2}-\d{4}$"
								maxlength="10" placeholder="dd-MM-yyyy" required>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">Mail: </label>
						<div class="col-sm-9">
							<input type="email" name="mail" class="form-control"
								onpaste="return false" value="${objUsuario.mail}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Nro Teléfono: </label>
						<div class="col-sm-9">
							<input type="text" name="nroTelefono" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								value="${objUsuario.nroTelefono}" maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label for="mail" class="control-label col-sm-3">Sexo: </label>
						<div class="col-sm-9">
							<c:choose>
								<c:when test="${objUsuario.sexo}">
									<label class="radio-inline"><input type="radio"
										name="rdbSexo" value="true" checked>Masculino</label>
									<label class="radio-inline"><input type="radio"
										name="rdbSexo" value="false">Femenino</label>
								</c:when>
								<c:otherwise>
									<label class="radio-inline"><input type="radio"
										name="rdbSexo" value="true">Masculino</label>
									<label class="radio-inline"><input type="radio"
										name="rdbSexo" value="false" checked>Femenino</label>
								</c:otherwise>
							</c:choose>

						</div>
					</div>

					<div class="form-group">
						<div class="col-md-12" align="center">
							<div>
								<input type="hidden" name="accionPOST"
									value="modificarDatosUsuario">
							</div>
							<div>
								<input type="submit" class="btn btn-info"
									name="btnModificarDatosUsuario" value="Modificar Datos">
							</div>
						</div>
					</div>

				</form>



			</div>


			<div class="col-md-6">
				<h3>Foto del Usuario</h3>
				<hr />

				<div id="fotoPerfil" class="form-group" align="center">
					<c:if test="${not empty objUsuario.rutaFotoPerfil}">
						<img class="img-rounded" id="fotoUsuario" alt=" " width="250"
							height="250" src="${objUsuario.rutaFotoPerfil}" />
					</c:if>

					<c:if test="${empty objUsuario.rutaFotoPerfil}">
						<!-- <img class="img-rounded" alt=" " width="60" height="60"
								src="<c:url value='${url.currentModule}/${rutaDefaultFoto}'/>" />
							 -->
						<img class="img-rounded" alt=" " width="250" height="250"
							id="fotoUsuarioImg" src="${rutaDefaultFoto}" />

					</c:if>
				</div>
				<div class="row" align="center">
					<label for="cambio" class="btn btn-info">Cambiar imagen</label>
				</div>

				<div class="form-group">
					<div class="col-sm-12" align="right">
						<form action="UploadFilesServlet" id="formPhotoUpdate"
							method="post" enctype="multipart/form-data">
							<input type="file" id="cambio" name="archivo"
								accept="image/jpeg,image/gif,image/png"
								style="visibility: hidden;" />
						</form>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12" align="center">
						<a class="btn btn-default" data-toggle="modal"
							data-target="#formCambioClave"> <span
							class="glyphicon glyphicon-cog"></span> Cambiar contraseña
						</a>
					</div>
				</div>
			</div>
		</div>

		<div class="row"></div>


		<div class="row" title="FormularioCambioClave (Modal)">
			<div class="modal fade" id="formCambioClave" role="dialog">
				<!-- <div class="modal-dialog"></div> -->
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<%@ include file="UsuarioCambioClave.jsp"%>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar/
							Volver</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(function() {
			$("#cambio").change(
					function() {
						var form = $('#formPhotoUpdate')[0];

						var data = new FormData(form);

						data.append("accionPOST", "cambiarImagenUsuario");

						$.ajax({
							type : "POST",
							enctype : 'multipart/form-data',
							url : "UploadFilesServlet",
							data : data,
							processData : false,
							contentType : false,
							cache : false,
							timeout : 600000,
							success : function(result) {
								if (result.newImage != null) {
									var obj = document
											.getElementById("fotoPerfil");
									obj.children[0].src = result.newImage + "?"
											+ (new Date()).getTime();
								}
							},
							error : function(e) {
							}
						});

					});
		});
	</script>
</body>
</html>