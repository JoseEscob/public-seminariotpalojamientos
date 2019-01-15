<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="modelo.Usuario, extra.Tag, extra.Constantes"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Alojamientos</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>


	<%
		if (request.getSession().getAttribute(Constantes.sessionUser) != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute(Constantes.sessionUser);
	%>
	Sesion iniciada. Bienvenido
	<%=usuario.getNombre()%>
	<%
		} else {
			//Redireccionar
		}
	%>

</body>
</html>