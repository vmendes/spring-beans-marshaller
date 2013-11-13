package org.springframework.context.marshaller.model;

import java.util.List;

public class Bean {
	private String clazz;

	private List<Property> properties;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	
	
}
