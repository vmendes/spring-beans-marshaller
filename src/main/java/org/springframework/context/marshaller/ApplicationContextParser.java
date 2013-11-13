package org.springframework.context.marshaller;

import javax.xml.transform.Result;

public interface ApplicationContextParser<R extends Result> {

	void parse(R result, ApplicationContextMarshaller marshaller);
}
