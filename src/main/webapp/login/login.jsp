<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cashflow - Login</title>

<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<div class="container">
		<c:if test="${not empty_msg}">
			<div class="alert alert-danger">$_msg</div>
		</c:if>
		
		<form class="form-signin" action="${pageContext.request.contextPath}/controller.do?op=login" method="post">
			<label for="inputEmail" class="sr-only">Usuario</label>
			<input type="text" name="login" id="login" class="form-control" placeholder="O seu username para login" required autofocus
			   value="${cookie['loginCookie'].value}" /><br/><br/>
			   
			<label for="inputPassword" class="sr-only">Senha</label>
			<input type="password" name="senha" id="senha" class="form-control" placeholder="Senha" required /><br/>
			
			<div class="checkbox">
				<label><input type="checkbox" value="sim" name="lembrar" id="lembrar">
					Lembrar-me
				</label>
			</div>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
		</form>
	</div>

</body>
</html>