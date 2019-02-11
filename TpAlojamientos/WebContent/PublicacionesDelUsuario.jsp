<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=ConstantesJSP.jspPub_PublicacionesListaGrillacss%>"
	rel="stylesheet">
<title>Mis Publicaciones</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<div class="row">
			<h2>Mis publicaciones</h2>
			<hr />

		</div>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
		<c:choose>
			<c:when test="${empty listadoDePublicaciones}">
				<div class="alert alert-info">
					<h4>No se encontraron publicaciones cargadas</h4>
				</div>
			</c:when>
			<c:otherwise>
				<h4>En esta secci�n se listan las publicaciones que cargaste.
					Ten�s ${fn:length(listadoDePublicaciones)} cargada/s</h4>
				<br>
				<div class="row container">
					<%@ include file="mostrarListadoDePublicaciones.jsp"%>
				</div>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>