package org.springframework.context.marshaller.string;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.dom.DOMApplicationContextMarshaller;
import org.springframework.context.marshaller.string.exception.StringResultMarshallingException;
import org.w3c.dom.Document;

public class StringApplicationContextMarshaller implements
		ApplicationContextMarshaller<StringResult> {

	private ApplicationContextMarshaller<DOMResult> domMarshaller;

	public StringApplicationContextMarshaller() {
		this(new DOMApplicationContextMarshaller());
	}

	public StringApplicationContextMarshaller(
			ApplicationContextMarshaller<DOMResult> innerMarshaller) {
		super();
		this.domMarshaller = innerMarshaller;
	}

	public ApplicationContextMarshaller<DOMResult> getDomMarshaller() {
		return domMarshaller;
	}

	public void setDomMarshaller(
			ApplicationContextMarshaller<DOMResult> domMarshaller) {
		this.domMarshaller = domMarshaller;
	}

	public void addBean(Object obj) {
		domMarshaller.addBean(obj);
	}

	public void marshal(StringResult result) {
		Document doc = createDocument();
		DOMResult domResult = new DOMResult(doc);
		getDomMarshaller().marshal(domResult);
		transformXml(doc, result);
	}

	protected void transformXml(Document doc, StringResult result) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			tf.setAttribute("indent-number", new Integer(3));
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), result);
		} catch (Exception e) {
			throw new StringResultMarshallingException(e);
		}
	}

	private Document createDocument() {
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			return doc;
		} catch (ParserConfigurationException e) {
			throw new StringResultMarshallingException(e);
		}
	}

}
