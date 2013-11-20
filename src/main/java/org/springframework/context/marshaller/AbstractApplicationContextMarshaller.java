package org.springframework.context.marshaller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import org.springframework.context.marshaller.runtime.BeanDefinitionRuntimeReader;
import org.springframework.context.marshaller.runtime.GenericBeanDefinitionRuntimeReader;

public abstract class AbstractApplicationContextMarshaller<R extends Result> implements
		ApplicationContextMarshaller<R> {

	private List<Object> beans = new ArrayList<Object>();
	
	private BeanDefinitionRuntimeReader beanReader;
	
	public AbstractApplicationContextMarshaller() {
		this(new GenericBeanDefinitionRuntimeReader());
	}

	public AbstractApplicationContextMarshaller(BeanDefinitionRuntimeReader beanReader) {
		super();
		this.beanReader = beanReader;
	}

	public void addBean(Object obj) {
		beans.add(obj);
	}
	
	public List<Object> getBeans() {
		return beans;
	}
	
	public BeanDefinitionRuntimeReader getBeanReader() {
		return beanReader;
	}

	public void setBeanReader(BeanDefinitionRuntimeReader beanReader) {
		this.beanReader = beanReader;
	}

	public void marshal(R result) {
		if (result == null) {
			throw new UnsupportedOperationException("Result not support by marshaller.");
		}
		this.doMarshal(result);
	}
	
	protected abstract void doMarshal(R result);
	
	
}
