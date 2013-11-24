package org.springframework.context.marshaller.runtime.config;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.PropertyValue;

public class ListPropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListPropertyValue(String name, Collection<PropertyValue> value) {
		super(name, value);
	}

	public Collection<PropertyValue> getCollection() {
		return (Collection<PropertyValue>) getValue();
	}

}
