<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page import="modelo.Usuario, extra.Tag, modelo.Publicacion, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Alojamientos</title>
</head>
<body>

	<%
	// control de sesion
		if (request.getSession().getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if(usuario.isAdmin()){
				
				ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuariosBajoPuntaje");
				ArrayList<Publicacion> publicaciones = (ArrayList<Publicacion>) request.getAttribute("publicacionesBajoPuntaje");
				
				//Siempre deberiamos mostrar primer las publicaciones.
				
				
				
			}else{
				//Redireccionar
			}
		}else{
			//Redireccionar
		}
	%>
	<%@ include file="Banner.jsp"%>

	
</body>
</html>