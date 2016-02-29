package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.Developer;


public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext=event.getServletContext();
		
		String name=servletContext.getInitParameter("name");
		
		String mail=servletContext.getInitParameter("mail");
		
		String company=servletContext.getInitParameter("company");
		
		String division=servletContext.getInitParameter("division");
		
		Developer developer=new Developer(name,mail,company,division);
		System.out.println("servletContextListener säger: "+developer);
		servletContext.setAttribute("developer", developer);
		

	}

}
