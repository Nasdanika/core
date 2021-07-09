package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CompoundCommand extends ListCompoundExecutionParticipant<Command> implements Command  {
	
	private ExecutorService executorService;

	public CompoundCommand(String name, ExecutorService executorService, Collection<Command> commands) {
		super(name);
		this.executorService = executorService;
		for (Command command: commands) {
			add(command);
		}
	}
	
	public CompoundCommand(String name, ExecutorService executorService, Command... commands) {
		this(name, executorService, Arrays.asList(commands));
	}

	@Override
	public void execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		if (executorService == null || elements.size() < 2) {
			for (Command e: getElements()) {
				e.splitAndExecute(progressMonitor);			
			}
		} else {
			List<Future<Void>> invokeAll = executorService.invokeAll(getTasks(elements.subList(1, elements.size()), progressMonitor));
			elements.get(0).splitAndExecute(progressMonitor); // Execute the first element in the current thread while other elements are executed in other threads.
			for (Future<?> future: invokeAll) {
				future.get();
			};			
		}
	}
	
	private static List<Callable<Void>> getTasks(List<Command> elements, ProgressMonitor progressMonitor) {
		List<Callable<Void>> ret = new ArrayList<>();
		for (Command e: elements) {
			ret.add(new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					e.splitAndExecute(progressMonitor);
					return null;
				}
				
			});
		}		
		return ret;
	}

}
