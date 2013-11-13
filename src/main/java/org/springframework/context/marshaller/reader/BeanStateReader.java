package org.springframework.context.marshaller.reader;

import org.springframework.context.marshaller.model.Bean;

public interface BeanStateReader {

	Bean read(Object object);
}
