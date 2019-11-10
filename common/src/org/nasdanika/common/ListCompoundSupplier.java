package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

public class ListCompoundSupplier<E> extends ListCompoundExecutionParticipant<Supplier<E>> implements Supplier<List<E>>  {
	
	public ListCompoundSupplier(String name) {
		super(name);
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
