package org.springframework.context.marshaller.runtime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;

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
				values.add(field.getName(), String.valueOf(field.get(object)));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return values;
	}

}
