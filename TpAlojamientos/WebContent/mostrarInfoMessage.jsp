<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<c:if test="${not empty objInfoMessage}">
		<c:choose>
			<c:when test="${objInfoMessage.estado eq true}">
				<div class="alert alert-success alert-dismissible fade in">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Éxito!</strong> ${objInfoMessage.message}
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger alert-dismissible fade in">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Oops!</strong> ${objInfoMessage.message}
				</div>
			</c:otherwise>
		</c:choose>
		<!-- Elimina la session de objInfoMessage  -->
		<c:remove var="objInfoMessage" />
	</c:if>

</body>
</html>