package br.edu.ifpb.pweb2.cashflow.controller;

import java.util.Map;

import javax.persistence.EntityManager;

import br.edu.ifpb.pweb2.cashflow.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.cashflow.model.Usuario;

public class LoginController {
	private EntityManager entityManager;
	
	public LoginController(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public Resultado isValido(Map<String, String[]> parametros) {
		Resultado r = new Resultado();
		r.setErro(false);
		
		String login = parametros.get("login")[0];
		String senha = parametros.get("senha")[0];
		
		UsuarioDAO udao = new UsuarioDAO(entityManager);
		Usuario usuario = udao.findByLogin(login);
		if (usuario != null) {
			if (usuario.getSenha().equals(senha)) {
				r.setModel(usuario);
			} else {
				r.setErro(true);
				r.addMensagens("Usuário ou senha inválida(o)");
			}
			
		} else {
			r.setErro(true);
			r.addMensagens("Usuário ou senha inválida(o)");
		}
		return r;
	}
}