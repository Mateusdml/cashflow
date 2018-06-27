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
	public List<Movimentacao> findAllFromUser(Usuario usuario){
		Query q = this.getEntityManager().createQuery("from Movimentacao m where m.usuario =: user");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
}