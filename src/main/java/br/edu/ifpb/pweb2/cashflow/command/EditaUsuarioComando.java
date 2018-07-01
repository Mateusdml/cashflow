package br.edu.ifpb.pweb2.cashflow.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.controller.UsuarioController;
import br.edu.ifpb.pweb2.cashflow.dao.PersistenceUtil;
import br.edu.ifpb.pweb2.cashflow.model.Usuario;

public class EditaUsuarioComando implements ICommand {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		
		Usuario usuario = usuarioCtrl.busque(request.getParameterMap());
		
		request.setAttribute("usuario", usuario);
		Resultado resultado = new Resultado();
		resultado.setProximaPagina("usuario/cadastro.jsp");
		return resultado;
	}
}