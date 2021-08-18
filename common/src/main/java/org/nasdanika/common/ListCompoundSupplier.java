package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListCompoundSupplier<E> extends ListCompoundExecutionParticipant<Supplier<? extends E>> implements Supplier<List<E>>  {

	public ListCompoundSupplier(String name, Collection<Supplier<? extends E>> suppliers) {
		super(name);
		for (Supplier<? extends E> supplier: suppliers) {
			add(supplier);
		}
	}
	
	@SafeVarargs
	public ListCompoundSupplier(String name, Supplier<? extends E>... suppliers) {
		this(name, Arrays.asList(suppliers));
	}

	@Override
	public List<E> execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		List<E> result = new ArrayList<>();
		for (Supplier<? extends E> e: getElements()) {
			result.add(e == null ? null : e.splitAndExecute(progressMonitor));			
		}
		return result;
	}	

}
