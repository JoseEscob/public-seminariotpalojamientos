<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Usuario, extra.Tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Alojamientos</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>


	<%
		if (request.getSession().getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	%>
	Sesion iniciada. Bienvenido 
	<%=usuario.getNombre()%>
	<%
		}
	%>
</body>
</html>