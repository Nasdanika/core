package org.nasdanika.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DelimitedStringMap extends AbstractSplitJoinMap<String, String, String, String> {
	
	private String entryDelimiter;
	private String keyValueDelimiter;
	
	public DelimitedStringMap(String entryDelimiter, String keyValueDelimiter) {
		this.entryDelimiter = entryDelimiter;
		this.keyValueDelimiter = keyValueDelimiter;
	}	

	@Override
	protected List<String> split(String state) {
		return state == null ? Collections.emptyList() : Arrays.asList(state.split(entryDelimiter));
	}

	@Override
	protected String join(List<String> chunks) {
		return String.join(entryDelimiter, chunks.toArray(new String[chunks.size()]));
	}

	@Override
	protected Entry<String, String> load(String chunk) {
		String[] ea = chunk.split(keyValueDelimiter);
		return new Entry<String, String>() {

			@Override
			public String getKey() {
				return ea[0];
			}

			@Override
			public String getValue() {
				return ea[1];
			}

			@Override
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	protected String store(Entry<String, String> element) {
		return element.getKey() + keyValueDelimiter + element.getValue();
	}

}
