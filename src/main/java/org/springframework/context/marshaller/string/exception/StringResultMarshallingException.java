package org.springframework.context.marshaller.string.exception;

public class StringResultMarshallingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StringResultMarshallingException() {
		super();
	}

	public StringResultMarshallingException(String message, Throwable cause) {
		super(message, cause);
	}

	public StringResultMarshallingException(String message) {
		super(message);
	}

	public StringResultMarshallingException(Throwable cause) {
		super(cause);
	}

	
}
