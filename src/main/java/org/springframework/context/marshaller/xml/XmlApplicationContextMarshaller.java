package org.springframework.context.marshaller.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;

import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.ApplicationContextParser;
import org.springframework.context.marshaller.parser.DOMApplicationContextParser;
import org.springframework.context.marshaller.parser.StreamApplicationContextParser;

public class XmlApplicationContextMarshaller implements
		ApplicationContextMarshaller {

	private List<Object> beans = new ArrayList<Object>();

	public void addBean(Object obj) {
		beans.add(obj);
	}
	
	public List<Object> getBeans() {
		return beans;
	}
	
	public void marshal(Result result) {
		if (result instanceof DOMResult) {
			ApplicationContextParser<DOMResult> parser = new DOMApplicationContextParser();
			parser.parse((DOMResult) result, this);
		} else if (result instanceof StreamResult) {
			ApplicationContextParser<StreamResult> parser = new StreamApplicationContextParser();
			parser.parse((StreamResult) result, this);
		} else {
			throw new UnsupportedOperationException("Result not support by marshaller.");
		}
	}
}
