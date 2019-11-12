package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

public class ListCompoundSupplier<E> extends ListCompoundExecutionParticipant<Supplier<E>> implements Supplier<List<E>>  {
	
	@SafeVarargs
	public ListCompoundSupplier(String name, Supplier<E>... suppliers) {
		super(name);
		for (Supplier<E> supplier: suppliers) {
			add(supplier);
		}
	}

	@Override
	public List<E> execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		List<E> result = new ArrayList<>();
		for (Supplier<E> e: getElements()) {
			result.add(e.splitAndExecute(progressMonitor));			
		}
		return result;
	}	

}
