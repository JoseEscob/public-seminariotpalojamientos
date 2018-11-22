<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iniciar Sesion</title>
</head>
<body>
	<form action="IniciarSesionServlet" method="post">
		Usuario <input type="text" name="txtUser"/>
		Contraseña <input type="text" name="txtPass"/>
		<input type="submit" name="btnLogin" value="Iniciar Sesion"/>
	</form>
</body>
</html>