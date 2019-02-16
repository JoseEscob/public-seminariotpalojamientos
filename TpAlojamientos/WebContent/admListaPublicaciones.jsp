<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Admin - Lista de Publicaciones</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<h2>Admin - Lista de Publicaciones</h2>
		<c:choose>
			<c:when test="${fn:length(listadoDePublicaciones) gt 0}">
				<h4>Cant. de Usuarios: ${fn:length(listadoDePublicaciones)}</h4>
			</c:when>
			<c:otherwise>
				<h4>No se encontraron registros de Publicaciones</h4>
			</c:otherwise>
		</c:choose>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>

	<div class="container">

		<div class="row">
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#ListadoNoVerificados"
					data-toggle="tab"><h4>
							Publicaciones No Verificadas&nbsp;<span class="badge"></span>
						</h4></a></li>
				<li><a href="#ListadoVerificados" data-toggle="tab"><h4>
							Publicaciones Verificadas&nbsp;<span class="badge"></span>
						</h4></a></li>
			</ul>
		</div>

		<div class="row col-md-12">
			<br>
		</div>
		<div class="row">
			<div class="tab-content">
				<div id="ListadoNoVerificados" class="tab-pane active">
					<div class="container">
						<table class="table table-hover table-responsive">
							<%@ include file="headerPublicacion.jsp"%>
							<tbody>
								<c:forEach items="${listadoDePublicaciones}"
									var="objPublicacion">
									<c:if test="${objPublicacion.verificado eq false}">
										<tr>
											<%@ include file="filaPublicacion.jsp"%>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div id="ListadoVerificados" class="tab-pane">
					<div class="container">
						<table class="table table-hover table-responsive">
							<%@ include file="headerPublicacion.jsp"%>
							<tbody>
								<c:forEach items="${listadoDePublicaciones}"
									var="objPublicacion">
									<c:if test="${objPublicacion.verificado eq true}">
										<tr class="info">
											<%@ include file="filaPublicacion.jsp"%>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>