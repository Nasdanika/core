package org.nasdanika.common;

public class CompoundConsumer<E> extends ListCompoundExecutionParticipant<Consumer<E>> implements Consumer<E>  {
	
	public CompoundConsumer(String name) {
		super(name);
	}

	@Override
	public void execute(E arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Consumer<E> e: getElements()) {
			e.splitAndExecute(arg, progressMonitor);			
		}
	}	

}
