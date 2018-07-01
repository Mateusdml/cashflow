package br.edu.ifpb.pweb2.cashflow.command;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.model.Movimentacao;

public class CadastraMovimentacaoComando implements ICommand {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		final String paginaSucesso = "controller.do?op=cadmov";
		final String paginaErro = "movimentacao/movimentacao.jsp";
		
		EntityManagerFactory emf = (EntityManagerFactory)
		request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		MovimentacaoController movimentacaoCtrl = new MovimentacaoController(em);
		
		Resultado resultado = movimentacaoCtrl.cadastre(request.getParameterMap());
		
		System.out.println("Resultado: " + resultado);

		if (!resultado.isErro()) {
			System.out.println("Entrou em !Erro de Resultado");

			resultado.setProximaPagina(paginaSucesso);
			resultado.setRedirect(true);
		} else {
			request.setAttribute("movimentacao", (Movimentacao) resultado.getModel());
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		}
		return resultado;
	}

}
