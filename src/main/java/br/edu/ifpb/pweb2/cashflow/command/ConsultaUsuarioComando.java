package br.edu.ifpb.pweb2.cashflow.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb2.cashflow.controller.Resultado;
import br.edu.ifpb.pweb2.cashflow.controller.UsuarioController;
import br.edu.ifpb.pweb2.cashflow.dao.PersistenceUtil;
import br.edu.ifpb.pweb2.cashflow.model.Usuario;

public class ConsultaUsuarioComando implements ICommand {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		List<Usuario> usuarios = usuarioCtrl.consulte(usuario);
		
		Resultado resultado = new Resultado();
		request.setAttribute("usuarios", usuarios);
		resultado.setProximaPagina("usuario/lista.jsp");
		return resultado;
	}
}