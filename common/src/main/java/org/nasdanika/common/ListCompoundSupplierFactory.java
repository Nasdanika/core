package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListCompoundSupplierFactory<T> implements SupplierFactory<List<T>> {

	private String name;
	private List<SupplierFactory<? extends T>> elements = new ArrayList<>();

	public ListCompoundSupplierFactory(String name, Collection<? extends SupplierFactory<? extends T>> elements) {
		this.name = name;
		elements.stream().forEach(Objects::requireNonNull);
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public ListCompoundSupplierFactory(String name, SupplierFactory<? extends T>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Supplier<List<T>> create(Context context) throws Exception {
		ListCompoundSupplier<T> ret = new ListCompoundSupplier<>(name);
		for (SupplierFactory<? extends T> e: elements) {
			ret.add(e.create(context));
		}
		return ret;
	}
	
	public void add(SupplierFactory<? extends T> element) {
		elements.add(Objects.requireNonNull(element));
	}

}