package org.nasdanika.common;

/**
 * Converts object to target type.
 * @author Pavel
 *
 * @param <S> Source type
 * @param <T> Target type
 * @param <C> Context type
 */
public interface Converter extends Composeable<Converter> {
	
	<T> T convert(Object source, Class<T> type);
	
	default Converter compose(Converter other) {
		if (other == null) {
			return this;
		}
		
		return new Converter() {
			
			@Override
			public <T> T convert(Object source, Class<T> type) {
				T ret = Converter.this.convert(source, type);
				return ret == null ? other.convert(source, type) : ret;
			}
		};
	}
	
}
