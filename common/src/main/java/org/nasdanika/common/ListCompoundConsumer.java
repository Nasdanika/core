package org.nasdanika.common;

import java.util.Arrays;
import java.util.List;

/**
 * For each argument element an element consumer at the same index is invoked if present. 
 * @author Pavel
 *
 * @param <E>
 */
public class ListCompoundConsumer<E> extends ListCompoundExecutionParticipant<Consumer<? super E>> implements Consumer<List<E>>  {

	public ListCompoundConsumer(String name, List<Consumer<? super E>> consumers) {
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
	public void execute(List<E> args, ProgressMonitor progressMonitor) {
		if (args != null) {
			progressMonitor.setWorkRemaining(size());
			int idx = 0;
			List<Consumer<? super E>> els = getElements();
			for (E arg: args) {
				if (idx < els.size()) {
					Consumer<? super E> ec = els.get(idx);
					if (ec != null) {
						ec.splitAndExecute(arg, progressMonitor);
					}
				}
				++idx;
			}
		}
	}	

}
