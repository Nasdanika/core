package org.nasdanika.common;

public class CompoundCommand extends ListCompoundExecutionParticipant<Command> implements Command  {
	
	public CompoundCommand(String name) {
		super(name);
	}

	@Override
	public void execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Command e: getElements()) {
			e.splitAndExecute(progressMonitor);			
		}
	}

}
