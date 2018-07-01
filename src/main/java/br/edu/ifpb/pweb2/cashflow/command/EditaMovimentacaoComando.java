package br.edu.ifpb.pweb2.cashflow.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflow.dao.PersistenceUtil;
import br.edu.ifpb.pweb2.cashflow.model.Movimentacao;

public class EditaMovimentacaoComando implements ICommand {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		MovimentacaoController movimentacaoCtrl = new MovimentacaoController(PersistenceUtil.getCurrentEntityManager());
		
		Movimentacao movimentacao = movimentacaoCtrl.busque(request.getParameterMap());
		
		request.setAttribute("movimentacao", movimentacao);
		Resultado resultado = new Resultado();
		resultado.setProximaPagina("movimentacao/cadastro.jsp");
		return resultado;
	}

}
