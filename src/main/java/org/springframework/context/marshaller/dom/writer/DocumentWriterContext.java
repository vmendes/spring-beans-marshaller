package org.springframework.context.marshaller.dom.writer;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;

public class DocumentWriterContext {

	private BeanDefinitionDocumentWriter writer;

	private List<BeanDefinition> beanDefinitions;

	public BeanDefinitionDocumentWriter getWriter() {
		return writer;
	}

	public void setWriter(BeanDefinitionDocumentWriter writer) {
		this.writer = writer;
	}

	public List<BeanDefinition> getBeanDefinitions() {
		return beanDefinitions;
	}

	public void setBeanDefinitions(List<BeanDefinition> beanDefinitions) {
		this.beanDefinitions = beanDefinitions;
	}

}
