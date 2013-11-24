package org.springframework.context.marshaller.runtime.config;

import org.springframework.beans.PropertyValue;


public class ValuePropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValuePropertyValue(PropertyValue original) {
		super((String)null, original);
	}

	public PropertyValue getOriginal() {
		return (PropertyValue) getValue();
	}
	
}
