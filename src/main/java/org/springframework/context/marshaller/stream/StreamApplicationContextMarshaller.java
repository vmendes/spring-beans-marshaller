package org.springframework.context.marshaller.stream;

import java.io.Writer;

import javax.xml.transform.stream.StreamResult;

import org.springframework.context.marshaller.ApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringResult;

public class StreamApplicationContextMarshaller implements
		ApplicationContextMarshaller<StreamResult> {

	private ApplicationContextMarshaller<StringResult> stringMarshaller;

	public StreamApplicationContextMarshaller() {
		this(new StringApplicationContextMarshaller());
	}

	public StreamApplicationContextMarshaller(
			ApplicationContextMarshaller<StringResult> innerMarshaller) {
		super();
		this.stringMarshaller = innerMarshaller;
	}

	public ApplicationContextMarshaller<StringResult> getStringMarshaller() {
		return stringMarshaller;
	}

	public void setStringMarshaller(
			ApplicationContextMarshaller<StringResult> stringMarshaller) {
		this.stringMarshaller = stringMarshaller;
	}

	public void addBean(Object obj) {
		this.stringMarshaller.addBean(obj);
	}

	public void marshal(StreamResult result) {
		
		try {
			Writer writer = result.getWriter();
			StringResult stringResult = new StringResult();
			getStringMarshaller().marshal(stringResult);
			String xml = stringResult.getString();
			writer.append(xml);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
