package org.nasdanika.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ProgressRecorder;
import org.nasdanika.common.Status;

import picocli.CommandLine.Command;
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
				"5:Execution failed or was cancelled, rollback was not successful"
		})
public abstract class DelegatingCommand extends ContextCommand {
	
	protected abstract CommandFactory getCommandFactory();
	
	@Option(names = {"-p", "--progress"}, description = "Output file for progress monitor")
	private File progressOutput;
	
	@Option(names = {"-j", "--json"}, description = "Output progress in JSON")
	private boolean jsonProgress;
	
	protected ProgressMonitor createProgressMonitor(double size) throws Exception {
		if (progressOutput == null) {
			if (jsonProgress) {
				return new ProgressRecorder() {
					
					@Override
					public void close() {
						System.out.println(toJSON().toString(4));
						super.close();
					}
					
				};			
			}
			return new PrintStreamProgressMonitor(System.out, 0, 4, false);
		}
		
		if (jsonProgress) {
			return new ProgressRecorder() {
				
				@Override
				public void close() {
					try (Writer w = new FileWriter(progressOutput)) {
						toJSON().write(w, 4, 0);
					} catch (IOException e) {
						System.err.println("Error closing progress writer");
						e.printStackTrace();
					}
					super.close();
				}
				
			};			
		}
		return new PrintStreamProgressMonitor(new PrintStream(progressOutput), 0, 4, true);		
	}
	
	@Override
	public Integer call() throws Exception {		
		try (org.nasdanika.common.Command delegate = getCommandFactory().create(createContext()); ProgressMonitor progressMonitor = createProgressMonitor(3 * delegate.size())) {
			Diagnostic diagnostic = delegate.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				System.err.println("Diagnostic failed");
				diagnostic.dump(System.err, 4);
				return 3;
			}
			
			try {
				delegate.splitAndExecute(progressMonitor);
				delegate.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				System.err.println("Exception during command execution or commit: "+e.getMessage());
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				return delegate.splitAndRollback(progressMonitor) ? 4 : 5;
			}			
		}
		return 0;
	}

}
