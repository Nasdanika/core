package org.nasdanika.common;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.springframework.util.AntPathMatcher;

public interface Input<I> {
	
	URI getURI();
	
	I openInput();

	default Input<I> mapURI(UnaryOperator<URI> mapper) {
		return new Input<I>() {
			
			@Override
			public URI getURI() {
				return mapper.apply(Input.this.getURI());
			}
			
			@Override
			public I openInput() {
				return Input.this.openInput();
			}
			
		};
	}
	
	default <P> Input<P> map(java.util.function.Function<I, P> mapper) {
		return new Input<P>() {
			
			@Override
			public URI getURI() {
				return Input.this.getURI();
			}
			
			@Override
			public P openInput() {
				I input = Input.this.openInput();
				if (input == null) {
					return null;
				}
				return mapper.apply(input);
			}
			
		};
	}
	
	default <P> Input<P> map(UnaryOperator<URI> uriMapper, java.util.function.Function<I, P> mapper) {
		return mapURI(uriMapper).map(mapper);
	}
	
	/**
	 * Returns a predicate that matches inputs whose URI matches any of the given Ant patterns.
	 */
	static <I> Predicate<Input<I>> include(String... patterns) {
		AntPathMatcher matcher = new AntPathMatcher();
		return input -> {
			String path = input.getURI().toString();
			for (String pattern : patterns) {
				if (matcher.match(pattern, path)) {
					return true;
				}
			}
			return false;
		};
	}
	
	/**
	 * Returns a predicate that rejects inputs whose URI matches any of the given Ant patterns.
	 */
	static <I> Predicate<Input<I>> exclude(String... patterns) {
		AntPathMatcher matcher = new AntPathMatcher();
		return input -> {
			String path = input.getURI().toString();
			for (String pattern : patterns) {
				if (matcher.match(pattern, path)) {
					return false;
				}
			}
			return true;
		};
	}

	/**
	 * Returns a function that returns a stream of inputs that match the given glob pattern  
	 * such as "src/resources/template/**".
	 * URI's are mapped to the path end - the ** part.
	 * @param <I>
	 * @param pattern
	 * @return
	 */
	static <I> Function<Input<I>, Stream<Input<I>>> subpath(String pattern) {
		AntPathMatcher matcher = new AntPathMatcher();
		return input -> {
			String path = input.getURI().toString();
			if (matcher.match(pattern, path)) {
				String subPath = matcher.extractPathWithinPattern(pattern, path);
				return Stream.of(input.mapURI(uri -> URI.createURI(subPath)));
			}
			return Stream.empty();
		};
	}
	
	static <I extends Input<?>> UnaryOperator<I> mapMatch(UnaryOperator<I> mapper, String... patterns) {
		AntPathMatcher matcher = new AntPathMatcher();
		return input -> {
			String path = input.getURI().toString();
			for (String pattern : patterns) {
				if (matcher.match(pattern, path)) {
					return mapper.apply(input);
				}
			}
			return input;
		};
	}
	
}