<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
<div class="container">
	
	<div class="row">
		<h1>En costruccion!</h1>
		<h4>Editar imagenes de la publicacion</h4>
		<hr/>
		<label for="" class="btn btn-primary" onclick="addImages(${idPublicacion})" id="btnAdd">Añadir Imagenes <label for="" style="font-weight: normal;" id="imageCounter">${imageCounter }</label> / 20</label>
		<a class="btn btn-success" href="PublicacionServlet?accionGET=VerPublicacion&idPublicacion=${idPublicacion}">Finalizar</a>
		<hr/>
	</div>
	<div class="row">
		<div id="resultImages">
			<c:choose>
				<c:when test="${not empty listImagenes }">
					<c:forEach items="${listImagenes }" var="objImagen">
					
						<div class="container-fluid col-md-6" id="imagen_${objImagen.idImagen }">
							<img alt="imagen de la publicacion" src="${objImagen.rutaImgPublicacion}" class="img-responsive">
							<label for="" class="btn btn-primary cimg" id="chn_${objImagen.idImagen }" onclick="onclickCambiarEvent(${objImagen.idImagen},${objImagen.idPublicacion })">Cambiar</label>
							<label for="" class="btn btn-danger" onclick="eliminarImagen(${objImagen.idImagen},${objImagen.idPublicacion})">Eliminar</label>
							<hr/>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info" id="msg">
						<h4>No se han encontrado imagenes para esta publiacion.</h4>					
					</div>
				</c:otherwise>
				
			</c:choose>
		</div>
		<form action="UploadFilesServlet" action="post" enctype="multipart/form-data" id="formImage">
			<input type="file" name="archivo" id="input_image" accept="image/jpeg,image/gif,image/png" style="visibility: hidden;">
		</form>
		<form action="UploadFilesServlet" action="post" enctype="multipart/form-data" id="formImageMultiple">
			<input type="file" name="archivo" id="inputImageMultiple" accept="image/jpeg,image/gif,image/png" style="visibility: hidden;" multiple>
		</form>
		<div id="msgInfo" hidden>
			<div class="alert alert-info">
				<h4>No se han encontrado imagenes para esta publiacion.</h4>					
			</div>
		</div>
	</div>

</div>
<script type="text/javascript">

	

	function eliminarImagen(idImagen, idPublicacion){
		ajaxUploadFilesServlet("formImage", "borrarImagenPublicacion", function(result){
			if(result.carpetaVacia != null)
				mostrarNoSeEncontraronImagenes(true);
			
			if(result.ocultar != null)
				document.getElementById("imagen_"+result.ocultar).setAttribute("hidden", true);
			
			if(result.imageCounter != null){
				actualizarTextoLimite(result.imageCounter);	
			}
			
		},function(e){},{"idPublicacion": idPublicacion, "idImagen": idImagen});
	}
	
	function actualizarImagen(idImagen, idPublicacion){
		ajaxUploadFilesServlet("formImage","cambiarImagenPublicacion", function(result){
			if(result.newImage != null){
				var obj = document.getElementById("imagen_"+result.newImage.idImagen);
				obj.children[0].src =result.newImage.rutaImgPublicacion+"?"+(new Date()).getTime();
			}
			
		},function(e){}, {"idPublicacion": idPublicacion, "idImagen": idImagen});
	}
	
	function cargarNuevasImagenes(idPublicacion){
		
		ajaxUploadFilesServlet("formImageMultiple", "subirImagenesPublicacion",function(result){
			if(result.imageCounter != null){
				actualizarTextoLimite(result.imageCounter);	
				if(result.imageCounter >= 20){
					actualizarTextoLimite(20);	
				}
			}
			
			if(result.imagenes != null){
				mostrarNoSeEncontraronImagenes(false);

				$('#resultImages').empty();
				for(var k in result.imagenes){
					addElement(result.imagenes[k].idImagen, result.imagenes[k].idPublicacion, result.imagenes[k].rutaImgPublicacion);
				}
			}
			
		}, function(e){},{"idPublicacion": idPublicacion});
	}
	
	
	
	function ajaxUploadFilesServlet(idForm, functionName, successFunction, errorFunction, dataForAppend){
		 // Get form
	    var form = $('#'+idForm)[0];
	
		// Create an FormData object 
	    var data = new FormData(form);
		
		if(dataForAppend != null)
			for(var key in dataForAppend)
				data.append(key,dataForAppend[key]);
		
		data.append("accionPOST", functionName);
	
		  $.ajax({
	            type: "POST",
	            enctype: 'multipart/form-data',
	            url: "UploadFilesServlet",
	            data: data,
	            processData: false,
	            contentType: false,
	            cache: false,
	            timeout: 600000,
	            success: successFunction,
	            error: errorFunction
	        });
		
	}

	function onclickCambiarEvent(idImagen, idPublicacion){
		$('#input_image').unbind("change");
		$('#input_image').change(function(){
			actualizarImagen(idImagen, idPublicacion);
		});
		$('#input_image').focus().trigger("click");
		
		
	}
	
	function addElement(idImagen, idPublicacion, src){
		var div = document.createElement("div");
		var img = document.createElement("img");
		var cambiar = document.createElement("label");
		var eliminar = document.createElement("label");
		
		div.setAttribute("class", "container col-md-6");
		div.setAttribute("id","imagen_"+idImagen);
		
		img.setAttribute("class", "img-responsive");
		img.alt="Imagen de la publicacion";
		img.src=src;

		
		cambiar.setAttribute("class" , "btn btn-primary cimg");
		cambiar.setAttribute("id", "chn_"+idImagen);
		cambiar.setAttribute("onclick", "onclickCambiarEvent("+idImagen+","+idPublicacion+")");
		cambiar.innerText = "Cambiar";
		
		eliminar.setAttribute("class", "btn btn-danger");
		eliminar.setAttribute("onclick", "eliminarImagen("+idImagen+", "+idPublicacion+")");
		eliminar.innerText="Eliminar";
		
		div.appendChild(img);
		div.appendChild(cambiar);
		div.appendChild(eliminar);

		$('#resultImages').append(div);

	}

	function addImages(idPublicacion){
		$('#inputImageMultiple').unbind("change");
		$('#inputImageMultiple').change(function(){
			//actualizarImagen(idImagen, idPublicacion);
			cargarNuevasImagenes(idPublicacion);
			
		});
		$('#inputImageMultiple').focus().trigger("click");
	}		
	function actualizarTextoLimite(actividad){
		$('#btnAdd').empty();
		$('#btnAdd').append("Añadir Imagenes "+actividad+" / 20");
	}

	function mostrarNoSeEncontraronImagenes(estado){
		if(estado && $("#msgInfo").attr("hidden"))
			document.getElementById("msgInfo").removeAttribute("hidden");
		else
			document.getElementById("msgInfo").setAttribute("hidden", true);
	}
	
	
	
</script>
</body>
</html>