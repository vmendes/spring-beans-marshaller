package org.springframework.context.marshaller.parser;

import java.util.List;

import javax.xml.transform.dom.DOMResult;

import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.ApplicationContextParser;
import org.springframework.context.marshaller.model.Bean;
import org.springframework.context.marshaller.model.Property;
import org.springframework.context.marshaller.reader.BeanStateReader;
import org.springframework.context.marshaller.reader.SpringBeanStateReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMApplicationContextParser implements
		ApplicationContextParser<DOMResult> {

	private BeanStateReader reader;

	public DOMApplicationContextParser() {
		this(new SpringBeanStateReader());
	}

	public DOMApplicationContextParser(BeanStateReader reader) {
		super();
		this.reader = reader;
	}

	public BeanStateReader getReader() {
		return reader;
	}

	public void setReader(BeanStateReader reader) {
		this.reader = reader;
	}

	public void parse(DOMResult result, ApplicationContextMarshaller marshaller) {

		Document doc = result.getNode().getOwnerDocument();
		
		Element root = createRoot(doc);

		List<Object> beans = marshaller.getBeans();
		for (Object object : beans) {
			Bean bean = reader.read(object);
			Element element = buildBean(doc, bean);
			root.appendChild(element);
		}
	}
	
	public Element createRoot(Document doc) {
		return null;
	}

	public Element buildBean(Document doc, Bean bean) {
		
		Element beanElement = doc.createElement("bean");
        beanElement.setAttribute("class", bean.getClazz());
        
        List<Property> properties = bean.getProperties();
        
        for (Property property : properties) {
        	Element propertyElement = null;
			beanElement.appendChild(propertyElement);
		}

		return beanElement;
	}

}
