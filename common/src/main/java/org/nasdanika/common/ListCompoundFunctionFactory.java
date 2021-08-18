package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListCompoundFunctionFactory<T,R> implements FunctionFactory<T,List<R>> {

	private String name;
	private List<FunctionFactory<? super T, ? extends R>> elements = new ArrayList<>();

	public ListCompoundFunctionFactory(String name, Collection<FunctionFactory<? super T, ? extends R>> elements) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public ListCompoundFunctionFactory(String name, FunctionFactory<? super T, ? extends R>... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Function<T,List<R>> create(Context context) throws Exception {
		ListCompoundFunction<T, R> ret = new ListCompoundFunction<T,R>(name);
		for (FunctionFactory<? super T, ? extends R> e: elements) {
			ret.add(e == null ? null : e.create(context));
		}
		return ret;
	}
	
	public void add(FunctionFactory<? super T, ? extends R> element) {
		elements.add(element);
	}	
	
	public void addAll(Iterable<? extends FunctionFactory<? super T, ? extends R>> elements) {
		elements.forEach(this::add);
	}

}
