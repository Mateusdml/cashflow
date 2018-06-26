<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Controle Financeiro Pessoal - Cadastro de Usuário</title>

<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<div class="container">
		<h1>Cadastro de Usuário</h1>
		
		<form action="${pageContext.request.contextPath}/cadastro" method="post">
			
			<div class="form-group">
				
				<label for="exampleInputEmail1">Email</label>
				<input type="email" name=email
					
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Seu email para contato e login" />
					
				<!-- ACIMA, CHAMADA PARA O SERVLET -->

				<small id="emailHelp" class="form-text text-muted">We'll
					never share your email with anyone else.</small><br/><br/>
			</div>
			
			<div class="form-group">
				<label for="exampleInputName">Login</label>
				<input type="text" name="login"
				
					class="form-control" id="exampleInputName"
					aria-describedby="NameHelp" placeholder="Seu nome de usuario" /><br/><br/>
					
				<!-- ACIMA, CHAMADA PARA O SERVLET -->

			</div>
			
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label>
				<input type="password" name="password"
				
					class="form-control" id="exampleInputPassword1" placeholder="Password" /><br/><br/>
				
				<!-- ACIMA, CHAMADA PARA O SERVLET -->
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	
</body>
</html>