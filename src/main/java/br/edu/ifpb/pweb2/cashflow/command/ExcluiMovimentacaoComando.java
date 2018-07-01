package br.edu.ifpb.pweb2.cashflow.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.controller.MovimentacaoController;
import br.edu.ifpb.pweb2.cashflow.dao.PersistenceUtil;

public class ExcluiMovimentacaoComando  implements ICommand  {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		MovimentacaoController movimentacaoCtrl = new MovimentacaoController(PersistenceUtil.getCurrentEntityManager());
		
		Resultado resultado = movimentacaoCtrl.exclua(request.getParameterMap());
		if (resultado.isErro()) {
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina("movimentacao/lista.jsp");
		} else {
			resultado.setProximaPagina("controller.do?op=conmov");
			resultado.setRedirect(true);
		}
		
		return resultado;
	}

}
