package servletPackage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebContext implements ServletContextListener {

	private ServletContext context = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		context = arg0.getServletContext();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		context = arg0.getServletContext();
		
	}
}
