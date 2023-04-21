package org.nasdanika.common;

import java.util.Arrays;
import java.util.Collection;

/**
 * This consumer invokes element consumers passing them the argument.
 * @author Pavel
 *
 * @param <E>
 */
public class CollectionCompoundConsumer<E> extends ListCompoundExecutionParticipant<Consumer<? super E>> implements Consumer<E>  {

	public CollectionCompoundConsumer(String name, Collection<Consumer<? super E>> consumers) {
		super(name);
		for (Consumer<? super E> consumer: consumers) {
			add(consumer);
		}
	}
	
	@SafeVarargs
	public CollectionCompoundConsumer(String name, Consumer<? super E>... consumers) {
		this(name, Arrays.asList(consumers));
	}

	@Override
	public void execute(E arg, ProgressMonitor progressMonitor) {
		progressMonitor.setWorkRemaining(size());
		for (Consumer<? super E> e: getElements()) {
			if (e != null) {
				e.splitAndExecute(arg, progressMonitor);			
			}
		}
	}	

}
