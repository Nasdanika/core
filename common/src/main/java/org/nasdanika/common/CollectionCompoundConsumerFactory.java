package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Creates a {@link CollectionCompoundConsumer} which passes the argument to all element consumers.
 * @author Pavel
 *
 * @param <T>
 */
public class CollectionCompoundConsumerFactory<T> implements ConsumerFactory<T> {

	private String name;
	private List<ConsumerFactory<? super T>> elements = new ArrayList<>();

	public CollectionCompoundConsumerFactory(String name, Collection<? extends ConsumerFactory<? super T>> elements) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public CollectionCompoundConsumerFactory(String name, ConsumerFactory<? super T>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Consumer<T> create(Context context) {
		CollectionCompoundConsumer<T> ret = new CollectionCompoundConsumer<>(name);
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
