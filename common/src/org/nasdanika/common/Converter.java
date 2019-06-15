package org.nasdanika.common;

/**
 * Converts object to target type.
 * @author Pavel
 *
 * @param <S> Source type
 * @param <T> Target type
 * @param <C> Context type
 */
public interface Converter {
	
	<T> T convert(Object source, Class<T> type);
	
}
