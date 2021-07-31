package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListCompoundConsumerFactory<T> implements ConsumerFactory<T> {

	private String name;
	private List<ConsumerFactory<? super T>> elements = new ArrayList<>();

	public ListCompoundConsumerFactory(String name, Collection<? extends ConsumerFactory<? super T>> elements) {
		this.name = name;
		elements.stream().forEach(Objects::requireNonNull);
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public ListCompoundConsumerFactory(String name, ConsumerFactory<? super T>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Consumer<T> create(Context context) throws Exception {
		ListCompoundConsumer<T> ret = new ListCompoundConsumer<>(name);
		for (ConsumerFactory<? super T> e: elements) {
			ret.add(e.create(context));
		}
		return ret;
	}
	
	public void add(ConsumerFactory<? super T> element) {
		elements.add(Objects.requireNonNull(element));
	}

}
