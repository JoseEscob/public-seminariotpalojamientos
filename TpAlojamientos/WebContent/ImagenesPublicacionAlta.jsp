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
		<form action="UploadFilesServlet" method="post" enctype="multipart/form-data">
			<label for="imagenes" class="btn btn-info">Seleccionar imagenes</label>
			<input type="file" id="imagenes" name="archivo" accept="image/jpeg,image/gif,image/png" multiple style="visibility: hidden;"/>
			<input type="hidden" name="accionPOST" value="cargarImagenesPublicacion"/>
			<input type="hidden" name="idPublicacion" value="${idPublicacion}"/>
			<input type="submit"/>
		</form> 
	</div>

</div>

</body>
</html>