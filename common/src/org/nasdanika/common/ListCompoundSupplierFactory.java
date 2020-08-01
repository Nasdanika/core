package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListCompoundSupplierFactory<T> implements SupplierFactory<List<T>> {

	private String name;
	private List<SupplierFactory<T>> elements = new ArrayList<>();

	public ListCompoundSupplierFactory(String name, Collection<? extends SupplierFactory<T>> elements) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public ListCompoundSupplierFactory(String name, SupplierFactory<T>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Supplier<List<T>> create(Context context) throws Exception {
		ListCompoundSupplier<T> ret = new ListCompoundSupplier<T>(name);
		for (SupplierFactory<T> e: elements) {
			ret.add(e.create(context));
		}
		return ret;
	}
	
	public void add(SupplierFactory<T> element) {
		elements.add(element);
	}

}
