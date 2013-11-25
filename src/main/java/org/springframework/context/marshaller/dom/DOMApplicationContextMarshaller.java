package org.springframework.context.marshaller.dom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMResult;

import org.springframework.context.marshaller.AbstractApplicationContextMarshaller;
import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.schema.NodeContext;
import org.springframework.context.marshaller.schema.nodes.Bean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMApplicationContextMarshaller extends
		AbstractApplicationContextMarshaller<DOMResult> implements
		ApplicationContextMarshaller<DOMResult> {

	private static final String BEANS_ELEMENT = "beans";

	private static final String BEANS_NAMESPACE = "http://www.springframework.org/schema/beans";

	private static final String COMMON_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";

	private static final String COMMON_LOCATION = "xsi:schemaLocation";

	private static final String BEANS_LOCATION = "http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-"
			+ SPRING_VERSION + ".xsd";

	@Override
	protected void doMarshal(DOMResult result) {

		Node node = result.getNode();
		Document doc = node.getOwnerDocument();
		if (node instanceof Document) {
			doc = (Document) node;
		}
		final Document d = doc;

		NodeContext context = new NodeContext() {

			@Override
			public Document getDocument() {
				return d;
			}
		};

		writeDocument(context);

	}

	protected void writeDocument(NodeContext context) {
		Element root = createRootElement(context.getDocument());
		List<Bean> readBeans = readBeans();
		for (Bean bean : readBeans) {
			root.appendChild(bean.visit(context));
		}

	}

	public Element createRootElement(Document doc) {
		Element beansElement = doc.createElementNS(BEANS_NAMESPACE,
				BEANS_ELEMENT);
		beansElement.setAttributeNS(COMMON_NAMESPACE, COMMON_LOCATION,
				BEANS_LOCATION);
		doc.appendChild(beansElement);
		return beansElement;
	}

	protected List<Bean> readBeans() {
		List<Bean> definitions = new ArrayList<Bean>();

		List<Object> beans = getBeans();
		for (Object object : beans) {
			definitions.add(getBeanReader().read(object));
		}
		return definitions;
	}

}
