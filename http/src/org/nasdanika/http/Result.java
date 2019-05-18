package org.nasdanika.http;

/**
 * Result of request processing
 * @author Pavel
 *
 */
public interface Result extends AutoCloseable {
	
	Result NOP = wrap(null);
	
	Result NOT_FOUND = wrap(ProcessingError.NOT_FOUND);
	
	Result BAD_REQUEST = wrap(ProcessingError.BAD_REQUEST);
	
	Result FORBIDDEN = wrap(ProcessingError.FORBIDDEN);
	
	Result INTERNAL_SERVER_ERROR = wrap(ProcessingError.INTERNAL_SERVER_ERROR);
	
	Result SERVICE_UNAVAILABLE = wrap(ProcessingError.SERVICE_UNAVAILABLE);
	
	Result UNAUTHORIZED = wrap(ProcessingError.UNAUTHORIZED);
	
	Object getValue();
		
	/**
	 * Value content type.
	 * @return
	 */
	default String getContentType() {
		return null;
	};

	static Result wrap(Object value) {
		return wrap(value, null);
	}	
	
	static Result wrap(Object value, String contentType) {
		return new Result() {

			@Override
			public Object getValue() {
				return value;
			}
			
			@Override
			public String getContentType() {
				return contentType;
			}
			
		};
	}
	
	@Override
	default void close() throws Exception {
		// NOP		
	}

}
