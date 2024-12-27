package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;


/**
 * By convention Supplier does not split the monitor for itself - this is the responsibility of the caller.
 * 
 * {@link ProgressMonitor} allocation pattern - the caller creates a monitor with work's size and name and passes it to work's execute:
 * 
 * ```
 * try (Supplier supplier = ...; ProgressMonitor workMonitor = monitor.split(supplier.getName(), supplier.getSize(), supplier)) {
 *     work.execute(workMonitor);
 *     
 *     // If success
 *     work.commit();
 *     
 *     // If something went wrong
 *     work.rollback();
 *     
 * }
 * ```
 * 
 * @author Pavel Vlasov
 * @param C context type.
 * @param T result type.
 */
public interface Supplier<T> extends ExecutionParticipant, ExecutionParticipantInfo, java.util.function.Function<ProgressMonitor,T> {
	
	/**
	 * Executes the Supplier.
	 * @param monitor Monitor to use.
	 * @return
	 */
	T execute(ProgressMonitor progressMonitor);	
	
	default T splitAndExecute(ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Executing " + name())) {
			return execute(subMonitor);
		}
	}
	
	default T splitAndExecute(double size, ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(size, progressMonitor, "Executing " + name())) {
			return execute(subMonitor);
		}
	}
	
	Supplier<Object> EMPTY = new Supplier<Object>() {

		@Override
		public double size() {
			return 0;
		}
		
		@Override
		public String name() {
			return "Empty supplier";
		}

		@Override
		public Object execute(ProgressMonitor monitor) {
			return null;
		}
		
	};
	
	@SuppressWarnings("unchecked")
	static <T> Supplier<T> empty() {
		return (Supplier<T>) EMPTY;
	}
	
	default <R> Supplier<R> then(java.util.function.Function<? super T,R> then) {
		return new Supplier<R>() {
			
			@Override
			public R execute(ProgressMonitor progressMonitor) {
				return then.apply(Supplier.this.execute(progressMonitor));
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Supplier.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() {
				Supplier.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) {
				Supplier.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) {
				return Supplier.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Supplier.this.size();
			}
			
			@Override
			public String name() {
				return Supplier.this.name();
			}
			
		};
	}
		
	default <R> Supplier<R> then(java.util.function.BiFunction<? super T,ProgressMonitor,R> then) {
		return new Supplier<R>() {
			
			@Override
			public R execute(ProgressMonitor progressMonitor) {			
				// TODO - split monitor
				return then.apply(Supplier.this.execute(progressMonitor), progressMonitor);
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Supplier.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() {
				Supplier.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) {
				Supplier.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) {
				return Supplier.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Supplier.this.size();
			}
			
			@Override
			public String name() {
				return Supplier.this.name();
			}
			
		};
	}
	
	
	default <V> Supplier<V> then(Function<? super T,V> then) {
		List<ExecutionParticipant> elements = new ArrayList<>();
		elements.add(this);
		elements.add(then);
		class Then extends CompoundExecutionParticipant<ExecutionParticipant> implements Supplier<V> {

			protected Then(String name) {
				super(name, true);
			}
			
			@Override
			public V execute(ProgressMonitor progressMonitor) {
				return then.splitAndExecute(Supplier.this.splitAndExecute(progressMonitor), progressMonitor);
			}

			@Override
			protected List<ExecutionParticipant> getElements() {
				return elements;
			}			
		}
		
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(Supplier.this.name()).append(" => ").append(then.name());
		return new Then(nameBuilder.toString());
	}
	
	default Command then(Consumer<? super T> then) {
		List<ExecutionParticipant> elements = new ArrayList<>();
		elements.add(this);
		elements.add(then);
		class Then extends CompoundExecutionParticipant<ExecutionParticipant> implements Command {

			protected Then(String name) {
				super(name, true);
			}
			
			@Override
			public void execute(ProgressMonitor progressMonitor) {
				then.splitAndExecute(Supplier.this.splitAndExecute(progressMonitor), progressMonitor);
			}

			@Override
			protected List<ExecutionParticipant> getElements() {
				return elements;
			}			
		}
		
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(Supplier.this.name()).append(" => ").append(then.name());
		return new Then(nameBuilder.toString());
	}
	
	static <T> Supplier<T> fromFunction(java.util.function.Function<ProgressMonitor, T> function, String name, double size) {
		return new Supplier<T>() {
			
			@Override
			public double size() {
				return size;
			}
			
			@Override
			public String name() {
				return name;
			}
			
			@Override
			public T execute(ProgressMonitor progressMonitor) {
				return function.apply(progressMonitor);
			}
		};
	}
	
	static <T> Supplier<T> fromSupplier(java.util.function.Supplier<T> supplier, String name, double size) {
		return fromFunction((p) -> supplier.get(), name, size);		
	}
	
	static <T> Supplier<T> from(T value, String name) {
		return fromFunction((p) -> value, name, 0);		
	}
		
	static <T> Supplier<T> fromCallable(Callable<T> callable, String name, double size) {
		return new Supplier<T>() {
			
			@Override
			public double size() {
				return size;
			}
			
			@Override
			public String name() {
				return name;
			}
			
			@Override
			public T execute(ProgressMonitor progressMonitor) {
				try {
					return callable.call();
				} catch (Exception e) {
					throw new ExecutionException(e, this);
				}
			}
		};
	}
	
	/**
	 * Diagnoses, executes, commits/rollsback, closes.
	 * @param progressMonitor
	 * @return
	 */
	@Override
	default public T apply(ProgressMonitor progressMonitor) {
		try {
			progressMonitor.setWorkRemaining(size()*3); // diagnose, execute, commit or rollback
			diagnose(progressMonitor.split("Diagnosing", size())).checkError("Diagnostic failed: "+name());
			try {
				T result = execute(progressMonitor.split("Executing", size()));
				commit(progressMonitor.split("Committing", size()));
				return result;
			} catch (Exception e) {
				try {
					rollback(progressMonitor.split("Rolling back: "+e, size(), e));
				} catch (Exception re) {
					e.addSuppressed(re);
				}
				throw e;
			}
		} finally {
			close();
		}
	}
	
	/**
	 * Calls apply() with a {@link NullProgressMonitor}.
	 * @return
	 */
	default java.util.function.Supplier<T> asSupplier() {
		return () -> apply(new NullProgressMonitor());
	}
	
	/**
	 * Creates work, diagnoses it, executes, commits if there is no exception and rolls back if there is, closes, returns result.
	 * Uses  null progress monitor.
	 * @return
	 */
	default Callable<T> asCallable() {
		return new Callable<T>() {
	
			@Override
			public T call() throws Exception {
				try (ProgressMonitor progressMonitor = new NullProgressMonitor()) {
					progressMonitor.setWorkRemaining(size()*3); // diagnose, execute, commit or rollback
					diagnose(progressMonitor.split("Diagnosing", size())).checkError("Diagnostic failed: "+name());
					try {
						T result = execute(progressMonitor.split("Executing", size()));
						commit(progressMonitor.split("Committing", size()));
						return result;
					} catch (Exception e) {
						try {
							rollback(progressMonitor.split("Rolling back: "+e, size(), e));
						} catch (Exception re) {
							e.addSuppressed(re);
						}
						throw e;
					}
				} finally {
					close();
				}
			}
		};
	}	
	
	public record FunctionResult<T,R>(T argument, R result) {
		
	}
	
	/**
	 * @return Function which executes this supplier and returns the argument and the result as a {@link FunctionResult}.
	 * This method can be used for embedding suppliers into function chains.
	 */
	default <V> Function<V,FunctionResult<V,T>> asFunction() {
		return new Function<V,FunctionResult<V,T>>() {
			
			@Override
			public FunctionResult<V,T> execute(V arg, ProgressMonitor progressMonitor) {
				T result = Supplier.this.execute(progressMonitor);
				return new FunctionResult<V, T>(arg, result);
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Supplier.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() {
				Supplier.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) {
				Supplier.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) {
				return Supplier.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Supplier.this.size();
			}
			
			@Override
			public String name() {
				return Supplier.this.name();
			}
			
		};
	}
	
	/**
	 * Combines with progress monitor
	 * @param <T>
	 * @param <U>
	 * @param <R>
	 */
	interface Combiner<T,U,R> {
		
	    R combine(T t, U u, ProgressMonitor progressMonitor);		
		
	}	
	
	/**
	 * @return Function which executes this supplier and returns the argument and the result combined by the combiner
	 * This method can be used for embedding suppliers into function chains.
	 */
	default <V,R> Function<V,R> asFunction(BiFunction<V,T,R> combiner) {
		return new Function<V,R>() {
			
			@Override
			public R execute(V arg, ProgressMonitor progressMonitor) {
				T result = Supplier.this.execute(progressMonitor);
				return combiner.apply(arg, result);
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Supplier.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() {
				Supplier.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) {
				Supplier.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) {
				return Supplier.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Supplier.this.size();
			}
			
			@Override
			public String name() {
				return Supplier.this.name();
			}
			
		};
	}
	
	
	/**
	 * @return Function which executes this supplier and returns the argument and the result combined by the combiner
	 * This method can be used for embedding suppliers into function chains.
	 */
	default <V,R> Function<V,R> asFunction(Combiner<V,T,R> combiner) {
		return new Function<V,R>() {
			
			@Override
			public R execute(V arg, ProgressMonitor progressMonitor) {
				T result = Supplier.this.execute(progressMonitor);
				return combiner.combine(arg, result, progressMonitor);
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Supplier.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() {
				Supplier.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) {
				Supplier.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) {
				return Supplier.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Supplier.this.size();
			}
			
			@Override
			public String name() {
				return Supplier.this.name();
			}
			
		};
	}
	
	/**
	 * Executes full supplier lifecycle - diagnose, execute, commit/rollback, close.
	 * @param monitor
	 * @param diagnosticConsumer TODO
	 * @param context
	 * @param component
	 * @return
	 */
	default T call(ProgressMonitor monitor, java.util.function.Consumer<Diagnostic> diagnosticConsumer, Status... failOnStatuses) {
		try (ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling supplier", 3)) {
			Diagnostic diagnostic = splitAndDiagnose(progressMonitor);
			if (diagnosticConsumer != null) {
				diagnosticConsumer.accept(diagnostic);
			}
			Status status = diagnostic.getStatus();
			if (failOnStatuses.length == 0) {
				if (status == Status.FAIL) {
					throw new DiagnosticException(diagnostic);
				}
			} else {
				for (Status failStatus: failOnStatuses) {
					if (status == failStatus) {
						throw new DiagnosticException(diagnostic);
					}					
				}
			}
			
			try {
				T result = splitAndExecute(progressMonitor);
				splitAndCommit(progressMonitor);
				return result;
			} catch (Exception e) {
				splitAndRollback(progressMonitor);
				throw e;
			}
		} finally {
			close();
		}
	}
	
	
}
