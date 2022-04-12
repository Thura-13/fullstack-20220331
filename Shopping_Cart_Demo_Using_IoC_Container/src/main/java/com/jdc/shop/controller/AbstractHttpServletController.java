package com.jdc.shop.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.jdc.shop.listener.SpringContainerManager;

public class AbstractHttpServletController extends HttpServlet implements BaseController{

	private static final long serialVersionUID = 1L;

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		
		var context =getServletContext().getAttribute(SpringContainerManager.SPRING_CONTEXT);
		if(context != null && context instanceof BeanFactory factory) {
			return factory.getBean(name, requiredType);
		}
		return null;
	}

}
