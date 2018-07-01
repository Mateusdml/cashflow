package br.edu.ifpb.pweb2.cashflow.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.pweb2.cashflow.model.Movimentacao;
import br.edu.ifpb.pweb2.cashflow.model.Usuario;

public class MovimentacaoDAO extends GenericDAO<Movimentacao, Integer> {
	
	public MovimentacaoDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimentacao> findAllFromMovimentacao(Movimentacao movimentacao){
		Query q = this.getEntityManager().createQuery("from Movimentacao m where m.id =: id");
		q.setParameter("movimentacao", movimentacao);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllFromUser(Usuario usuario) {
		Query q = this.getEntityManager().createQuery("from Usuario u where u.usuario = :user");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
}