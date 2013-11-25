package org.springframework.context.marshaller.schema;

import org.w3c.dom.Node;

public interface NodeVisitor {

	Node visit(NodeContext context);
}
