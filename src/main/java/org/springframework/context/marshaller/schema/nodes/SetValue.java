package org.springframework.context.marshaller.schema.nodes;

import java.util.List;

public class SetValue extends CollectionValue implements Value {

	public static final String ELEMENT = "set";

	public SetValue(List<Value> list) {
		super(list);
	}

	@Override
	protected String getElementType() {
		return ELEMENT;
	}

}
