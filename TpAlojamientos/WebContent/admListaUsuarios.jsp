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
			<c:out value="${sessionUser.fechaUltConexion}" />
		</h4>
		<c:choose>
			<c:when test="${fn:length(listaUsuariosNuevos) gt 0}">
				<h4>Cant. de Usuarios nuevos: ${fn:length(listaUsuariosNuevos)}</h4>
			</c:when>
			<c:otherwise>
				<h4>No se encontraron nuevos usuario</h4>
			</c:otherwise>
		</c:choose>

		<h4>
			<c:out value="${sessionScope.sessionUser.apellido}" />
		</h4>
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs nav-justified">
					<li class="active" id="ListadoUsuarios">
						<a href="#s" data-toggle="tab" ><h4>
								Listado Usuarios
							</h4></a></li>
					<li  id="CuadriculaUsuarios" data-toggle="tab"><a href="#s" data-toggle="tab"><h4>
								Cuadricula Usuarios</h4></a></li>
				</ul>
			</div>
			<div class="row col-md-6">
				<ul class="nav nav-tabs nav-justified">
					<li class="active" id="NuevosUsuarios"><a href="#s"
						data-toggle="tab" ><h4>
								Nuevos Usuarios&nbsp;<span class="badge">${fn:length(listaUsuariosNuevos)}</span>
							</h4></a></li>
					<li id="TodosUsuarios"><a href="#s" data-toggle="tab"><h4>
								Todos los Usuarios</h4></a></li>
				</ul>
			</div>
		</div>

		<!--table class="table table-hover table-responsive">
			<c:forEach items="${listaUsuarios}" var="objUsuario">
				<tr>
					<td>${objUsuario.idUsuario}</td>
					<td><c:choose>
							<c:when
								test="${sessionUser.fechaUltConexion gt objUsuario.fechaAlta}">
								<h4>Mayor</h4>
							</c:when>
							<c:otherwise>
								<h4>menor</h4>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		<h3>Lista de Usuarios</h3>
		</table-->
		<div class="row">
			<div class="tab-content">
				<div class="tab-pane active" id="listadoUsuariosNuevos">
					<table class="table table-hover table-responsive">
						<%@ include file="headerUsuario.jsp"%>
						<tbody>
							<c:forEach items="${listaUsuariosNuevos}" var="objUsuario">
								<tr class="info">
								<%@ include file="filaUsuario.jsp"%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane" id="listadoTodosUsuarios">
					<table class="table table-hover table-responsive">
					<%@ include file="headerUsuario.jsp"%>
					
					<tbody>
						<c:forEach items="${listaUsuariosNuevos}" var="objUsuario">
							<tr class="info">
							<%@ include file="filaUsuario.jsp"%>
							</tr>
						</c:forEach>
						<c:forEach items="${listaUsuarios}" var="objUsuario">
							<tr>
								<%@ include file="filaUsuario.jsp"%>
							</tr>
						</c:forEach>
		
					</tbody>
				</table>
				</div>
				<div class="tab-pane" id="cuadriculaUsuariosNuevos">
				cuadricula nuevos
				</div>
				<div class="tab-pane" id="cuadriculaTodosUsuarios">
				cuadricula
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