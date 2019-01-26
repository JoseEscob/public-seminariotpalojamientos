<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page</title>
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<h2>Test Page</h2>
		<hr />
		
		<form action="TestServlet" method="post" enctype="multipart/form-data">
			<input type="file" name="archivo"/>
			<input type="text" name="newName"/>
			<input type="submit"/>
		</form>
		<div class="row">
		<h2>${message}</h2>
		</div>
		
	</div>
		

<script src="bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>




</body>
</html>