package org.nasdanika.common;

public class ContextifiedExecutionParticipant<T extends ExecutionParticipant>  implements ExecutionParticipant {
	
	public static class ContextifiedSupplier<T> extends ContextifiedExecutionParticipant<Supplier<T>> implements Supplier<T> {

		public ContextifiedSupplier(Supplier<Context> contextSupplier, SupplierFactory<T> supplierFactory) {
			super(contextSupplier, supplierFactory);
		}

		@Override
		public T execute(ProgressMonitor progressMonitor) throws Exception {
			if (target != null) {
				return target.execute(progressMonitor);
			}
			
			progressMonitor.setWorkRemaining(2);			
			target = createTarget(progressMonitor);
			
			try (ProgressMonitor targetMonitor = progressMonitor.split("Execute target", 1)) {
				targetMonitor.setWorkRemaining(target.size());
				return target.execute(targetMonitor);
			}			
		}
		
	}
	
	public static class ContextifiedFunction<T,R> extends ContextifiedExecutionParticipant<Function<T,R>> implements Function<T,R> {

		public ContextifiedFunction(Supplier<Context> contextSupplier, FunctionFactory<T,R> functionFactory) {
			super(contextSupplier, functionFactory);
		}
		
		@Override
		public R execute(T arg, ProgressMonitor progressMonitor) throws Exception {
			if (target != null) {
				return target.execute(arg, progressMonitor);
			}
			
			progressMonitor.setWorkRemaining(2);			
			target = createTarget(progressMonitor);
			
			try (ProgressMonitor targetMonitor = progressMonitor.split("Execute target", 1)) {
				targetMonitor.setWorkRemaining(target.size());
				return target.execute(arg, targetMonitor);
			}			
		}
		
	}
	
	public static class ContextifiedConsumer<T> extends ContextifiedExecutionParticipant<Consumer<T>> implements Consumer<T> {

		public ContextifiedConsumer(Supplier<Context> contextSupplier, ConsumerFactory<T> consumerFactory) {
			super(contextSupplier, consumerFactory);
		}

		@Override
		public void execute(T arg, ProgressMonitor progressMonitor) throws Exception {
			if (target != null) {
				target.execute(arg, progressMonitor);
			} else {
				progressMonitor.setWorkRemaining(2);			
				target = createTarget(progressMonitor);
				
				try (ProgressMonitor targetMonitor = progressMonitor.split("Execute target", 1)) {
					targetMonitor.setWorkRemaining(target.size());
					target.execute(arg, targetMonitor);
				}
			}
		}
		
	}
	
	public static class ContextifiedCommand extends ContextifiedExecutionParticipant<Command> implements Command {

		public ContextifiedCommand(Supplier<Context> contextSupplier, CommandFactory commandFactory) {
			super(contextSupplier, commandFactory);
		}

		@Override
		public void execute(ProgressMonitor progressMonitor) throws Exception {
			if (target != null) {
				target.execute(progressMonitor);
			} else {			
				progressMonitor.setWorkRemaining(2);			
				target = createTarget(progressMonitor);
				
				try (ProgressMonitor targetMonitor = progressMonitor.split("Execute target", 1)) {
					targetMonitor.setWorkRemaining(target.size());
					target.execute(targetMonitor);
				}
			}
		}
		
	}
	
	private Supplier<Context> contextSupplier;
	private ExecutionParticipantFactory<T> targetFactory;
	
	protected T target;

	public ContextifiedExecutionParticipant(Supplier<Context> contextSupplier, ExecutionParticipantFactory<T> targetFactory) {
		this.contextSupplier = contextSupplier;
		this.targetFactory = targetFactory;
	}

	@Override
	public double size() {
		return 3;
	}

	@Override
	public String name() {
		return target == null ? getClass().getName() : "Contextified "+target.name();
	}
	
	/**
	 * Create context, create target. Finally commit/rollback and close context supplier 
	 * @param progressMonitor
	 * @return
	 */
	protected T createTarget(ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor createMonitor = progressMonitor.split("Creating target", 1)){
			createMonitor.setWorkRemaining(contextSupplier.size() * 2);
			try {
				T ret = targetFactory.create(contextSupplier.splitAndExecute(createMonitor));
				contextSupplier.splitAndCommit(createMonitor);
				return ret;
			} catch (Exception e) {
				contextSupplier.splitAndRollback(createMonitor);
				throw e;
			} finally {
				contextSupplier.close();
			}
		}				
	}
	
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		if (target != null) {
			return target.diagnose(progressMonitor);
		}
		progressMonitor.setWorkRemaining(3);
		
		try (ProgressMonitor contextMonitor = progressMonitor.split("Diagnose context supplier", 1)) {
			contextMonitor.setWorkRemaining(contextSupplier.size());
			Diagnostic cd = contextSupplier.diagnose(contextMonitor);
			if (cd.getStatus() == Status.ERROR) {
				return cd;
			}
		}		
				
		try {
			target = createTarget(progressMonitor);
		} catch (Exception e) {
			return new BasicDiagnostic(Status.ERROR, "Exception creating target: "+e, e);
		}
		
		try (ProgressMonitor targetMonitor = progressMonitor.split("Diagnose target", 1)) {
			targetMonitor.setWorkRemaining(target.size());
			return target.diagnose(targetMonitor);
		}
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		if (target != null) {
			target.commit(progressMonitor);
		} else {			
			progressMonitor.setWorkRemaining(2);			
			target = createTarget(progressMonitor);
			
			try (ProgressMonitor targetMonitor = progressMonitor.split("Commit target", 1)) {
				targetMonitor.setWorkRemaining(target.size());
				target.commit(targetMonitor);
			}			
		}
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		if (target != null) {
			return target.rollback(progressMonitor);
		}
		
		progressMonitor.setWorkRemaining(2);			
		target = createTarget(progressMonitor);
		
		try (ProgressMonitor targetMonitor = progressMonitor.split("Rollback target", 1)) {
			targetMonitor.setWorkRemaining(target.size());
			return target.rollback(targetMonitor);
		}			
	}
	
	@Override
	public void close() throws Exception {
		if (target != null) {
			target.close();
		}
	}

}
