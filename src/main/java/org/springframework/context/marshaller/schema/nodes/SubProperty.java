package org.springframework.context.marshaller.schema.nodes;

import org.springframework.context.marshaller.schema.NodeContext;
import org.w3c.dom.Node;

public class SubProperty extends Property {

	private Value propertyValue;

	public SubProperty(String name, Value propertyValue) {
		super(name, null);
		this.propertyValue = propertyValue;
	}

	public Value getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Value propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public Node visit(NodeContext context) {
		Node node = super.visit(context);
		node.appendChild(getPropertyValue().visit(context));
		return node;
	}

}
