package org.springframework.context.marshaller.schema.nodes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.marshaller.schema.NodeContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Bean implements Value {

	public static final String ELEMENT = "bean";

	public static final String CLASS_ATTRIBUTE = "class";

	private String className = "";
	
	private List<Property> properties = new ArrayList<Property>();

	public Bean(String className) {
		super();
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public Node visit(NodeContext context) {
		Element element = context.getDocument().createElement(ELEMENT);
		element.setAttribute(CLASS_ATTRIBUTE, getClassName());
		for (Property property : getProperties()) {
			element.appendChild(property.visit(context));
		}
		
		return element;
	}
}
