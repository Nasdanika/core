package org.nasdanika.cli;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.capability.CapabilityLoader;
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
				"Non-negative number:Delegate result",
				"-1:Unhandled exception during execution",
				"-2:Invalid input",
				"-3:Diagnostic failed",
				"-4:Execution failed or was cancelled, successful rollback",
				"-5:Execution failed or was cancelled, rollback failed",
				"-6:Executor service termination timed out"
		})
public abstract class DelegatingCommand extends ContextCommand {
		
	public DelegatingCommand() {
		
	}
	
	public DelegatingCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}	
	
	protected abstract SupplierFactory<Integer> getSupplierFactory();
	
	@Mixin
	private ProgressMonitorMixIn progressMonitorMixin;
		
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
		int result = -1;
		try (ProgressMonitor progressMonitor = progressMonitorMixin.createProgressMonitor(4)) {
			Context context = createContext(progressMonitor.split("Creating context", 1));
			try (org.nasdanika.common.Supplier<Integer> delegate = getSupplierFactory().create(context)) {
				ProgressMonitor commandMonitor = progressMonitor.split("Command monitor", 3).setWorkRemaining(3 * delegate.size());
				Diagnostic diagnostic = delegate.splitAndDiagnose(commandMonitor);
				if (diagnostic.getStatus() == Status.ERROR) {
					System.err.println("Diagnostic failed");
					diagnostic.dump(System.err, 4);
					return -3;
				}
				
				try {
					result = delegate.splitAndExecute(commandMonitor);
					delegate.splitAndCommit(commandMonitor);
				} catch (Exception e) {
					reportException(e);
					if (e instanceof DiagnosticException) {
						((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
					}
					return delegate.splitAndRollback(commandMonitor) ? -4 : -5;
				}
			} finally {
				ExecutorService executorService = context.get(ExecutorService.class);
				if (executorService != null) {
					executorService.shutdown();
					return executorService.awaitTermination(timeout, TimeUnit.SECONDS) ? result : -6;
				}
			}
		}
		return result;
	}

	protected void reportException(Exception e) {
		System.err.println("Exception during command execution or commit: "+e.getMessage());
		e.printStackTrace();
	}
	
}
