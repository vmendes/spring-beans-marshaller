package org.springframework.context.marshaller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import org.springframework.context.marshaller.runtime.BeanRuntimeReader;
import org.springframework.context.marshaller.runtime.SchemaBeanRuntimeReader;

public abstract class AbstractApplicationContextMarshaller<R extends Result> implements
		ApplicationContextMarshaller<R> {

	private List<Object> beans = new ArrayList<Object>();
	
	private BeanRuntimeReader beanReader;
	
	public AbstractApplicationContextMarshaller() {
		this(new SchemaBeanRuntimeReader());
	}

	public AbstractApplicationContextMarshaller(BeanRuntimeReader beanReader) {
		super();
		this.beanReader = beanReader;
	}

	public void addBean(Object obj) {
		if (obj != null) {
			beans.add(obj);
		}
	}
	
	public List<Object> getBeans() {
		return beans;
	}
	
	public BeanRuntimeReader getBeanReader() {
		return beanReader;
	}

	public void setBeanReader(BeanRuntimeReader beanReader) {
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
