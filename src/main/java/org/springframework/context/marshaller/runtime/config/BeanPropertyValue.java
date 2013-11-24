package org.springframework.context.marshaller.runtime.config;

import org.springframework.beans.factory.config.BeanDefinition;

public class BeanPropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanPropertyValue(String name, BeanDefinition value) {
		super(name, value);
	}
	
	public BeanDefinition getBean() {
		return (BeanDefinition) getValue();
	}

	
}
