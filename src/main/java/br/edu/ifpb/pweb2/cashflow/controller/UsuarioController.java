package br.edu.ifpb.pweb2.cashflow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifpb.pweb2.cashflow.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.cashflow.model.Usuario;

public class UsuarioController {
	private List<String> mensagensErro;
	private EntityManager entityManager;

	public UsuarioController(EntityManager em) {
		this.entityManager = em;
	}

	public Resultado cadastre(Map<String, String[]> parametros ) {
		Resultado resultado = new Resultado();
		Usuario usuario = null;
//		String email = null;
		
		if ((usuario = fromParametros(parametros)) != null) {
			UsuarioDAO dao = new UsuarioDAO(entityManager);
			dao.beginTransaction();
			
			System.out.println("Antes do INSERT no UsuarioController!");
			dao.insert(usuario);
			
			System.out.println("Usuario depois da persistencia no UsuarioController: " +usuario);
			dao.commit();
			System.out.println("Depois do commit.");
			resultado.setErro(false);
			String m = "Usuario salvo com sucesso!";
			resultado.addMensagens(m);
			
//			UsuarioDAO udao = new UsuarioDAO(entityManager);
//			usuario = udao.findByEmail(usuario.getEmail());
			
//			usuario.setEmail(email);
			
//			if (usuario.getEmail() == null) {
//				dao.insert(usuario);
//			} else {
//				dao.update(usuario);
//			}
//				dao.commit();
//				resultado.setErro(false);
//				String msg = "Usuario salvo com sucesso";
//				resultado.addMensagens(msg);
		} else {
//			resultado.setModel(usuario);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		System.out.println("Resultado: " +resultado);
		return resultado;
	}

	/**
	 * Valida os parametros e os transforma em um objeto do tipo Usuario. Se houver
	 * algum erro, preenche a variavel de instancia listaErros e retorna null.
	 * 
	 * @param parametros
	 *            Mapa de parametros
	 * @return um objeto Usuario ou null, em caso de erros.
	 */

	private Usuario fromParametros(Map<String, String[]> parametros) {
	// os nomes dos parametros vem dos atributos 'name' das tags HTML do formulario

		String[] email = parametros.get("email");
		String[] login = parametros.get("login");
		String[] senha = parametros.get("senha");

		Usuario usuario = new Usuario();
		this.mensagensErro = new ArrayList<String>();

		if (email == null || email.length == 0 || email[0].isEmpty()) {
			this.mensagensErro.add("Email é campo obrigatório!");
		} else {
			usuario.setEmail(email[0]);
		}

		if (login == null || login.length == 0 || login[0].isEmpty()) {
			this.mensagensErro.add("Login é campo obrigatório!");
		} else {
			usuario.setLogin(login[0]);
		}

		if (senha == null || senha.length == 0 || senha[0].isEmpty()) {
			this.mensagensErro.add("Senha é campo obrigatório!");
		} else {
			usuario.setSenha(senha[0]);
		}

		return this.mensagensErro.isEmpty() ? usuario : null;
	}

	public List<Usuario> consulte(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		List<Usuario> usuarios = dao.findAllFromUser(usuario);
		return usuarios;
	}

	public Usuario busque(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuario = dao.find(Integer.parseInt(id[0]));
		return usuario;
	}

	public Resultado exclua(Map<String, String[]> parameterMap) {
		String emails[] = parameterMap.get("delemails");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Resultado r = new Resultado();
		try {
			dao.beginTransaction();
			for (String email : emails) {
				Usuario u = dao.findByEmail(email);
				dao.delete(u);
			}
			dao.commit();
			r.setErro(false);
		} catch (PersistenceException e) {
			dao.rollback();
			r.setErro(true);
			r.addMensagens("Erro ao excluir usuarios");
		}
		return r;
	}
}