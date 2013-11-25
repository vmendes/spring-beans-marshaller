package org.springframework.context.marshaller.schema.nodes;

import java.util.List;

public class ArrayValue extends CollectionValue implements Value {

	public static final String ELEMENT = "array";
	
	public ArrayValue(List<Value> list) {
		super(list);
	}

	@Override
	protected String getElementType() {
		return ELEMENT;
	}

	
}
