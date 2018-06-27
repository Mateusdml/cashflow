package br.edu.ifpb.pweb2.cashflow.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.command.ICommand;
import br.edu.ifpb.pweb2.cashflow.controller.Resultado;


@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOME_PACOTE = "br.edu.ifpb.pweb2.projetos.cashflow.command.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doRequest(request, response);
		
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Limpa as mensagens entre paginas
		request.getServletContext().removeAttribute("_msg");
	
	// Descobre a classe de comando a ser executada:
	Properties comandos = (Properties) request.getServletContext().getAttribute("comandos");
	
	String operacao = request.getParameter("op");
	if (operacao == null) {
		this.getServletContext().setAttribute("msgs", "Operacao op nao especificada na requisicao!");
		response.sendRedirect(request.getHeader("Referer"));
		return;	
	}
	
	// Usa reflection para carregar a classe do comando dinamicamente
	Resultado resultado = null;
	String nomeClasseCommand = comandos.getProperty(operacao);
	try {
		Class<?> clazzCommand = Class.forName(NOME_PACOTE + nomeClasseCommand);
		ICommand comando = (ICommand) clazzCommand.newInstance();
		resultado = comando.execute(request, response);
		
	} catch (ClassNotFoundException | InstatiationException | IllegalAccessException e) {
		this.getServletContext().setAttribute("_msg", "Comando inexistente, verifique arquivo de comandos!");
		response.sendRedirect(request.getHeader("Referer"));
		return;
	} catch (Exception e) {
		this.getServletContext().setAttribute("_msg", "Erro inesperado!");
		response.sendRedirect(request.getHeader("Referer"));
		return;
	}
	
	if (resultado.isErro()) {
		// Retorna para a pagina principal
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultado.getProximaPagina());
		dispatcher.forward(request, response);
	} else {
		// Vai para a proxima pagina
		if (resultado.isRedirect()) {
			response.sendRedirect(resultado.getProximaPagina());
		} else {
			request.getRequestDispatcher(resultado.getProximaPagina()).forward(request, response);
		}
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doRequest(request, response);
	}

}
