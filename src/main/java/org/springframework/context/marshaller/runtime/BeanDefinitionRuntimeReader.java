package org.springframework.context.marshaller.runtime;

import org.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRuntimeReader {

	BeanDefinition read(Object object);
}
