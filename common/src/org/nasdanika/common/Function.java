package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 
 * @author Pavel Vlasov
 * @param C context type.
 * @param T result type.
 */
public interface Function<T,R> extends ExecutionParticipant, ExecutionParticipantInfo, BiFunction<T,ProgressMonitor,R> {
	
	R execute(T arg, ProgressMonitor progressMonitor) throws Exception;	
	
	default R splitAndExecute(T arg, ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(progressMonitor)) {
			return execute(arg, subMonitor);
		}
	}
	
	Function<Object,Object> NOP = new Function<Object,Object>() {

		@Override
		public double size() {
			return 0;
		}
		
		@Override
		public String name() {
			return "No operation";
		}

		@Override
		public Object execute(Object arg, ProgressMonitor monitor) throws Exception {
			return arg;
		}
		
	};
	
	@SuppressWarnings("unchecked")
	static <V> Function<V,V> nop() {
		return (Function<V,V>) NOP;
	}
	
	default <V> Function<V,R> before(java.util.function.Function<V,T> before) {
		return new Function<V,R>() {
			
			@Override
			public R execute(V arg, ProgressMonitor progressMonitor) throws Exception {
				return Function.this.execute(before.apply(arg), progressMonitor);
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Function.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Function.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Function.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Function.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Function.this.size();
			}
			
			@Override
			public String name() {
				return Function.this.name();
			}
			
		};
	}
	
	default <V> Function<T,V> then(java.util.function.Function<R,V> then) {
		return new Function<T,V>() {
			
			@Override
			public V execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				return then.apply(Function.this.execute(arg, progressMonitor));
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Function.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Function.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Function.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Function.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Function.this.size();
			}
			
			@Override
			public String name() {
				return Function.this.name();
			}
			
		};
	}
	
	default <V> Function<T,V> then(Function<R,V> then) throws Exception {
		List<ExecutionParticipant> elements = new ArrayList<>();
		elements.add(this);
		elements.add(then);
		class Then extends CompoundExecutionParticipant<ExecutionParticipant> implements Function<T,V> {

			protected Then(String name) {
				super(name);
			}
			
			@Override
			public V execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				return then.splitAndExecute(Function.this.splitAndExecute(arg, progressMonitor), progressMonitor);
			}

			@Override
			protected List<ExecutionParticipant> getElements() {
				return elements;
			}			
		}
		
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(Function.this.name()).append(" => ").append(then.name());
		return new Then(nameBuilder.toString());
	}
		
	default Consumer<T> then(Consumer<R> then) throws Exception {
		List<ExecutionParticipant> elements = new ArrayList<>();
		elements.add(this);
		elements.add(then);
		class Then extends CompoundExecutionParticipant<ExecutionParticipant> implements Consumer<T> {

			protected Then(String name) {
				super(name);
			}
			
			@Override
			public void execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				then.splitAndExecute(Function.this.splitAndExecute(arg, progressMonitor), progressMonitor);
			}

			@Override
			protected List<ExecutionParticipant> getElements() {
				return elements;
			}			
		}
		
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(Function.this.name()).append(" => ").append(then.name());
		return new Then(nameBuilder.toString());
	}	
	
	static <T,R> Function<T,R> fromBiFunction(BiFunction<T, ProgressMonitor, R> biFunction, String name, double size) {
		return new Function<T,R>() {

			@Override
			public double size() {
				return size;
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public R execute(T arg, ProgressMonitor progressMonitor) throws Exception {
				return biFunction.apply(arg, progressMonitor);
			}
			
		};
	}
	
	default R apply(T arg, ProgressMonitor progressMonitor) {
		try {
			progressMonitor.setWorkRemaining(size()*3); // diagnose, execute, commit or rollback
			diagnose(progressMonitor.split("Diagnosing", size())).checkError("Diagnostic failed: "+name());
			try {
				R result = execute(arg, progressMonitor.split("Executing", size()));
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
	
	/**
	 * Calls asBiFunction() with a {@link NullProgressMonitor}.
	 * @return
	 */
	default java.util.function.Function<T,R> asFunction() {
		return arg -> apply(arg, new NullProgressMonitor());
	}
		
}
