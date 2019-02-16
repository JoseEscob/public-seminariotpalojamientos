<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<div class="container">
		<h2>Cambiar claves</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>
	<form action="UsuarioServlet" method="post">
		<div class="container">
			<div class="col-md-12">
				<div class="col-md-12">
					<div class="form-group">
						<label>Contraseña Actual</label> <input type="password"
							name="claveActual" class="form-control" maxlength="20"
							onpaste="return false">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Nueva contraseña </label> <input type="password"
							name="claveUno" class="form-control" maxlength="20"
							onpaste="return false">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Repita contraseña </label> <input type="password"
							name="claveDos" class="form-control" maxlength="20"
							onpaste="return false">
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label>Contraseña Actual</label> <input type="password"
							name="claveActual" class="form-control" maxlength="20"
							onpaste="return false">
					</div>
				</div>

				<div class="col-md-12" align="center">
					<input type="hidden" name="accionPOST" value="cambiarClaveUsuario">
					<input type="submit" class="btn btn-primary" name="btnCambiarClave"
						value="Cambiar clave">
				</div>
			</div>
		</div>
	</form>
</body>
</html>