package org.springframework.context.marshaller.dom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMResult;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.marshaller.AbstractApplicationContextMarshaller;
import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.dom.writer.BeanDefinitionDocumentWriter;
import org.springframework.context.marshaller.dom.writer.DefaultBeanDefinitionDocumentWriter;
import org.springframework.context.marshaller.dom.writer.DocumentWriterContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DOMApplicationContextMarshaller extends
		AbstractApplicationContextMarshaller<DOMResult> implements
		ApplicationContextMarshaller<DOMResult> {

	private BeanDefinitionDocumentWriter documentWriter;

	public DOMApplicationContextMarshaller() {
		this(new DefaultBeanDefinitionDocumentWriter());
	}

	public DOMApplicationContextMarshaller(BeanDefinitionDocumentWriter writer) {
		super();
		this.documentWriter = writer;
	}

	@Override
	protected void doMarshal(DOMResult result) {

		DocumentWriterContext context = new DocumentWriterContext();
		context.setWriter(getWriter());
		context.setBeanDefinitions(readDefinitions());

		writeDocument(result, context);

	}

	protected void writeDocument(DOMResult result, DocumentWriterContext context) {

		Node node = result.getNode();
		
		Document doc = node.getOwnerDocument();
		
		if (node instanceof Document) {
			doc = (Document) node;
		}
		
		context.getWriter().registerBeanDefinitions(doc, context);
	}

	protected List<BeanDefinition> readDefinitions() {
		List<BeanDefinition> definitions = new ArrayList<BeanDefinition>();

		List<Object> beans = getBeans();
		for (Object object : beans) {
			definitions.add(getBeanReader().read(object));
		}
		return definitions;
	}

	public BeanDefinitionDocumentWriter getWriter() {
		return documentWriter;
	}

	public void setWriter(BeanDefinitionDocumentWriter writer) {
		this.documentWriter = writer;
	}

}
