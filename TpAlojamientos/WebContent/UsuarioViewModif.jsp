<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="modelo.Usuario, extra.Tag"%>
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


	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h3>Datos Personales</h3>
				<form class="form-horizontal" action="">

					<div class="form-group ">
						<div class="col-xs-8">
							<label class="control-label col-xs-7">Fecha Última
								Conexión: </label>
							<div class="col-xs-5">
								<label for="nombre" class="control-label"> <c:out
										value="${objUsuario.anteriorFechaUltConexion}"></c:out>
								</label>
							</div>
						</div>
						<c:if test="${objUsuario.isVerificado() eq true}">
							<div class="col-xs-4">
								<label class="control-label pull-right "> <span
									class="glyphicon glyphicon-ok-circle"></span> Usuario
									Verificado
								</label>
							</div>
						</c:if>

					</div>


					<div class="form-group">
						<div class="pull-right col-xs-3">
							<label class="control-label"> <span
								class="glyphicon glyphicon-stats"></span> Puntaje:
								${objUsuario.puntaje}
							</label>
						</div>

					</div>


					<div class="form-group">
						<label for="nombre" class="control-label col-xs-3">Nombre:
						</label>
						<div class="col-xs-9">
							<input type="text" name="nombre" class="form-control"
								value="${objUsuario.nombre}"
								onkeypress="return soloLetras(event)" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="apellido" class="control-label col-xs-3">Apellido:
						</label>
						<div class="col-xs-9">
							<input type="text" name="apellido" id="apellido"
								class="form-control" onkeypress="return soloLetras(event)"
								value="${objUsuario.apellido}" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="fechaNac" class="control-label col-xs-3">Fecha
							de Nacimiento: </label>
						<div class="col-xs-9">
							<input type="date" name="fechaNac" id="fechaNac"
								class="form-control" value="${objUsuario.fechaNac}"
								pattern="^\d{2}-\d{2}-\d{4}$" maxlength="10"
								placeholder="dd-MM-yyyy" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="dni" class="control-label col-sm-3">DNI: </label>
						<div class="col-sm-9">
							<input type="text" name="dni" id="dni" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								value="${objUsuario.dni}" required="true">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Mail: </label>
						<div class="col-sm-9">
							<input type="email" name="mail" id="mail" class="form-control"
								onpaste="return false" value="${objUsuario.mail}"
								required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="mail" class="control-label col-sm-3">Sexo: </label>
						<div class="col-sm-9">
							<c:choose>
								<c:when test="${objUsuario.sexo}">
									<label class="radio-inline"><input type="radio"
										name="rdbSexo" checked>Masculino</label>
									<label class="radio-inline"><input type="radio"
										name="rdbSexo">Femenino</label>
								</c:when>
								<c:otherwise>
									<label class="radio-inline"><input type="radio"
										name="rdbSexo">Masculino</label>
									<label class="radio-inline"><input type="radio"
										name="rdbSexo" checked>Femenino</label>
								</c:otherwise>
							</c:choose>

						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-10" align="right">
							<button class="btn btn-info">Modificar Datos</button>
						</div>
					</div>

				</form>
			</div>

			<div class="col-md-6">
				<h3>Foto del Usuario</h3>


				<div id="fotoPerfil" class="form-group" align="center">
					<c:if test="${not empty objUsuario.rutaFotoPerfil}">
						<img class="img-rounded" alt=" " width="250" height="250"
							src="${objUsuario.rutaFotoPerfil}" />
					</c:if>

					<c:if test="${empty objUsuario.rutaFotoPerfil}">
						<!-- <img class="img-rounded" alt=" " width="60" height="60"
								src="<c:url value='${url.currentModule}/${rutaDefaultFoto}'/>" />
							 -->
						<img class="img-rounded" alt=" " width="250" height="250"
							src="${rutaDefaultFoto}" />

					</c:if>
				</div>
				<form class="form-horizontal" action="/action_page.php">
					<div class="form-group">
						<div class="col-sm-12" align="right">
							<button class="btn btn-info">Cambiar foto</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="row"></div>
	</div>
</body>
</html>