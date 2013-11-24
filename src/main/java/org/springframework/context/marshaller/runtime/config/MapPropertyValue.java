package org.springframework.context.marshaller.runtime.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyValue;

public class MapPropertyValue extends SubPropertyValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MapPropertyValue(String name, List<EntryProperty> value) {
		super(name, value);
	}

	public List<EntryProperty> getMapList() {
		return (List<EntryProperty>) getValue();
	}

	public static class EntryProperty {
		private PropertyValue key;
		private PropertyValue value;

		public EntryProperty(PropertyValue key, PropertyValue value) {
			super();
			this.key = key;
			this.value = value;
		}

		public PropertyValue getKey() {
			return key;
		}

		public void setKey(PropertyValue key) {
			this.key = key;
		}

		public PropertyValue getValue() {
			return value;
		}

		public void setValue(PropertyValue value) {
			this.value = value;
		}

	}
}
