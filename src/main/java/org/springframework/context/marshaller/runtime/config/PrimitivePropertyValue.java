package org.springframework.context.marshaller.runtime.config;

import org.springframework.beans.PropertyValue;

public class PrimitivePropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrimitivePropertyValue(PropertyValue original, Object newValue) {
		super(original, newValue);
	}

	public PrimitivePropertyValue(PropertyValue original) {
		super(original);
	}

	public PrimitivePropertyValue(String name, Object value) {
		super(name, value);
	}

	
}
