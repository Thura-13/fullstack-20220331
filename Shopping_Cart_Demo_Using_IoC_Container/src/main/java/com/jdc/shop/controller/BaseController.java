package com.jdc.shop.controller;

import org.springframework.beans.BeansException;

public interface  BaseController {
	
	<T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
