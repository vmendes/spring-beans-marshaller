package org.springframework.context.marshaller.runtime.config;


public class TextPropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextPropertyValue(String name, String value) {
		super(name, value);
	}

	public String getText() {
		return (String) getValue();
	}
	
}
