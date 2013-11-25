package org.springframework.context.marshaller.schema.nodes;

import org.springframework.context.marshaller.schema.NodeContext;
import org.springframework.context.marshaller.schema.NodeVisitor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Property implements NodeVisitor {

	public static final String ELEMENT = "property";

	public static final String NAME_ATTRIBUTE = "name";

	public static final String VALUE_ATTRIBUTE = "value";

	private String name;

	private String value;
	
	public Property(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node visit(NodeContext context) {
		Element element = context.getDocument().createElement(ELEMENT);
		element.setAttribute(NAME_ATTRIBUTE, getName());
		if (getValue() != null) {
			element.setAttribute(VALUE_ATTRIBUTE, getValue());
		}
		return element;
	}

}
