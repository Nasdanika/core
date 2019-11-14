package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CompoundConsumerFactory<T> implements ConsumerFactory<T> {

	private String name;
	private List<ConsumerFactory<T>> elements = new ArrayList<>();

	public CompoundConsumerFactory(String name, Collection<ConsumerFactory<T>> elements) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public CompoundConsumerFactory(String name, ConsumerFactory<T>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Consumer<T> create(Context context) throws Exception {
		CompoundConsumer<T> ret = new CompoundConsumer<T>(name);
		for (ConsumerFactory<T> e: elements) {
			ret.add(e.create(context));
		}
		return ret;
	}
	
	public void add(ConsumerFactory<T> element) {
		elements.add(element);
	}

}
