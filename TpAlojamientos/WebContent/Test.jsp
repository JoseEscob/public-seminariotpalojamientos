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
		<button class="btn btn-info" id="add">Añadir</button>
		
		<div class="row" id="identificado">
		</div>
	</div>
		



<script type="text/javascript">
	
	$(function(){
		
		$("#add").click(function(){
			var div = document.createElement("div");
			div.setAttribute("class", "col-md-3");
			var img = document.createElement("img");
			img.setAttribute("alt","alterna");
			div.appendChild(img);
			var form = document.createElement("form");
			form.setAttribute("action", "action");
			form.setAttribute("method", "method");
			var submit = document.createElement("input");
			submit.setAttribute("type","submit");
			submit.setAttribute("value","quitar");
			form.appendChild(submit);
			div.appendChild(form);
			$("#identificado").append(div);
		});
		
	});
</script>

</body>
</html>