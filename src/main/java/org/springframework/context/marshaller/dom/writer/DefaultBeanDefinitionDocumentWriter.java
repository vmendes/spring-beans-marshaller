package org.springframework.context.marshaller.dom.writer;

import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.*;

import java.util.List;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DefaultBeanDefinitionDocumentWriter implements BeanDefinitionDocumentWriter {
	
	public static final String BEANS_ELEMENT = BEAN_ELEMENT + "s";

	public void registerBeanDefinitions(Document doc,
			DocumentWriterContext writerContext) throws BeanDefinitionStoreException {
		
		List<BeanDefinition> beans = writerContext.getBeanDefinitions();
		
		Element root = createRootElement(doc);
		
		for (BeanDefinition def : beans) {
			Element element = buildBeanElement(doc, def);
			root.appendChild(element);
		}
		
	}
	
	public Element createRootElement(Document doc) {
		Element beansElement = doc.createElement(BEANS_ELEMENT);
		doc.appendChild(beansElement);
		return beansElement;
	}
	
	public Element buildBeanElement(Document doc, BeanDefinition def) {

		Element beanElement = doc.createElement(BEAN_ELEMENT);
		beanElement.setAttribute(CLASS_ATTRIBUTE, def.getBeanClassName());
		
		buildPropertiesElement(doc, def.getPropertyValues(), beanElement);
		
		return beanElement;
	}

	public void buildPropertiesElement(Document doc, PropertyValues properties, Element beanElement) {

		if (properties != null && !properties.isEmpty()) {
			PropertyValue[] propertyValues = properties.getPropertyValues();
			for (PropertyValue property : propertyValues) {
				beanElement.appendChild(buildPropertyElement(doc, property));
			}
		}
	}
	
	public Element buildPropertyElement(Document doc, PropertyValue property) {

		Element propElement = doc.createElement(PROPERTY_ELEMENT);
		propElement.setAttribute(NAME_ATTRIBUTE, property.getName());
		if (property.getValue() != null) {
			propElement.setAttribute(VALUE_ATTRIBUTE, String.valueOf(property.getValue()));
		}
		
		return propElement;
	}
}
