package org.nasdanika.common;

import java.io.IOException;
import java.util.function.UnaryOperator;

import org.eclipse.emf.common.util.URI;

public interface Output<O> {
	
	static <O> Output<O> empty() {
		return new Output<O>() {
		
			@Override
			public O openOutput(URI uri) throws IOException {
				return null;
			}
			
		};
	}
	
	O openOutput(URI uri) throws IOException;

	default Output<O> mapURI(UnaryOperator<URI> mapper) {
		return new Output<O>() {
			
			@Override
			public O openOutput(URI uri) throws IOException {
				return Output.this.openOutput(mapper.apply(uri));
			}
			
		};
	}	
	
	default Output<O> base(URI base) {
		return mapURI(uri -> base == null ? uri : uri.resolve(base));
	}
	
	default <P> Output<P> map(java.util.function.Function<O, P> mapper) {
		return new Output<P>() {
			
			@Override
			public P openOutput(URI uri) throws IOException {
				O output = Output.this.openOutput(uri);
				if (output == null) {
					return null;
				}
				return mapper.apply(output);
			}
			
		};
	}
	
	default <P> Output<P> map(UnaryOperator<URI> uriMapper, java.util.function.Function<O, P> mapper) {
		return mapURI(uriMapper).map(mapper);
	}
	
}
