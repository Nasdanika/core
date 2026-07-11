package org.nasdanika.common;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

public interface StringOutput extends Output<java.util.function.Consumer<String>> {

	static StringOutput of(Output<OutputStream> output) {
		return new StringOutput() {
			
			@Override
			public java.util.function.Consumer<String> openOutput(URI uri) {
				OutputStream os = output.openOutput(uri);
				if (os == null) {
					return null;
				}
												
				return string -> {
					try {
						os.write(string.getBytes());
					} catch (IOException e) {
						throw new NasdanikaException(e);
					}
				};
			}
			
		};
	}

}
