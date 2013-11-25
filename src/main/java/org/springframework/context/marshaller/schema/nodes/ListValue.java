package org.springframework.context.marshaller.schema.nodes;

import java.util.Collection;

public class ListValue extends CollectionValue implements Value {

	public static final String ELEMENT = "list";

	public ListValue(Collection<Value> collection) {
		super(collection);
	}

	@Override
	protected String getElementType() {
		return ELEMENT;
	}

}
