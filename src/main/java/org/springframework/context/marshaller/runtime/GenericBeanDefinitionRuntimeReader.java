package org.springframework.context.marshaller.runtime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.marshaller.runtime.config.BeanPropertyValue;
import org.springframework.context.marshaller.runtime.config.ListPropertyValue;
import org.springframework.context.marshaller.runtime.config.MapPropertyValue;
import org.springframework.context.marshaller.runtime.config.MapPropertyValue.EntryProperty;
import org.springframework.context.marshaller.runtime.config.NullPropertyValue;
import org.springframework.context.marshaller.runtime.config.PrimitivePropertyValue;
import org.springframework.context.marshaller.runtime.config.SetPropertyValue;
import org.springframework.context.marshaller.runtime.config.TextPropertyValue;
import org.springframework.context.marshaller.runtime.config.ValuePropertyValue;
import org.springframework.util.ClassUtils;

public class GenericBeanDefinitionRuntimeReader implements
		BeanDefinitionRuntimeReader {

	public BeanDefinition read(Object object) {

		GenericBeanDefinition def = new GenericBeanDefinition();
		def.setBeanClass(object.getClass());

		def.setPropertyValues(readFields(object));

		return def;
	}

	private MutablePropertyValues readFields(Object object) {

		MutablePropertyValues values = new MutablePropertyValues();
		Class<?> clazz = object.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				field.setAccessible(true);
				values.addPropertyValue(readPropertyValue(field.getName(),
						field.get(object)));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return values;
	}

	private PropertyValue readPropertyValue(String name, Object value) {
		if (value == null) {
			return new NullPropertyValue(name);
		} else if (isText(value)) {
			return new TextPropertyValue(name, (String) value);
		} else if (isPrimitiveWrapper(value) || isEnum(value)) {
			return new PrimitivePropertyValue(name, value);
		} else if (isSubproperty(value)) {
			if (isCollection(value)) {
				if (value instanceof Set) {
					return readSetProperty(name, value);
				} else {
					return readListProperty(name, value);
				}
			} else if (isMap(value)) {
				return readMapProperty(name, value);
			} else if (isArray(value)) {
				return readListProperty(name, Arrays.asList((Object[])value));
			} else {
				BeanDefinition read = read(value);
				return new BeanPropertyValue(name, read);
			}
		}
		return new TextPropertyValue(name, String.valueOf(value));
	}

	private ListPropertyValue readListProperty(String name, Object value) {
		List<PropertyValue> properties = new ArrayList<PropertyValue>();
		for (Object obj : (Collection) value) {
			properties.add(new ValuePropertyValue(readObject(obj)));
		}
		return new ListPropertyValue(name, properties);
	}

	private SetPropertyValue readSetProperty(String name, Object value) {
		List<PropertyValue> properties = new ArrayList<PropertyValue>();
		for (Object obj : (Collection) value) {
			properties.add(new ValuePropertyValue(readObject(obj)));
		}
		return new SetPropertyValue(name, properties);
	}

	private PropertyValue readObject(Object obj) {
		return readPropertyValue(null, obj);
	}

	private MapPropertyValue readMapProperty(String name, Object value) {
		List<EntryProperty> properties = new ArrayList<EntryProperty>();
		for (Map.Entry<?, ?> obj : ((Map<?, ?>) value).entrySet()) {
			Object key = obj.getKey();
			Object v = obj.getValue();
			properties.add(new EntryProperty(readObject(key), readObject(v)));
		}
		return new MapPropertyValue(name, properties);
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
