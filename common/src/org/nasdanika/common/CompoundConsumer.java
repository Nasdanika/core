package org.nasdanika.common;

public class CompoundConsumer<E> extends ListCompoundExecutionParticipant<Consumer<E>> implements Consumer<E>  {
	
	@SafeVarargs
	public CompoundConsumer(String name, Consumer<E>... consumers) {
		super(name);
		for (Consumer<E> consumer: consumers) {
			add(consumer);
		}
	}

	@Override
	public void execute(E arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Consumer<E> e: getElements()) {
			e.splitAndExecute(arg, progressMonitor);			
		}
	}	

}
