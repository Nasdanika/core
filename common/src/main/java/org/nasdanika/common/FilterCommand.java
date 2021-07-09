package org.nasdanika.common;

public class FilterCommand extends FilterExecutionParticipant<Command> implements Command { 

	public FilterCommand(Command target) {
		super(target);
	}

	@Override
	public void execute(ProgressMonitor progressMonitor) throws Exception {
		target.execute(progressMonitor);
	}

}
