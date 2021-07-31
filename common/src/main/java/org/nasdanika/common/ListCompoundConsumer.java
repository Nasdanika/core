package org.nasdanika.common;

import java.util.Arrays;
import java.util.Collection;

public class ListCompoundConsumer<E> extends ListCompoundExecutionParticipant<Consumer<? super E>> implements Consumer<E>  {

	public ListCompoundConsumer(String name, Collection<Consumer<? super E>> consumers) {
		super(name);
		for (Consumer<? super E> consumer: consumers) {
			add(consumer);
		}
	}
	
	@SafeVarargs
	public ListCompoundConsumer(String name, Consumer<? super E>... suppliers) {
		this(name, Arrays.asList(suppliers));
	}

	@Override
	public void execute(E arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Consumer<? super E> e: getElements()) {
			e.splitAndExecute(arg, progressMonitor);			
		}
	}	

}
