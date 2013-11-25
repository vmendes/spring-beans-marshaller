package org.springframework.context.marshaller.schema.nodes;

import org.springframework.context.marshaller.schema.NodeContext;
import org.w3c.dom.Node;

public class NullValue implements Value {

	public static final String ELEMENT = "null";

	@Override
	public Node visit(NodeContext context) {
		return context.getDocument().createElement(ELEMENT);
	}

}
