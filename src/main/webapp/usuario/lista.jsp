<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Controle Financeiro Pessoal - Lista de Usuários</title>

<link
href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<c:if test="${not empty _msg}">
				<c:forEach var="_m" items="${_msg}">
					<div class="alert alert-danger">${_m}</div>
				</c:forEach>
			</c:if>
		
			<h1>Lista de Usuários</h1>
		
			<form action="${pageContext.request.contextPath}/controller.do" method="post" >
				<input type="hidden" name="op" value="delusu">
				<table class="table">
					<tr align="left">
						<th></th>
						<th style="width: 30%">Email</th>
						<th>Login</th>
					</tr>
					<c:forEach var="usuario" items="${usuarios}">
						<tr align="left">
							<td><input type="checkbox" name="delids" value="${usuario.id}" onclick="showDeleteIcon('bt-excluir')"></td>
							<td><a href="${pageContext.request.contextPath}/controller.do?op=edtusu&id=${usuario.id}">${usuario.email}</a></td>
							<td>${usuario.login}</td>
						</tr>					
					</c:forEach>
				</table>
				
				<div id="bt-excluir" style="display: none">
					<input type="submit" value="Excluir" class="form-control btn btn-danger" onclick="return confirm('Você quer realmente APAGAR o usuário?');" />				
				</div>
			</form>
			<a href="usuario/cadastro.jsp" class="form-control btn btn-primary">Novo</a>
		</div>
	</div>

</body>
</html>