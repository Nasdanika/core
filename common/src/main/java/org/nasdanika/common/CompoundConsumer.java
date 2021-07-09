package org.nasdanika.common;

import java.util.Arrays;
import java.util.Collection;

public class CompoundConsumer<E> extends ListCompoundExecutionParticipant<Consumer<E>> implements Consumer<E>  {
	
	public CompoundConsumer(String name, Collection<Consumer<E>> consumers) {
		super(name);
		for (Consumer<E> consumer: consumers) {
			add(consumer);
		}
	}

	@SafeVarargs
	public CompoundConsumer(String name, Consumer<E>... consumers) {
		this(name, Arrays.asList(consumers));
	}

	@Override
	public void execute(E arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Consumer<E> e: getElements()) {
			e.splitAndExecute(arg, progressMonitor);			
		}
	}	

}
