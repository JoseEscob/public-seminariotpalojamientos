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
				<form class="form-horizontal" action="/action_page.php">
					<!-- <div class="row" id="configuracion">
                      <div class="form-group">
                        <label class="col-sm-2 control-label">Identificador</label>
                        <div class="col-sm-10">
                          <p class="form-control-static">${user.idUsuario}</p>
                        </div>
                      </div>
                  </div>
                -->
					<div class="form-group">
						<label for="nombre" class="control-label col-xs-3">Nombre:
						</label>
						<div class="col-xs-9">
							<input type="text" name="nombre"  class="form-control"
								value="${user.nombre}" onkeypress="return soloLetras(event)"
								required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="apellido" class="control-label col-xs-3">Apellido:
						</label>
						<div class="col-xs-9">
							<input type="text" name="apellido" id="apellido"
								class="form-control" onkeypress="return soloLetras(event)"
								value="${user.apellido}" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="fechaNac" class="control-label col-xs-3">Fecha
							de Nacimiento: </label>
						<div class="col-xs-9">
							<input type="date" name="fechaNac" id="fechaNac"
								class="form-control" value="${user.fechaNac}"
								pattern="^\d{2}-\d{2}-\d{4}$" maxlength="10"
								placeholder="dd-MM-yyyy" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="dni" class="control-label col-sm-3">DNI: </label>
						<div class="col-sm-9">
							<input type="text" name="dni" id="dni" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								value="${user.dni}" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="mail" class="control-label col-sm-3">Mail: </label>
						<div class="col-sm-9">
							<input type="email" name="mail" id="mail" class="form-control"
								onpaste="return false" value="${user.mail}" required="true">
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
					<c:if test="${not empty rutaFotoPerfil}">
						<img class="img-rounded" alt=" " width="250" height="250"
							src="${rutaFotoPerfil}" />
					</c:if>

					<c:if test="${empty rutaFotoPerfil}">
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

			<div class="row"></div>
		</div>
			
</body>
</html>