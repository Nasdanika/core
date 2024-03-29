package org.nasdanika.common;

/**
 * Command executes with {@link ProgressMonitor} and returns no result.
 * @author Pavel Vlasov
 * @param C context type.
 * @param T result type.
 */
public interface Command extends ExecutionParticipant, ExecutionParticipantInfo {
	
	Command NOP = new Command() {

		@Override
		public double size() {
			return 0;
		}
		
		@Override
		public String name() {
			return "NOP";
		}

		@Override
		public void execute(ProgressMonitor monitor) {
			
		}
		
	};
			
	void execute(ProgressMonitor progressMonitor);	
	
	default void splitAndExecute(ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Executing "+name())) {
			execute(subMonitor);
		}
	}	
	
	default void splitAndExecute(double size, ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(size, progressMonitor, "Executing "+name())) {
			execute(subMonitor);
		}
	}	
		
	static Command fromRunnable(Runnable runnable, String name, double size) {
		return new Command() {
			
			@Override
			public double size() {
				return size;
			}
			
			@Override
			public String name() {
				return name;
			}
			
			@Override
			public void execute(ProgressMonitor progressMonitor) {
				runnable.run();
			}
		};
	}
	
	default Runnable toRunnable(ProgressMonitor progressMonitor) {
		return () -> execute(progressMonitor);
	}
	
	
}
