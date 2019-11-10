package org.nasdanika.common;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * By convention Supplier does not split the monitor for itself - this is the responsibility of the caller.
 * 
 * {@link ProgressMonitor} allocation pattern - the caller creates a monitor with work's size and name and passes it to work's execute:
 * 
 * ```
 * try (Supplier supplier = ...; ProgressMonitor workMonitor = monitor.split(work.getName(), work.getSize(), work)) {
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
public interface Function<T,R> extends ExecutionParticipant, ExecutionParticipantInfo {
	
	Supplier<Object> EMPTY = new Supplier<Object>() {

		@Override
		public double size() {
			return 0;
		}
		
		@Override
		public String getName() {
			return "Empty supplier";
		}

		@Override
		public Object execute(ProgressMonitor monitor) throws Exception {
			return null;
		}
		
	};
	
	@SuppressWarnings("unchecked")
	static <T> Supplier<T> empty() {
		return (Supplier<T>) EMPTY;
	}
	
	default <R> Supplier<R> adapt(Function<T,R> adapter) {
		return new Supplier<R>() {
			
			@Override
			public R execute(ProgressMonitor progressMonitor) throws Exception {
				return adapter.apply(Supplier.this.execute(progressMonitor));
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Supplier.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Supplier.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Supplier.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Supplier.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Supplier.this.size();
			}
			
			@Override
			public String getName() {
				return Supplier.this.getName();
			}
			
		};
	}
	
	/**
	 * Executes the _LegacyCommandToRemove.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;	
	
	default T splitAndExecute(ProgressMonitor progressMonitor) throws Exception {
		return execute(split(progressMonitor));
	}
	
	/**
	 * Returns a composed work factory that first executes this work factor work, 
	 * and then executes the {@code then} function work with the result.
	 *
	 * @param <V> the type of work result of the {@code then} function, and of the composed function's work
	 * @param then
	 * @return
	 * @throws Exception 
	 */
	default <V> Supplier<V> then(Function<T,V> after) throws Exception {
		return after.create(this);
	}
	
	static <T> SupplierFactory<T> fromBiFunction(BiFunction<Context, ProgressMonitor, T> biFunction, String name, double size) {
		return new SupplierFactory<T>() {
	
			@Override
			public Supplier<T> create(Context context) throws Exception {
				return new Supplier<T>() {
	
					@Override
					public T execute(ProgressMonitor progressMonitor) throws Exception {
						return biFunction.apply(context, progressMonitor);
					}
	
					@Override
					public double size() {
						return size;
					}
	
					@Override
					public String getName() {
						return name;
					}
					
				};
			}
			
		};
	}
	
	static <T> SupplierFactory<T> fromSupplier(Supplier<T> supplier, String name, double size) {
		return fromBiFunction((c,p) -> supplier.get(), name, size);		
	}
	
	static <T> SupplierFactory<T> from(T value, String name) {
		return fromBiFunction((c,p) -> value, name, 0);		
	}
		
	static <T> SupplierFactory<T> fromCallable(Callable<T> callable, String name, double size) {
		return new SupplierFactory<T>() {
	
			@Override
			public Supplier<T> create(Context context) throws Exception {
				return new Supplier<T>() {
	
					@Override
					public T execute(ProgressMonitor progressMonitor) throws Exception {
						return callable.call();
					}
	
					@Override
					public double size() {
						return size;
					}
	
					@Override
					public String getName() {
						return name;
					}
					
				};
			}
			
		};
	}
		
	static SupplierFactory<Void> fromRunnable(Runnable runnable, String name, double size) {
		return fromBiFunction((c,p) -> { runnable.run(); return null; }, name, size);		
	}
	
	/**
	 * Creates work, diagnoses it, executes, commits if there is no exception and rolls back if there is, closes, returns result.
	 * @return
	 */
	default BiFunction<Context,ProgressMonitor,T> asBiFunction() {
		return new BiFunction<Context, ProgressMonitor, T>() {
	
			@Override
			public T apply(Context context, ProgressMonitor progressMonitor) {
				try (Supplier<T> work = create(context)) {
					progressMonitor.setWorkRemaining(3); // diagnose, execute, commit or rollback
					work.diagnose(progressMonitor.split("Diagnosing", 1)).checkError("Diagnostic failed: "+work.getName());
					try {
						T result = work.execute(progressMonitor.split("Executing", 1));
						work.commit(progressMonitor.split("Committing", 1));
						return result;
					} catch (Exception e) {
						try {
							work.rollback(progressMonitor.split("Rolling back: "+e, 1, e));
						} catch (Exception re) {
							e.addSuppressed(re);
						}
						throw e;
					}
				} catch (DiagnosticException e) {
					throw e;
				} catch (Exception e) {
					throw new NasdanikaException(e);
				}
			}
		};
	}
	
	/**
	 * Calls asBiFunction() with a {@link NullProgressMonitor}.
	 * @return
	 */
	default java.util.function.Function<Context,T> asFunction() {
		return context -> asBiFunction().apply(context, new NullProgressMonitor());
	}
	
	/**
	 * Calls asFunction() with an empty context.
	 * @return
	 */
	default Supplier<T> asSupplier() {
		return () -> asFunction().apply(Context.EMPTY_CONTEXT);
	}
	
	/**
	 * Creates work, diagnoses it, executes, commits if there is no exception and rolls back if there is, closes, returns result.
	 * Uses empty context and null progress monitor.
	 * @return
	 */
	default Callable<T> asCallable() {
		return new Callable<T>() {
	
			@Override
			public T call() throws Exception {
				try (Supplier<T> work = create(Context.EMPTY_CONTEXT); ProgressMonitor progressMonitor = new NullProgressMonitor()) {
					progressMonitor.setWorkRemaining(3); // diagnose, execute, commit or rollback
					work.diagnose(progressMonitor.split("Diagnosing", 1)).checkError("Diagnostic failed: "+work.getName());
					try {
						T result = work.execute(progressMonitor.split("Executing", 1));
						work.commit(progressMonitor.split("Committing", 1));
						return result;
					} catch (Exception e) {
						try {
							work.rollback(progressMonitor.split("Rolling back: "+e, 1, e));
						} catch (Exception re) {
							e.addSuppressed(re);
						}
						throw e;
					}
				}
			}
		};
	}	
	
	
}
