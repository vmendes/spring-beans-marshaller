package org.springframework.context.marshaller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;

import org.springframework.context.marshaller.dom.DOMApplicationContextMarshaller;
import org.springframework.context.marshaller.stream.StreamApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringApplicationContextMarshaller;
import org.springframework.context.marshaller.string.StringResult;
import org.w3c.dom.Node;

public class SpringBeansMarshaller {

	public static String resolveString(Object... objs) {
		ApplicationContextMarshaller<StringResult> mashaller = getStringMarshaller();
		for (Object object : objs) {
			mashaller.addBean(object);
		}
		StringResult result = new StringResult();
		mashaller.marshal(result);
		return result.getString();
	}
	
	public static Node resolveNode(Node node, Object... objs) {
		ApplicationContextMarshaller<DOMResult> mashaller = getDOMMarshaller();
		for (Object object : objs) {
			mashaller.addBean(object);
		}
		DOMResult result = new DOMResult(node);
		mashaller.marshal(result);
		return result.getNode();
	}
	
	public static void resolveFile(File file, Object... objs) {
		ApplicationContextMarshaller<StreamResult> mashaller = getStreamMarshaller();
		for (Object object : objs) {
			mashaller.addBean(object);
		}
		try {
			StreamResult result = new StreamResult(new FileWriter(file));
			mashaller.marshal(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static ApplicationContextMarshaller<StringResult> getStringMarshaller() {
		return new StringApplicationContextMarshaller();
	}
	public static ApplicationContextMarshaller<DOMResult> getDOMMarshaller() {
		return new DOMApplicationContextMarshaller();
	}
	public static ApplicationContextMarshaller<StreamResult> getStreamMarshaller() {
		return new StreamApplicationContextMarshaller();
	}
}
