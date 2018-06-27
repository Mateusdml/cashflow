package br.edu.ifpb.pweb2.cashflow.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.model.Usuario;

@WebServlet("/usuario/cadastre")
public class CadastrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obter os parametros
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		// Cria um objeto do modelo
		Usuario usuario = new Usuario(email, login, senha);
		
			
		
		// Insere o contato no banco de dados
		
		
		
		
		
		// Pagina de resposta: o proprio formulario
		
		
		
		
	}
}
