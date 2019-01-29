<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page</title>
<link href="web-design/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="web-design/bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="web-design/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Test Page</h2>
		<hr />
		
		<form action="TestServlet" method="post" enctype="multipart/form-data">
			<input type="file" name="archivo" accept="image/jpeg,image/gif,image/png" multiple/>
			<input type="hidden" name="cargarNuevasImagenes" value="3"/>
			<input type="submit"/>
		</form> 
		<div class="row">
		<h2>${message}</h2>
		</div>
		
		<c:if test="${not empty imagenes}">
			<form action="TestServlet" method="post">		
				<c:forEach items="${imagenes }" var="imagen">
					<img alt="" src="${imagen}"  width="200" height="200">
					<label for="cambio_${imagen }" class="btn btn-info">Cambiar imagen</label>
					<input type="file" id="cambio_${imagen }" name="archivo" accept="image/jpeg,image/gif,image/png" style="visibility: hidden;"/>
					
				</c:forEach>
			</form>
		</c:if>
		<form action="UploadFilesServlet" method="post" enctype="multipart/form-data">
			<input type="file" name="archivo" accept="image/jpeg,image/gif,image/png"/>
			<input type="hidden" name="accionPOST" value="cargarImagen"/>			
			<input type="submit"/>
		</form> 
		<form action="UploadFilesServlet" method="post" enctype="multipart/form-data">		
				
			<img alt="" src="${imagen}"  width="200" height="200">
			<label for="cambio" class="btn btn-info">Cambiar imagen</label>
			<input type="file" id="cambio" name="archivo" accept="image/jpeg,image/gif,image/png" style="visibility: hidden;"/>
			<input type="hidden" name="accionPOST" value="cambiarImagen"/>			
			<input type="hidden" name="sessionUSer" value="3"/>
			<input type="submit" value="actualizar"/>
			
		</form>
		<div class="row" hidden name="alertms">
			<div class="col-md-4">			
				<div class="alert alert-danger">
					Solo se pueden subir <strong>cuatro</strong> imagenes.
				</div>
			</div>
		</div>
	</div>
		



<script type="text/javascript">
	
	$(function(){
		
		$("input[type = 'submit']").click(function(e){
			var $file = $("input[name='archivo']");
			if(parseInt($file.get(0).files.length) > 4){
				$("div[name='alertms']").removeAttr("hidden");
				e.preventDefault();
			}else{
				$("div[name='alertms']").attr("hidden");	
			}
		});
		
	});
</script>

</body>
</html>