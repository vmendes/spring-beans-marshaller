package org.springframework.context.marshaller.dom.writer;

import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.BEAN_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.CLASS_ATTRIBUTE;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.ENTRY_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.KEY_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.LIST_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.MAP_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.NAME_ATTRIBUTE;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.PROPERTY_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.SET_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.VALUE_ELEMENT;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.marshaller.runtime.config.BeanPropertyValue;
import org.springframework.context.marshaller.runtime.config.ListPropertyValue;
import org.springframework.context.marshaller.runtime.config.MapPropertyValue;
import org.springframework.context.marshaller.runtime.config.MapPropertyValue.EntryProperty;
import org.springframework.context.marshaller.runtime.config.NullPropertyValue;
import org.springframework.context.marshaller.runtime.config.PrimitivePropertyValue;
import org.springframework.context.marshaller.runtime.config.SetPropertyValue;
import org.springframework.context.marshaller.runtime.config.SubPropertyValue;
import org.springframework.context.marshaller.runtime.config.TextPropertyValue;
import org.springframework.context.marshaller.runtime.config.ValuePropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefaultBeanDefinitionDocumentWriter implements
		BeanDefinitionDocumentWriter {

	public static final String BEANS_ELEMENT = BEAN_ELEMENT + "s";

	public void registerBeanDefinitions(Document doc,
			DocumentWriterContext writerContext)
			throws BeanDefinitionStoreException {

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

	public void buildPropertiesElement(Document doc, PropertyValues properties,
			Element beanElement) {

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

		appendChild(doc, propElement, property);
		return propElement;
	}

	private void appendChild(Document doc, Element element,
			PropertyValue property) {

		if (property instanceof NullPropertyValue) {
			// nothing
		} else if (property instanceof PrimitivePropertyValue) {
			element.setTextContent(String.valueOf(property.getValue()));
		} else if (property instanceof TextPropertyValue) {
			element.setTextContent(((TextPropertyValue) property).getText());
		} else if (property instanceof SubPropertyValue) {
			element.appendChild(buildSubPropertyElement(doc,
					(SubPropertyValue) property));
		}
	}

	private Element buildSubPropertyElement(Document doc, PropertyValue property) {

		if (property instanceof ListPropertyValue) {
			return buildListPropertyElement(doc, (ListPropertyValue) property);
		} else if (property instanceof SetPropertyValue) {
			return buildSetPropertyElement(doc, (SetPropertyValue) property);
		} else if (property instanceof MapPropertyValue) {
			return buildMapPropertyElement(doc, (MapPropertyValue) property);
		} else if (property instanceof BeanPropertyValue) {
			return buildBeanElement(doc,
					((BeanPropertyValue) property).getBean());
		} else if (property instanceof ValuePropertyValue) {
			return buildValuePropertyElement(doc, (ValuePropertyValue) property);
		}
		return null;
	}

	private Element buildSetPropertyElement(Document doc, SetPropertyValue value) {

		Element element = doc.createElement(SET_ELEMENT);

		Collection<PropertyValue> values = value.getCollection();
		for (PropertyValue object : values) {
			element.appendChild(buildSubPropertyElement(doc,
					(SubPropertyValue) object));
		}

		return element;
	}

	private Element buildListPropertyElement(Document doc,
			ListPropertyValue value) {

		Element element = doc.createElement(LIST_ELEMENT);

		Collection<PropertyValue> values = value.getCollection();
		for (PropertyValue object : values) {
			element.appendChild(buildSubPropertyElement(doc,
					(SubPropertyValue) object));
		}

		return element;
	}

	private Element buildMapPropertyElement(Document doc, MapPropertyValue value) {
		Element element = doc.createElement(MAP_ELEMENT);
		List<EntryProperty> mapList = value.getMapList();
		for (EntryProperty entryList : mapList) {
			Element entry = doc.createElement(ENTRY_ELEMENT);

			Element keyElement = doc.createElement(KEY_ELEMENT);
			entry.appendChild(keyElement);

			Element valueElement = doc.createElement(VALUE_ELEMENT);
			entry.appendChild(valueElement);

			appendChild(doc, keyElement, entryList.getKey());
			appendChild(doc, valueElement, entryList.getValue());

			element.appendChild(entry);
		}

		return element;
	}

	private Element buildValuePropertyElement(Document doc,
			ValuePropertyValue value) {
		Element element = doc.createElement(VALUE_ELEMENT);
		appendChild(doc, element, value.getOriginal());
		return element;
	}

}
