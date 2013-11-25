package org.springframework.context.marshaller.runtime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.context.marshaller.schema.nodes.ArrayValue;
import org.springframework.context.marshaller.schema.nodes.Bean;
import org.springframework.context.marshaller.schema.nodes.CollectionValue;
import org.springframework.context.marshaller.schema.nodes.ListValue;
import org.springframework.context.marshaller.schema.nodes.MapValue;
import org.springframework.context.marshaller.schema.nodes.MapValue.MapEntry;
import org.springframework.context.marshaller.schema.nodes.NullValue;
import org.springframework.context.marshaller.schema.nodes.Property;
import org.springframework.context.marshaller.schema.nodes.SetValue;
import org.springframework.context.marshaller.schema.nodes.SubProperty;
import org.springframework.context.marshaller.schema.nodes.TextValue;
import org.springframework.context.marshaller.schema.nodes.Value;

public class SchemaBeanRuntimeReader implements
		BeanRuntimeReader {

	public Bean read(Object object) {
		return readBean(object);
	}
	
	public Bean readBean(Object object) {
		
		Bean bean = new Bean(object.getClass().getName());
		bean.setProperties(readFields(object));
		
		return bean;
	}

	private List<Property> readFields(Object object) {

		List<Property> values = new ArrayList<Property>();
		Class<?> clazz = object.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				field.setAccessible(true);
				Property property = readProperty(field.getName(), field.get(object));
				if (property != null) {
					values.add(property);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return values;
	}

	private Property readProperty(String name, Object value) {
		Property property;

		if (value == null) {
			property = new SubProperty(name, new NullValue());
		} else if (isPrimitiveWrapper(value) || isEnum(value)) {
			property = new Property(name, String.valueOf(value));
		} else if (isSubproperty(value)) {
			property = new SubProperty(name, readValue(value));
		} else {
			property = null;
		}
		return property;
	}
	
	private Value readValue(Object value) {
		
		if (value == null) {
			return new NullValue();
		} else if (isText(value)) {
			return new TextValue(String.valueOf(value));
		} else if (isCollection(value)) {
			if (value instanceof Set) {
				return readSetValue(value);
			} else {
				return readListValue(value);
			}
		} else if (isArray(value)) {
			return readArrayValue(value);
		} else if (isMap(value)) {
			return readMapValue(value);
		} else {
			return readBean(value);
		}
	}

	private CollectionValue readListValue(Object value) {
		List<Value> properties = new ArrayList<Value>();
		for (Object obj : (Collection<?>) value) {
			properties.add(readValue(obj));
		}
		return new ListValue(properties);
	}
	
	private CollectionValue readArrayValue(Object value) {
		List<Value> properties = new ArrayList<Value>();
		Collection<Object> col = Arrays.asList((Object[]) value);
		for (Object obj : col) {
			properties.add(readValue(obj));
		}
		return new ArrayValue(properties);
	}

	private CollectionValue readSetValue(Object value) {
		List<Value> properties = new ArrayList<Value>();
		for (Object obj : (Collection<?>) value) {
			properties.add(readValue(obj));
		}
		return new SetValue(properties);
	}


	private MapValue readMapValue(Object value) {
		List<MapEntry> properties = new ArrayList<MapEntry>();
		for (Map.Entry<?, ?> obj : ((Map<?, ?>) value).entrySet()) {
			Object key = obj.getKey();
			Object v = obj.getValue();
			properties.add(new MapEntry(readValue(key), readValue(v)));
		}
		return new MapValue(properties);
	}

	private boolean isText(Object value) {
		return value instanceof String;
	}

	public boolean isSubproperty(Object value) {
		return isCollection(value) || isMap(value) || isArray(value)
				|| (!isPrimitiveWrapper(value) && !isDate(value));
	}

	public boolean isDate(Object value) {
		return value instanceof Date;
	}

	public boolean isPrimitiveWrapper(Object value) {
		return ClassUtils.isPrimitiveOrWrapper(value.getClass());
	}

	public boolean isEnum(Object value) {
		return value.getClass().isEnum();
	}

	public boolean isCollection(Object value) {
		return value instanceof Collection<?>;
	}

	public boolean isMap(Object value) {
		return value instanceof Map;
	}

	public boolean isArray(Object value) {
		return value.getClass().isArray();
	}

}
