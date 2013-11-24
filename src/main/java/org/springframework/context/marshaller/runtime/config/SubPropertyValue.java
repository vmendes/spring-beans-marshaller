package org.springframework.context.marshaller.runtime.config;

import org.springframework.beans.PropertyValue;

public class SubPropertyValue extends PropertyValue {

	private static final long serialVersionUID = 1L;

	public SubPropertyValue(PropertyValue original, Object newValue) {
		super(original, newValue);
	}

	public SubPropertyValue(PropertyValue original) {
		super(original);
	}

	public SubPropertyValue(String name, Object value) {
		super(name, value);
	}

}
