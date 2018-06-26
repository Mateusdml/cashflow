package listener;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.PersistenceUtil;

@WebListener
public class CashflowContextListener implements ServletContextListener {
	private EntityManagerFactory emf;

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		emf = PersistenceUtil.createEntityManagerFactory("cashflow");
		e.getServletContext().setAttribute("emf", emf);
		System.out.println("Fabrica de EntityManagers criada e no contexto.");
		
		Properties p = new Properties();
		try {
			p.load(e.getServletContext().getResourceAsStream("/WEB-INF/comandos.properties"));
			e.getServletContext().setAttribute("comandos", p);
			System.out.println("Arquivo de comandos carregado!");		
		} catch (IOException e1) {
			System.out.println("Erro ao carregar arquivo de comandos!");
		}
	}
}