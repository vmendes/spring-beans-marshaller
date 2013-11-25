package org.springframework.context.marshaller;

import javax.xml.transform.Result;

/**
 * Generates the Spring application context based on the objects runtime state.
 *  
 * @author valdir
 *
 * @param <R>
 */
public interface ApplicationContextMarshaller<R extends Result> {

	/**
	 * Add bean for marshalling. 
	 * 
	 * @param obj
	 */
	void addBean(Object obj);
	
	/**
	 * Creates an application contest based on the added beans.
	 * After marshalling, all the added beans should be removed from marshaller queue.
	 * 
	 * @param result The context to write into.
	 */
	void marshal(R result);
}
