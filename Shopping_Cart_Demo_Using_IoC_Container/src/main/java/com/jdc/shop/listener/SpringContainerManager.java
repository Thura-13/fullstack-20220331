package com.jdc.shop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.GenericXmlApplicationContext;

@WebListener
public class SpringContainerManager implements ServletContextListener{

	public static final String SPRING_CONTEXT = "SPRING_CONTEXT";
	
	private GenericXmlApplicationContext springContext;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		Create IoC Container
		
		springContext = new GenericXmlApplicationContext("application.xml");
		
//		Set IoC Container To Application Scope
		
		sce.getServletContext().setAttribute(SPRING_CONTEXT, springContext);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
//		Close IoC Container
		
		if(springContext != null) {
			springContext.close();
		}
	}
}
