<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Listado</title>
</head>
<body>
	<!-- Al incluir el banner me parece q ya incluye lo del HEAD y los script del final -->
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<h4>
			<c:out value="${sessionScope.sessionUser.apellido}" />
		</h4>
		<h4>
			<c:out value="${sessionUser.fechaUltConexion}" />
		</h4>
		<c:choose>
			<c:when test="${fn:length(listadoDePublicaciones) gt 0}">
				<h4>Cant. de Publicaciones:
					${fn:length(listadoDePublicaciones)}</h4>
			</c:when>
			<c:otherwise>
				<h4>No se encontraron nuevas Publicaciones</h4>
			</c:otherwise>
		</c:choose>
	</div>


	<div class="row">
		<div class="row col-md-12">
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#ListadoPublicaciones"
					data-toggle="tab"><h4>
							Listado Publicaciones&nbsp;<span class="badge">${fn:length(listadoDePublicaciones)}</span>
						</h4></a></li>
				<li><a href="#CuadriculaPublicaciones" data-toggle="tab"><h4>Cuadricula
							Publicaciones</h4></a></li>
			</ul>
		</div>
		<div class="row col-md-12">
			<div class="tab-content">

				<div class="tab-pane" id="CuadriculaPublicaciones">
					<br>
					<%@ include file="mostrarListadoDePublicaciones.jsp"%>
				</div>

				<div class="tab-pane active" id="ListadoPublicaciones">


					<div class="container">
						<h3>Lista de Publicaciones</h3>
						<table class="table table-hover table-responsive">
							<%@ include file="headerPublicacion.jsp"%>
						
							<tbody>
								<c:forEach items="${listadoDePublicaciones}"
									var="objPublicacion">
									<tr class="info">
									<%@ include file="filaPublicacion.jsp"%>
									</tr>
								</c:forEach>
								<c:forEach items="${listadoDePublicaciones}"
									var="objPublicacion">
									<tr>

									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>



				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
		$(function(){
			$('#ListadoUsuarios').click(function(){
				if(checkN("NuevosUsuarios"))
					activeTab("listadoUsuariosNuevos");
				if(checkN("TodosUsuarios"))
					activeTab("listadoTodosUsuarios");
			});
			$('#CuadriculaUsuarios').click(function(){
				if(checkN("NuevosUsuarios"))
					activeTab("cuadriculaUsuariosNuevos");
				if(checkN("TodosUsuarios"))
					activeTab("cuadriculaTodosUsuarios");
			});
			$('#NuevosUsuarios').click(function(){	
				if(checkN("ListadoUsuarios"))
					activeTab("listadoUsuariosNuevos");
				if(checkN("CuadriculaUsuarios"))
					activeTab("cuadriculaUsuariosNuevos");
			});
			$('#TodosUsuarios').click(function(){	
				if(checkN("ListadoUsuarios"))
					activeTab("listadoTodosUsuarios");
				if(checkN("CuadriculaUsuarios"))
					activeTab("cuadriculaTodosUsuarios");
		
			});
			
			function checkN(nameTab){
				if($('#'+nameTab).attr('class') != null){
					var nuevos = $('#'+nameTab).attr('class').split(' ');
					for(var i = 0; i < nuevos.length; i ++){
						if(nuevos[i] == "active"){
							return true;
						}
					}
				}
				return false;
			}
			
			function activeTab(nameTab){
				$('#listadoUsuariosNuevos').removeClass("active");
				$('#listadoTodosUsuarios').removeClass("active");
				$('#cuadriculaUsuariosNuevos').removeClass("active");
				$('#cuadriculaTodosUsuarios').removeClass("active");
				$('#'+nameTab).addClass("active");
			}
		});
	</script>
	


</body>
</html>