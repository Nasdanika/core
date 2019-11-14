package org.nasdanika.common;

import java.util.Arrays;
import java.util.Collection;

public class CompoundCommand extends ListCompoundExecutionParticipant<Command> implements Command  {
	
	public CompoundCommand(String name, Collection<Command> commands) {
		super(name);
		for (Command command: commands) {
			add(command);
		}
	}
	
	public CompoundCommand(String name, Command... commands) {
		this(name, Arrays.asList(commands));
	}

	@Override
	public void execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		for (Command e: getElements()) {
			e.splitAndExecute(progressMonitor);			
		}
	}

}
