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
	<h1>En construccion!</h1>
		<h3>Imagenes de la publicación</h3>
		<h4>Se pueden seleccionar hasta 20 imagenes</h4>
		<h5 id="imageCounter">Imagenes 0/20</h5>
		<hr/>
	</div>
	<div class="row">
		<div class="col-md-2">
			<label for="imagenes" class="btn btn-info">Seleccionar imagenes</label>
		</div>
		<div class="col-md-2">
			<form action="UploadFilesServlet" method="post" enctype="multipart/form-data">
				<input type="hidden" name="accionPOST" value="saveTempFiles">
				<input type="submit" value="Finalizar" class="btn btn-success">
			</form>
		</div>
	</div>

	<div class="row">				
		<form action="UploadFilesServlet" method="post" enctype="multipart/form-data" id="fileUploadForm">
			<input type="file" id="imagenes" name="archivo" accept="image/jpeg,image/gif,image/png" multiple style="visibility: hidden;"/>
			
		</form> 
	</div>
	<div class="row" hidden name="alertms">
			<div class="col-md-4">			
				<div class="alert alert-danger">
					Solo se pueden subir <strong>veinte</strong> imagenes.
				</div>
			</div>
	</div>

	<div id="newImageRows" clas="row">
		
	</div>
</div>
<script type="text/javascript">
	
	$(function(){
		
		$("input[name='archivo']").change(function(e){
			var $file = $("input[name='archivo']");
			if(parseInt($file.get(0).files.length) > 20){
				$("div[name='alertms']").removeAttr("hidden");
				e.preventDefault();
			}else{
				$("div[name='alertms']").attr("hidden");	
				
				
				 // Get form
		        var form = $('#fileUploadForm')[0];

				// Create an FormData object 
		        var data = new FormData(form);
				
				data.append("accionPOST", "retornaImagenes");
			
				   $.ajax({
			            type: "POST",
			            enctype: 'multipart/form-data',
			            url: "UploadFilesServlet",
			            data: data,
			            processData: false,
			            contentType: false,
			            cache: false,
			            timeout: 600000,
			            success: function (data) {
							actualizarCantidad(data.cantidadSubida);
			            	$.each(data.imagenes, function(index, value){
			            		$("#newImageRows").append("<div class='col-md-3'> <img src='"+value.rutaImgPublicacion+"' class='img-responsive'><form action='UploadFileServlet' method='post'><input type='submit' class='btn btn-danger' value='Quitar'><input type='hidden' name='accionPOST' value='deleteImageTmp'><input type='hidden' name='image' value='"+value.rutaImgPublicacion+"'></form></div");
			            		console.log(value.rutaImgPublicacion);
			            	});
			            	if(data.limite != null){
			    				$("div[name='alertms']").removeAttr("hidden");
			            	}

			            },
			            error: function (e) {

			            }
			        });
				
			}
		});
		function actualizarCantidad(cantidad){
			$("#imageCounter").empty();
			$("#imageCounter").append("Imagenes "+cantidad+"/20");
		}
		function createImageBlock(value){
			var Div = Document.createElement("div");
			Div.
		}
	});
</script>



</body>
</html>