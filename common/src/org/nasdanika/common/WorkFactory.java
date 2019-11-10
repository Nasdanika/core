package org.nasdanika.common;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Creates work to be executed.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface WorkFactory<T> extends CommandFactory<T> {
		
	/**
	 * Creates work for a given context. Narrows CommandFactory return type.
	 * @param context
	 * @return
	 * @throws Exception
	 */
	Work<T> create(Context context) throws Exception;
		
	/**
     * Returns a composed work factory that first executes this work factor work, 
     * and then executes the {@code then} function work with the result.
     *
     * @param <V> the type of work result of the {@code then} function, and of the composed function's work
	 * @param then
	 * @return
	 * @throws Exception 
	 */
	default <V> WorkFactory<V> then(Function<T,V> after) throws Exception {
		return after.create(this);
	}
	
	static <T> WorkFactory<T> fromBiFunction(BiFunction<Context, ProgressMonitor, T> biFunction, String name, double size) {
		return new WorkFactory<T>() {

			@Override
			public Work<T> create(Context context) throws Exception {
				return new Work<T>() {

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
	
	static <T> WorkFactory<T> fromSupplier(Supplier<T> supplier, String name, double size) {
		return fromBiFunction((c,p) -> supplier.get(), name, size);		
	}
	
	static <T> WorkFactory<T> from(T value, String name) {
		return fromBiFunction((c,p) -> value, name, 0);		
	}
		
	static <T> WorkFactory<T> fromCallable(Callable<T> callable, String name, double size) {
		return new WorkFactory<T>() {

			@Override
			public Work<T> create(Context context) throws Exception {
				return new Work<T>() {

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
		
	static WorkFactory<Void> fromRunnable(Runnable runnable, String name, double size) {
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
				try (Work<T> work = create(context)) {
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
				try (Work<T> work = create(Context.EMPTY_CONTEXT); ProgressMonitor progressMonitor = new NullProgressMonitor()) {
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
