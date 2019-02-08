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
		<label for="" class="btn btn-success" onclick="addImages(${idPublicacion})" id="btnAdd">Añadir Imagenes <label for="" style="font-weight: normal;" id="imageCounter">${imageCounter }</label> / 20</label>
	</div>
	<div class="row">
		
		<c:choose>
			<c:when test="${not empty listImagenes }">
				<c:forEach items="${listImagenes }" var="objImagen">
				
					<div class="container col-md-6" id="imagen_${objImagen.idImagen }">
						<img alt="imagen de la publicacion" src="${objImagen.rutaImgPublicacion}" class="img-responsive">
						<label for="" class="btn btn-primary cimg" id="chn_${objImagen.idImagen }" onclick="onclickCambiarEvent(${objImagen.idImagen},${objImagen.idPublicacion })">Cambiar</label>
						<label for="" class="btn btn-danger" onclick="eliminarImagen(${objImagen.idImagen},${objImagen.idPublicacion})">Eliminar</label>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<h4>No se han encontrado imagenes para esta publiacion.</h4>					
				</div>
				<label for="" class="btn btn-primary">Cargar Imagenes</label>
			</c:otherwise>
			
		</c:choose>
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
			<label for="" class="btn btn-primary">Cargar Imagenes</label>
		</div>
	</div>

</div>
<script type="text/javascript">

	function eliminarImagen(idImagen, idPublicacion){
		ajaxUploadFilesServlet("formImage", "borrarImagenPublicacion", function(result){
			if(result.carpetaVacia != null){
				document.getElementById("msgInfo").removeAttribute("hidden");

			}
			if(result.ocultar != null){
				document.getElementById("imagen_"+result.ocultar).setAttribute("hidden", true);
				console.log(result.ocultar);
			}
			if(result.imageCounter != null){
				$('#btnAdd').empty();
				$('#btnAdd').append("Añadir Imagenes "+result.imageCounter+" / 20");
						
				//$('#btnAdd')[0].innerText = "Añadir Imagenes "+result.imageCounter+" / 20";

			}
			
		},function(e){},{"idPublicacion": idPublicacion, "idImagen": idImagen});
	}
	function actualizarImagen("formImage", idPublicacion){
		ajaxUploadFilesServlet(idImagen,"cambiarImagenPublicacion", function(result){
			if(result.newImage != null){
				var obj = document.getElementById("imagen_"+result.newImage.idImagen);
				obj.children[0].src =result.newImage.rutaImgPublicacion+"?"+(new Date()).getTime();
			}
			
		},function(e){}, {"idPublicacion": idPublicacion, "idImagen": idImagen});
	}
	
	function cargarNuevasImagenes(idPublicacion){
		ajaxUploadFilesServlet("formImageMultiple", "subirImagenesPublicacion",function(result){
			
		}, function(e){},{"idPublicacion", idPublicacion});
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
	
	function updateImage(idImagen){
		var obj = document.getElementById("imagen_"+idImagen);
		var src = obj.children[0].src;
		obj.children[0].src = "";
		obj.children[0].src =src+"?"+(new Date()).getTime();
	}
	
	
	function onclickCambiarEvent(idImagen, idPublicacion){
		$('#input_image').unbind("change");
		$('#input_image').change(function(){
			actualizarImagen(idImagen, idPublicacion);
		});
		$('#input_image').focus().trigger("click");
		
		
	}
	
	function addImages(idPublicacion){
		$('#inputImageMultiple').unbind("change");
		$('#inputImageMultiple').change(function(){
			//actualizarImagen(idImagen, idPublicacion);
			console.log(parseInt($('#imageCounter')[0].innerText)+1);
			cargarNuevasImagenes(idPublicacion);
		});
		$('#inputImageMultiple').focus().trigger("click");
	}	
</script>
</body>
</html>