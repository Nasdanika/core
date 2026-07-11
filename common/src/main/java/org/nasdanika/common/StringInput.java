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
		return new StringInput() {
			
			@Override
			public String openInput() {
				return lines()
					.map(mapper)
					.map(Line::getLine)
					.reduce((a, b) -> a + System.lineSeparator() + b)
					.orElse("");
			}

			@Override
			public URI getURI() {
				return StringInput.this.getURI();
			}
			
		};
	}
	
}