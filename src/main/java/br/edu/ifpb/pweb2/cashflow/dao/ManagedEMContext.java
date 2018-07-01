package br.edu.ifpb.pweb2.cashflow.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

public class ManagedEMContext {

	@SuppressWarnings("rawtypes")
	private static final ThreadLocal context = new ThreadLocal();
	private final EntityManagerFactory factory;
	private static Logger logger = Logger.getLogger(ManagedEMContext.class);

	public ManagedEMContext(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public EntityManager currentEntityManager() {
		EntityManager current = existingEntityManager(factory);
		if ( current == null ) {
			throw new HibernateException( "No entity manager currently bound to execution context" );
		}
		return current;
	}

	public static boolean hasBind(EntityManagerFactory factory) {
		return existingEntityManager( factory ) != null;
	}

	@SuppressWarnings("unchecked")
	public static EntityManager bind(EntityManagerFactory emf, EntityManager em) {
		return ( EntityManager ) entityManagerMap( true ).put( emf, em );
	}

	@SuppressWarnings("rawtypes")
	public static EntityManager unbind(EntityManagerFactory factory) {
		EntityManager existing = null;
		Map emMap = entityManagerMap();
		if ( emMap != null ) {
			existing = ( EntityManager ) emMap.remove( factory );
			doCleanup();
		}
		return existing;
	}

	@SuppressWarnings("rawtypes")
	private static EntityManager existingEntityManager(EntityManagerFactory factory) {
		Map emMap = entityManagerMap();
		if ( emMap == null ) {
			return null;
		}
		else {
			return ( EntityManager ) emMap.get( factory );
		}
	}

	@SuppressWarnings("rawtypes")
	protected static Map entityManagerMap() {
		return entityManagerMap( false );
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static synchronized Map entityManagerMap(boolean createMap) {
		Map emMap = ( Map ) context.get();
		logger.debug("Mapa na ThreadLocal: " + emMap);
		if ( emMap == null && createMap ) {
			emMap = new HashMap();
			context.set( emMap );
			logger.debug("Criou mapa no ThreadLocal");
		}
		return emMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static synchronized void doCleanup() {
		Map emMap = entityManagerMap( false );
		if ( emMap != null ) {
			if ( emMap.isEmpty() ) {
				context.set( null );
			}
		}
	}
}