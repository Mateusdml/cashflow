package br.edu.ifpb.pweb2.cashflow.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.controller.UsuarioController;
import br.edu.ifpb.pweb2.cashflow.dao.PersistenceUtil;

public class ExcluiUsuarioComando implements ICommand {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		
		Resultado resultado = usuarioCtrl.exclua(request.getParameterMap());
		if (resultado.isErro()) {
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina("usuario/lista.jsp");
		} else {
			resultado.setProximaPagina("controller.do?op=conusu");
			resultado.setRedirect(true);
		}
		
		return resultado;
	}
}