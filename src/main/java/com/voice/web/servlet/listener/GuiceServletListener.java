package com.voice.web.servlet.listener;

import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.squarespace.jersey2.guice.JerseyGuiceServletContextListener;
import com.voice.module.WebModule;

public class GuiceServletListener extends JerseyGuiceServletContextListener {

	public static final String INJETOR_KEY = Injector.class.getName();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext sc = servletContextEvent.getServletContext();
		sc.setAttribute(INJETOR_KEY, getInjector());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext sc = servletContextEvent.getServletContext();
		sc.removeAttribute(INJETOR_KEY);
		
		super.contextDestroyed(servletContextEvent);
	}

	@Override
	protected List<? extends Module> modules() {
		return Collections.singletonList(new WebModule());
	}
}
