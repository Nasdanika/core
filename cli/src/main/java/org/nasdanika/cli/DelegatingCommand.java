package org.nasdanika.cli;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

/**
 * Base class for commands which create {@link Context} and {@link ProgressMonitor} and
 * then delegate execution to {@link Command} created by {@link CommandFactory} obtained from
 * <code>getCommandFactory()</code> abstract protected method which subclasses shall override.
 * This command executes all life cycle methods of the target command - diagnose, execute, commit or rollback, close.
 * Progress can be reported to console of file in text or json format.
 * 
 * @author Pavel
 *
 */
@Command(
		exitCodeListHeading = "Exit codes:\n",
		exitCodeList = {
				"0:Success",
				"1:Unhandled exception during execution",
				"2:Invalid input",
				"3:Diagnostic failed",
				"4:Execution failed or was cancelled, successful rollback",
				"5:Execution failed or was cancelled, rollback was not successful",
				"6:Executor service termination timed out"
		})
public abstract class DelegatingCommand extends ContextCommand {
	
	protected abstract CommandFactory getCommandFactory();
	
	@Mixin
	private ProgressMonitorMixin progressMonitorMixin;
		
	@Option(
			names = { "-P", "--parallelism" }, 
			defaultValue = "1", 
			description = "If the value greater than one then an executor service is created and injected into the context to allow concurrent execution.")
	private int parallelism;
	
	@Option(
			names = { "-t", "--timeout" }, 
			defaultValue = "60", 
			description = "If parallelism is greater than one this option specifies timout in seconds awaiting completion of execution. "
					+ "Default value is ${DEFAULT-VALUE}.")
	private int timeout;		
	
	@Override
	protected Context createContext(ProgressMonitor progressMonitor) throws Exception {
		Context context = super.createContext(progressMonitor);
		if (parallelism <= 1) {
			return context;
		}
		return context.compose(Context.singleton(ExecutorService.class, Executors.newWorkStealingPool(parallelism)));
	}
	
	@Override
	public Integer call() throws Exception {		
		try (ProgressMonitor progressMonitor = progressMonitorMixin.createProgressMonitor(4)) {
			Context context = createContext(progressMonitor.split("Creating context", 1));
			try (org.nasdanika.common.Command delegate = getCommandFactory().create(context)) {
				ProgressMonitor commandMonitor = progressMonitor.split("Command monitor", 3).setWorkRemaining(3 * delegate.size());
				Diagnostic diagnostic = delegate.splitAndDiagnose(commandMonitor);
				if (diagnostic.getStatus() == Status.ERROR) {
					System.err.println("Diagnostic failed");
					diagnostic.dump(System.err, 4);
					return 3;
				}
				
				try {
					delegate.splitAndExecute(commandMonitor);
					delegate.splitAndCommit(commandMonitor);
				} catch (Exception e) {
					reportException(e);
					if (e instanceof DiagnosticException) {
						((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
					}
					return delegate.splitAndRollback(commandMonitor) ? 4 : 5;
				}
			} finally {
				ExecutorService executorService = context.get(ExecutorService.class);
				if (executorService != null) {
					executorService.shutdown();
					return executorService.awaitTermination(timeout, TimeUnit.SECONDS) ? 0 : 6;
				}
			}
		}
		return 0;
	}

	protected void reportException(Exception e) {
		System.err.println("Exception during command execution or commit: "+e.getMessage());
		e.printStackTrace();
	}
	
}
