package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates a {@link ListCompoundConsumer} which passes the argument element to element consumer with the same index, if present.
 * @author Pavel
 *
 * @param <T>
 */
public class ListCompoundConsumerFactory<T> implements ConsumerFactory<List<T>> {

	private String name;
	private List<ConsumerFactory<? super T>> elements = new ArrayList<>();

	public ListCompoundConsumerFactory(String name, List<? extends ConsumerFactory<? super T>> elements) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public ListCompoundConsumerFactory(String name, ConsumerFactory<? super T>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Consumer<List<T>> create(Context context) {
		ListCompoundConsumer<T> ret = new ListCompoundConsumer<>(name);
		for (ConsumerFactory<? super T> e: elements) {
			ret.add(e == null ? null : e.create(context));
		}
		return ret;
	}
	
	public void add(ConsumerFactory<? super T> element) {
		elements.add(element);
	}
	
	public void addAll(Iterable<? extends ConsumerFactory<? super T>> elements) {
		elements.forEach(this::add);
	}

}
