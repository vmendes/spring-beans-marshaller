package org.springframework.context.marshaller;

import javax.xml.transform.Result;

public interface ApplicationContextMarshaller<R extends Result> {

	void addBean(Object obj);
	
	void marshal(R result);
}
