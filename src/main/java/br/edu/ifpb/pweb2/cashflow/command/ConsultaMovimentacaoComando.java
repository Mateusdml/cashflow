package br.edu.ifpb.pweb2.cashflow.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb2.cashflow.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.dao.PersistenceUtil;
import br.edu.ifpb.pweb2.cashflow.model.Movimentacao;

public class ConsultaMovimentacaoComando implements ICommand {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		MovimentacaoController movimentacaoCtrl = new MovimentacaoController(PersistenceUtil.getCurrentEntityManager());
		
		HttpSession session = request.getSession();
		Movimentacao movimentacao = (Movimentacao) session.getAttribute("movimentacao");
		List<Movimentacao> movimentacoes = movimentacaoCtrl.consulte(movimentacao);
		
		Resultado resultado = new Resultado();
		request.setAttribute("movimentacao", movimentacoes);
		resultado.setProximaPagina("movimentacao/movimentacoes.jsp");
		return resultado;
	}

}
