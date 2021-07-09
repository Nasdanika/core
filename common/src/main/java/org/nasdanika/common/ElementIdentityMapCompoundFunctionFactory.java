package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class ElementIdentityMapCompoundFunctionFactory<T,R> implements FunctionFactory<T, java.util.function.Function<FunctionFactory<T,R>,R>> {

	private String name;
	private List<FunctionFactory<T,R>> elements = new ArrayList<>();

	public ElementIdentityMapCompoundFunctionFactory(String name, Collection<FunctionFactory<T,R>> functionsFactories) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	public ElementIdentityMapCompoundFunctionFactory(String name) {
		this.name = name;
	}

	@Override
	public Function<T, java.util.function.Function<FunctionFactory<T, R>, R>> create(Context context) throws Exception {
		Map<FunctionFactory<T,R>, Function<T,R>> functionMap = new IdentityHashMap<>();
		ElementIdentityMapCompoundFunction<T,R> compoundFunction = new ElementIdentityMapCompoundFunction<>(name);		
		
		for (FunctionFactory<T,R> e: elements) {
			if (e != null) {
				Function<T,R> supplier = e.create(context);
				functionMap.put(e, supplier);
				compoundFunction.put(supplier);
			}
		}
		
		class Filter extends FilterExecutionParticipant<Function<T, java.util.function.Function<Function<T,R>,R>>> implements Function<T,java.util.function.Function<FunctionFactory<T,R>,R>> {

			public Filter(Function<T,java.util.function.Function<Function<T,R>,R>> target) {
				super(target);
			}

			@Override
			public java.util.function.Function<FunctionFactory<T, R>, R> execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				return ((java.util.function.Function<FunctionFactory<T, R>, Function<T, R>>) functionMap::get).andThen(target.execute(arg, progressMonitor));
			}
			
		}
		
		return new Filter(compoundFunction);
	}
	
	public void put(FunctionFactory<T,R> element) {
		if (element != null) {
			elements.add(element);
		}
	}

}
