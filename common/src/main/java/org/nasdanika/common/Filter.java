package org.nasdanika.common;

public interface Filter<O> {
	
	O filter(O output);
	
	default Filter<O> andThen(Filter<O> next) {
		if (next == null) {
			return this;
		}
		return output -> this.filter(next.filter(output));
	}

}
