package org.nasdanika.common;

import java.io.InputStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;

public interface StringInput extends Input<String> {
	
	interface Line {
		
		int getLineNumber();
		
		String getLine();
		
		static Line of(int lineNumber, String line) {
			return new Line() {
				
				@Override
				public int getLineNumber() {
					return lineNumber;
				}
				
				@Override
				public String getLine() {
					return line;
				}
			};
		}

		default Line mapLine(java.util.function.UnaryOperator<String> mapper) {
			return new Line() {
				
				@Override
				public int getLineNumber() {
					return Line.this.getLineNumber();
				}
				
				@Override
				public String getLine() {
					return mapper.apply(Line.this.getLine());
				}
				
			};
		}
		
	}

	static StringInput of(URI uri, String string) {
		return new StringInput() {
			
			@Override
			public String openInput() {
				return string;
			}

			@Override
			public URI getURI() {
				return uri;
			}
			
		};
	}
	
	static StringInput of(Input<String> input) {
		return new StringInput() {
			
			@Override
			public String openInput() {
				return input.openInput();
			}

			@Override
			public URI getURI() {
				return input.getURI();
			}
			
		};
	}
	
	static StringInput ofStreamInput(Input<InputStream> streamInput) {
		
		return of(streamInput.mapURI(uri -> uri).map(inputStream -> {
			
			try (InputStream is = inputStream) {
				return new String(is.readAllBytes());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}));
	}
	
	default Stream<Line> lines() {
		String content = openInput();
		if (content == null) {
			return Stream.empty();
		}
		String[] lineArray = content.split("\\R", -1);
		return IntStream.range(0, lineArray.length)
			.mapToObj(i -> {
				return new Line() {
					@Override
					public int getLineNumber() {
						return i + 1;
					}
					@Override
					public String getLine() {
						return lineArray[i];
					}
				};
			});
	}
	
	static StringInput ofLines(URI uri, Stream<String> lines) {
		StringBuilder sb = new StringBuilder();
		lines.forEach(line -> {
			sb.append(line);
			sb.append(System.lineSeparator());
		});
		return of(uri, sb.toString());
	}
	
	default StringInput mapLines(java.util.function.UnaryOperator<Line> mapper) {
		StringInput self = this; // capture outer instance to avoid recursive this.lines() → this.openInput() loop
		return new StringInput() {
			
			@Override
			public String openInput() {
				return self.lines()  // calls the OUTER instance's lines() → outer openInput(), not this one
					.map(mapper)
					.map(Line::getLine)
					.reduce((a, b) -> a + System.lineSeparator() + b)
					.orElse("");
			}

			@Override
			public URI getURI() {
				return self.getURI();
			}
			
		};
	}
	
	default void transferTo(Output<java.util.function.Consumer<String>> output) {
		output.openOutput(getURI()).accept(openInput());
	}
	
}