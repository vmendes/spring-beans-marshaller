package org.springframework.context.marshaller.schema.nodes;

import java.util.Collection;

import org.springframework.context.marshaller.schema.NodeContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class CollectionValue implements Value {

	public static final String VALUE_ELEMENT = "value";

	private Collection<Value> collection;

	public CollectionValue(Collection<Value> collection) {
		super();
		this.collection = collection;
	}

	public Collection<Value> getCollection() {
		return collection;
	}

	public void setCollection(Collection<Value> collection) {
		this.collection = collection;
	}

	protected abstract String getElementType();

	@Override
	public Node visit(NodeContext context) {

		Element element = context.getDocument().createElement(getElementType());
		for (Value value : getCollection()) {
			Element valueElement = context.getDocument().createElement(
					VALUE_ELEMENT);
			valueElement.appendChild(value.visit(context));
			element.appendChild(valueElement);
		}

		return element;
	}

}
