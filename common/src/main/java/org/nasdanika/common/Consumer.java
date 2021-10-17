package org.nasdanika.common;

import java.util.function.BiConsumer;

/**
 * @author Pavel Vlasov
 */
public interface Consumer<T> extends ExecutionParticipant, ExecutionParticipantInfo, BiConsumer<T, ProgressMonitor> {
		
	/**
	 * Executes Consumer.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	void execute(T arg, ProgressMonitor progressMonitor) throws Exception;	
	
	default void splitAndExecute(T arg, ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Executing "+name())) {
			execute(arg, subMonitor);
		}
	}	
	
	default void splitAndExecute(T arg, double size, ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(size, progressMonitor, "Executing "+name())) {
			execute(arg, subMonitor);
		}
	}	
	
	Consumer<Object> NOP = new Consumer<Object>() {

		@Override
		public double size() {
			return 0;
		}
		
		@Override
		public String name() {
			return "No operation consumer";
		}

		@Override
		public void execute(Object arg, ProgressMonitor monitor) throws Exception {
			// NOP
		}
		
	};
	
	@SuppressWarnings("unchecked")
	static <T> Consumer<T> nop() {
		return (Consumer<T>) NOP;
	}
	
	@Override
	default void accept(T arg, ProgressMonitor progressMonitor) {
		try {
			progressMonitor.setWorkRemaining(size()*3); // diagnose, execute, commit or rollback
			diagnose(progressMonitor.split("Diagnosing", size())).checkError("Diagnostic failed: "+name());
			try {
				execute(arg, progressMonitor.split("Executing", size()));
				commit(progressMonitor.split("Committing", size()));
			} catch (Exception e) {
				try {
					rollback(progressMonitor.split("Rolling back: "+e, size(), e));
				} catch (Exception re) {
					e.addSuppressed(re);
				}
				throw e;
			}
		} catch (DiagnosticException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		} finally {
			try {
				close();
			} catch (Exception e) {
				throw new NasdanikaException(e);
			}
		}		
	}
	
	default <V> Consumer<V> before(java.util.function.Function<V,T> before) {
		return new Consumer<V>() {
			
			@Override
			public void execute(V arg, ProgressMonitor progressMonitor) throws Exception {
				Consumer.this.execute(before.apply(arg), progressMonitor);
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Consumer.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Consumer.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Consumer.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Consumer.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Consumer.this.size();
			}
			
			@Override
			public String name() {
				return Consumer.this.name();
			}
			
		};
	}
		
	/**
	 * @return Function which executes this consumer and returns the argument. 
	 * This method can be used for chaining consumers.
	 */
	default Function<T,T> asFunction() {
		return new Function<T,T>() {
			
			@Override
			public T execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				Consumer.this.execute(arg, progressMonitor);
				return arg;
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Consumer.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Consumer.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Consumer.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Consumer.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Consumer.this.size();
			}
			
			@Override
			public String name() {
				return Consumer.this.name();
			}
			
		};
	}
	
	/**
	 * @return Function which executes this consumer passing BiSupplier.getFirst() (T) and returns getSecond() (R). 
	 * This method can be used for chaining consumers.
	 */
	default <R> Function<BiSupplier<T,R>,R> asBiSupplierFunction() {
		return new Function<BiSupplier<T,R>,R>() {
			
			@Override
			public R execute(BiSupplier<T,R> input, ProgressMonitor progressMonitor) throws Exception {
				Consumer.this.execute(input.getFirst(), progressMonitor);
				return input.getSecond();
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Consumer.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Consumer.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Consumer.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Consumer.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Consumer.this.size();
			}
			
			@Override
			public String name() {
				return Consumer.this.name();
			}
			
		};
	}
				
	static <T> Consumer<T> fromBiConsumer(BiConsumer<T,ProgressMonitor> biConsumer, String name, double size) {
		return new Consumer<T>() {

			@Override
			public double size() {
				return size;
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public void execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				biConsumer.accept(arg, progressMonitor);				
			}
		};
	}
	
	/**
	 * @param <T>
	 * @param supplier
	 * @param name
	 * @param size
	 * @return
	 */
	static <T> Consumer<T> fromConsumer(java.util.function.Consumer<T> consumer, String name, double size) {
		return fromBiConsumer((arg,progressMonitor) -> consumer.accept(arg), name, size);		
	}
	
}
