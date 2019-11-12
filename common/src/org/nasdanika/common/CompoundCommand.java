package org.nasdanika.common;

public class CompoundCommand extends ListCompoundExecutionParticipant<Command> implements Command  {
	
	public CompoundCommand(String name, Command... commands) {
		super(name);
		for (Command command: commands) {
			add(command);
		}
	}

	@Override
	public void execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Command e: getElements()) {
			e.splitAndExecute(progressMonitor);			
		}
	}

}
