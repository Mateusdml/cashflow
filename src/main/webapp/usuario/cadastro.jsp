<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Controle Financeiro Pessoal - Cadastro de Usuário</title>

<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<c:if test="${not empty _msg}">
				<c:forEach var="_m" items="${_msg}">
					<div class="alert alert-danger">"${_m}"</div>
				</c:forEach>
			</c:if>

			<h1>Cadastro de Usuário</h1>

			<form action="${pageContext.request.contextPath}/controller.do?op=cadusu" method="post" class="form-horizontal">

				<div class="form-group">

					<input type="hidden" name="op" value="cadusu">
					<input type="hidden" name="id" value="${usuario.id}">
					<label for="exampleInputEmail1">Email</label>
					<input type="email"	name=email id="email" value="${usuario.email}" class="form-control" aria-describedby="emailHelp"
					placeholder="Seu email para contato e login" />

					<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small><br /><br />
				</div>

				<div class="form-group">

					<label for="exampleInputName">Login</label>
					<input type="text" name="login" id="login" value="${usuario.login}" class="form-control" aria-describedby="NameHelp"
					placeholder="Seu nome de usuario" /><br /><br />

				</div>

				<div class="form-group">
					<label for="exampleInputPassword1">Senha</label>
					<input	type="password" name="senha" id="senha" value="${usuario.senha}" class="form-control" placeholder="Senha" /><br /><br />
				</div>
				
				<input type="submit" class="form-control btn btn-primary" value="Salvar">
				<!--  <button type="submit" class="btn btn-primary">Submit</button>  -->
			</form>
		</div>
	</div>

</body>
</html>