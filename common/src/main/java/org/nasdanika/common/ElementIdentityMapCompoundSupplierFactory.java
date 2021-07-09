package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ElementIdentityMapCompoundSupplierFactory<T> implements SupplierFactory<java.util.function.Function<SupplierFactory<T>,T>> {

	private String name;
	private List<SupplierFactory<T>> elements = new ArrayList<>();

	public ElementIdentityMapCompoundSupplierFactory(String name, Collection<SupplierFactory<T>> supplierFactories) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	public ElementIdentityMapCompoundSupplierFactory(String name) {
		this.name = name;
	}

	@Override
	public Supplier<java.util.function.Function<SupplierFactory<T>,T>> create(Context context) throws Exception {
		Map<SupplierFactory<T>, Supplier<T>> supplierMap = new IdentityHashMap<>();
		ElementIdentityMapCompoundSupplier<T> compoundSupplier = new ElementIdentityMapCompoundSupplier<>(name);		
		
		for (SupplierFactory<T> e: elements) {
			if (e != null) {
				Supplier<T> supplier = e.create(context);
				supplierMap.put(e, supplier);
				compoundSupplier.put(supplier);
			}
		}
		
		class Filter extends FilterExecutionParticipant<Supplier<java.util.function.Function<Supplier<T>,T>>> implements Supplier<java.util.function.Function<SupplierFactory<T>,T>> {

			public Filter(Supplier<java.util.function.Function<Supplier<T>,T>> target) {
				super(target);
			}

			@Override
			public Function<SupplierFactory<T>, T> execute(ProgressMonitor progressMonitor) throws Exception {
				return ((Function<SupplierFactory<T>, Supplier<T>>) supplierMap::get).andThen(target.execute(progressMonitor));
			}
			
		}
		
		return new Filter(compoundSupplier);
	}
	
	public void put(SupplierFactory<T> element) {
		if (element != null) {
			elements.add(element);
		}
	}	

}
