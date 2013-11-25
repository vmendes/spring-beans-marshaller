package org.springframework.context.marshaller.schema.nodes;

import org.springframework.context.marshaller.schema.NodeContext;
import org.w3c.dom.Node;

public class TextValue implements Value {

	private String text;

	public TextValue(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Node visit(NodeContext context) {
		return context.getDocument().createTextNode(getText());
	}

}
