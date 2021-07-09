package org.nasdanika.common;

public class ContextifiedExecutionParticipant<T extends ExecutionParticipant>  implements ExecutionParticipant {
	
	protected Supplier<Context> contextSupplier;
	private ExecutionParticipantFactory<T> targetFactory;
	
	protected T target;

	public ContextifiedExecutionParticipant(Supplier<Context> contextSupplier, ExecutionParticipantFactory<T> targetFactory) {
		this.contextSupplier = contextSupplier;
		this.targetFactory = targetFactory;
	}

	@Override
	public double size() {
		return contextSupplier.size() * 2 + 1; // Get target, target op, context op. Scaling target size to 1.
	}

	@Override
	public String name() {
		return target == null ? getClass().getName() : "Contextified "+target.name();
	}
	
	/**
	 * Create context, create target. Uses context size from the progress monitor. 
	 * @param progressMonitor
	 * @return
	 */
	protected synchronized T getTarget(ProgressMonitor progressMonitor) throws Exception {
		if (target == null) {
			target = targetFactory.create(contextSupplier.splitAndExecute(progressMonitor));
		} else {
			progressMonitor.worked(contextSupplier.size(), "Target was already created");
		}
		return target;
	}
	
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		Diagnostic cd = contextSupplier.splitAndDiagnose(progressMonitor);
		if (cd.getStatus() == Status.ERROR) {
			return cd;
		}
		
		try {
			return getTarget(progressMonitor).splitAndDiagnose(1, progressMonitor);
		} catch (Exception e) {
			return new BasicDiagnostic(Status.ERROR, "Exception creating target: "+e, e);
		}
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		getTarget(progressMonitor).splitAndCommit(1, progressMonitor);
		contextSupplier.splitAndCommit(progressMonitor);
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		boolean result = getTarget(progressMonitor).splitAndRollback(1, progressMonitor);
		return contextSupplier.splitAndRollback(progressMonitor) && result;
	}
	
	@Override
	public void close() throws Exception {
		if (target != null) {
			target.close();
		}
		contextSupplier.close();
	}

	// ==========================================
	
	
	public static class ContextifiedSupplier<T> extends ContextifiedExecutionParticipant<Supplier<T>> implements Supplier<T> {

		public ContextifiedSupplier(Supplier<Context> contextSupplier, SupplierFactory<T> supplierFactory) {
			super(contextSupplier, supplierFactory);
		}

		@Override
		public T execute(ProgressMonitor progressMonitor) throws Exception {
			return getTarget(progressMonitor).execute(progressMonitor.scale(contextSupplier.size() + 1));
		}
		
	}
	
	public static class ContextifiedFunction<T,R> extends ContextifiedExecutionParticipant<Function<T,R>> implements Function<T,R> {

		public ContextifiedFunction(Supplier<Context> contextSupplier, FunctionFactory<T,R> functionFactory) {
			super(contextSupplier, functionFactory);
		}
		
		@Override
		public R execute(T arg, ProgressMonitor progressMonitor) throws Exception {
			return getTarget(progressMonitor).execute(arg, progressMonitor.scale(contextSupplier.size() + 1));
		}
		
	}
	
	public static class ContextifiedConsumer<T> extends ContextifiedExecutionParticipant<Consumer<T>> implements Consumer<T> {

		public ContextifiedConsumer(Supplier<Context> contextSupplier, ConsumerFactory<T> consumerFactory) {
			super(contextSupplier, consumerFactory);
		}

		@Override
		public void execute(T arg, ProgressMonitor progressMonitor) throws Exception {
			getTarget(progressMonitor).execute(arg, progressMonitor.scale(contextSupplier.size() + 1));
		}
		
	}
	
	public static class ContextifiedCommand extends ContextifiedExecutionParticipant<Command> implements Command {

		public ContextifiedCommand(Supplier<Context> contextSupplier, CommandFactory commandFactory) {
			super(contextSupplier, commandFactory);
		}

		@Override
		public void execute(ProgressMonitor progressMonitor) throws Exception {
			getTarget(progressMonitor).execute(progressMonitor.scale(contextSupplier.size() + 1));
		}
		
	}
	
}
