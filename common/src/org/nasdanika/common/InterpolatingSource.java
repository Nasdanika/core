package org.nasdanika.common;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class converts value returned by the source as follows:
 * * Lists are streamed to lists with converting values as explained below.
 * * {@link Map}s are wrapped into {@link Context}s using Context.wrap receiving a new instance of {@link InterpolatingSource} constructed from the Map's get.
 * * {@link String}s are wrapped into interpolating {@link PropertyComputer}s.
 * @author Pavel
 *
 */
public class InterpolatingSource implements Function<String,Object> {

	private Function<String, Object> source;

	public InterpolatingSource(Function<String,Object> source) {
		this.source = source;
	}

	@Override
	public Object apply(String key) {
		return convert(source.apply(key));
	}
	
	/**
	 * Converts value. This implementation converts:
	 * 
	 * * {@link Map} to {@link Context}.
	 * * {@link List} to a list of converted values.
	 * * {@link String} to an interpolating {@link PropertyComputer}. 
	 * 
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Object convert(Object value) {
		if (value instanceof Map) {
			return Context.wrap(new InterpolatingSource(((Map<String,Object>) value)::get));
		}
		if (value instanceof List) {
			return ((List<Object>) value).stream().map(this::convert).collect(Collectors.toList());
		}
		if (value instanceof String) {
			return new PropertyComputer() {
				
				@Override
				public <T> T compute(Context context, String key, Class<T> type) {
					Converter converter = context.get(Converter.class);
					String interpolated = context.interpolate((String) value);
					return converter == null ? (T) interpolated : converter.convert(interpolated, type);
				}
				
			};
		}
		return value;
	}

}
