package br.edu.ifpb.pweb2.cashflow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifpb.pweb2.cashflow.dao.MovimentacaoDAO;
import br.edu.ifpb.pweb2.cashflow.model.Movimentacao;

public class MovimentacaoController {
	private List<String> mensagensErro;
	private EntityManager entityManager;

	public MovimentacaoController(EntityManager em) {
		this.entityManager = em;
	}
	public Resultado cadastre(Map<String, String[]> parametros ) {
		Resultado resultado = new Resultado();
		Movimentacao movimentacao = null;
		
		if ((movimentacao = fromParametros(parametros)) != null) {
			MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
			dao.beginTransaction();
			
			System.out.println("Antes do INSERT no MovimentacaoController!");
			dao.insert(movimentacao);
			
			System.out.println("Movimentacao depois da persistencia no MovimentacaoController: " +movimentacao);
			dao.commit();
			System.out.println("Depois do commit.");
			resultado.setErro(false);
			String m = "Movimentacao salvo com sucesso!";
			resultado.addMensagens(m);
		} else {
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		System.out.println("Resultado: " +resultado);
		return resultado;
	}
	
	private Movimentacao fromParametros(Map<String, String[]> parametros) {
		String[] descricao = parametros.get("descricao");
		String[] valor = parametros.get("valor");
		String[] operacao = parametros.get("operacao");
		
		Movimentacao movimentacao = new Movimentacao();
		this.mensagensErro = new ArrayList<String>();

		if (descricao == null || descricao.length == 0 || descricao[0].isEmpty()) {
			this.mensagensErro.add("Descricao é um campo obrigatório!");
		} else {
			movimentacao.setDescricao(descricao[0]);
		}

		if (valor == null || valor.length == 0 || valor[0].isEmpty()) {
			this.mensagensErro.add("Valor é um campo obrigatório!");
		} else {
			movimentacao.setValor(Double.parseDouble(valor[0]));
		}

		if (operacao == null || operacao.length == 0 || operacao[0].isEmpty()) {
			this.mensagensErro.add("Operacao é um campo obrigatório!");
		} else {
			if(operacao[0]=="true") {
				movimentacao.setOperacao(true);
			}else {movimentacao.setOperacao(false);}
		}

		return this.mensagensErro.isEmpty() ? movimentacao : null;
	}
	
	public List<Movimentacao> consulte(Movimentacao movimentacao) {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		List<Movimentacao> movimentacaos = dao.findAllFromMovimentacao(movimentacao);
		return movimentacaos;
	}

	public Movimentacao busque(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		Movimentacao movimentacao = dao.find(Integer.parseInt(id[0]));
		return movimentacao;
	}

	public Resultado exclua(Map<String, String[]> parameterMap) {
		String ids[] = parameterMap.get("delids");
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		Resultado r = new Resultado();
		try {
			dao.beginTransaction();
			for (String id : ids) {
				Movimentacao u = dao.find(Integer.parseInt(id));
				dao.delete(u);
			}
			dao.commit();
			r.setErro(false);
		} catch (PersistenceException e) {
			dao.rollback();
			r.setErro(true);
			r.addMensagens("Erro ao excluir movimentacoes");
		}
		return r;
	}
	
}
