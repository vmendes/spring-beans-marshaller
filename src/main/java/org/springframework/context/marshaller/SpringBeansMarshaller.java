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

/**
 * Shortcut to marshal beans with default implementations provided.
 * 
 * @author valdir
 *
 */
public class SpringBeansMarshaller {

	/**
	 * Sets the current Spring version to render the namespace tags.
	 * 
	 * @param version
	 */
	public static void setSpringVersion(String version) {
		AbstractApplicationContextMarshaller.SPRING_VERSION = version;
	}
	
	/**
	 * Marshals the array of beans to a pretty printed XML string.
	 * 
	 * @param objs
	 * @return
	 */
	public static String marshalString(Object... objs) {
		ApplicationContextMarshaller<StringResult> mashaller = getStringMarshaller();
		for (Object object : objs) {
			mashaller.addBean(object);
		}
		StringResult result = new StringResult();
		mashaller.marshal(result);
		return result.getString();
	}
	
	/**
	 * Marshals the array of objects incorporating in a DOM node.
	 *  
	 * @param node
	 * @param objs
	 * @return
	 */
	public static Node marshalNode(Node node, Object... objs) {
		ApplicationContextMarshaller<DOMResult> mashaller = getDOMMarshaller();
		for (Object object : objs) {
			mashaller.addBean(object);
		}
		DOMResult result = new DOMResult(node);
		mashaller.marshal(result);
		return result.getNode();
	}
	
	/**
	 * Marshals the array of objects into a file.
	 * 
	 * @param file
	 * @param objs
	 */
	public static void marshalFile(File file, Object... objs) {
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

	/**
	 * Provides the default implementation of String marshaller.
	 * 
	 * @return
	 */
	public static ApplicationContextMarshaller<StringResult> getStringMarshaller() {
		return new StringApplicationContextMarshaller();
	}
	
	/**
	 * Provides the default implementation of DOM marshaller.
	 * 
	 * @return
	 */
	public static ApplicationContextMarshaller<DOMResult> getDOMMarshaller() {
		return new DOMApplicationContextMarshaller();
	}
	
	/**
	 * Provides the default implementation of Stream marshaller.
	 * 
	 * @return
	 */
	public static ApplicationContextMarshaller<StreamResult> getStreamMarshaller() {
		return new StreamApplicationContextMarshaller();
	}
}
