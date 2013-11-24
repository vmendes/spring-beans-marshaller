package org.springframework.context.marshaller.runtime.config;

import java.util.Collection;

import org.springframework.beans.PropertyValue;

public class SetPropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetPropertyValue(String name, Collection<PropertyValue> value) {
		super(name, value);
	}

	public Collection<PropertyValue> getCollection() {
		return (Collection<PropertyValue>) getValue();
	}
	
}
