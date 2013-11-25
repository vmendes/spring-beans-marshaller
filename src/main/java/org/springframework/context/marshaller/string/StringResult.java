package org.springframework.context.marshaller.string;

import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

public class StringResult extends StreamResult {

	public StringResult() {
		super(new StringWriter());
	}

	public String getString() {
		return ((StringWriter) getWriter()).getBuffer().toString();
	}

}
