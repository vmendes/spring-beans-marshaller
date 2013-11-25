package org.springframework.context.marshaller.runtime;

import org.springframework.context.marshaller.schema.nodes.Bean;

public interface BeanRuntimeReader {

	Bean read(Object object);
}
