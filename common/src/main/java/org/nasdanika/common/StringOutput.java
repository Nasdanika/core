package org.nasdanika.common;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.eclipse.emf.common.util.URI;

public interface StringOutput<C extends java.util.function.Consumer<String>> extends Output<C> {
	
	interface CloseableStringConsumer extends java.util.function.Consumer<String>, AutoCloseable {
				
	}

	static StringOutput<CloseableStringConsumer> ofStreamOutput(Output<OutputStream> output) {
		return new StringOutput<CloseableStringConsumer>() {
			
			@Override
			public CloseableStringConsumer openOutput(URI uri) {
				OutputStream os = output.openOutput(uri);
				if (os == null) {
					return null;
				}
											
				return new CloseableStringConsumer() {
					
					@Override
					public void close() throws Exception {
						os.close();						
					}
					
					@Override
					public void accept(String string) {
						try {
							os.write(string.getBytes(StandardCharsets.UTF_8));
						} catch (IOException e) {
							throw new NasdanikaException(e);
						}
						
					}
				};
			}
			
		};
	}
	
	static <C extends java.util.function.Consumer<String>> StringOutput<C> of(Output<C> output) {
		return new StringOutput<C>() {
			
			@Override
			public C openOutput(URI uri) {
				return output.openOutput(uri);
			}
			
		};
	}
	
	/**
	 * Default output that uses EMF URIConverter to create output streams
	 */
	static StringOutput<CloseableStringConsumer> INSTANCE = ofStreamOutput(StreamOutput.INSTANCE);
	

}
