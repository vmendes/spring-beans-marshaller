package org.springframework.context.marshaller.parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.ApplicationContextParser;
import org.w3c.dom.Document;

public class StreamApplicationContextParser implements
		ApplicationContextParser<StreamResult> {

	public void parse(StreamResult result,
			ApplicationContextMarshaller marshaller) {
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = docBuilder.newDocument();

			DOMResult domResult = new DOMResult(document);

			DOMApplicationContextParser parser = new DOMApplicationContextParser();
			parser.parse(domResult, marshaller);

			Source xmlSource = new DOMSource(document);
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlSource, result);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
