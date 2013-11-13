package org.springframework.context.marshaller;

import java.util.List;

import javax.xml.transform.Result;

public interface ApplicationContextMarshaller {

	void addBean(Object obj);
	
	List<Object> getBeans();
	
	void marshal(Result result);
}
