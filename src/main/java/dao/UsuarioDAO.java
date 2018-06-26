package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {
	
	public UsuarioDAO(EntityManager em) {
		super(em);
	}
	
	public Usuario findByEmail(String email) throws DAOException {
		Query q = this.getEntityManager().createQuery("Select u from Usuario u where u.email = :email");
		q.setParameter("email", email);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
			
		} catch (NoResultException e) {
			throw new DAOException("Usuário não existe!", e);
		}
		return u;
	}

}