package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 
 * @author Pavel Vlasov.
 * 
 * This function creates work for all children using the input, then combines results and returns them
 *
 * @param <T> Input type
 * @param <R> Result type
 * @param <U> Child result type
 */
public abstract class CompoundFunction<T,R,U> implements FunctionFactory<T,R> {
	
	protected List<FunctionFactory<T,U>> children = new ArrayList<>();
	
	public void add(FunctionFactory<T,U> child) {
		children.add(child);
	}
	
	protected String getName() {
		return "Compound function";
	}
	
	protected Executor getExecutor(Context context) {
		return context.get(Executor.class);
	}

	@Override
	public SupplierFactory<R> create(SupplierFactory<T> arg) throws Exception {
		Reference<T> argReference = new Reference<>();
		
		List<SupplierFactory<U>> cwfl = new ArrayList<>();
		for (FunctionFactory<T, U> child: children) {
			cwfl.add(child.create(SupplierFactory.fromSupplier(argReference, "Argument", 1)));
		}
		return new SupplierFactory<R>() {

			@Override
			public Supplier<R> create(Context context) throws Exception {
				Supplier<T> argWork = arg.create(context);
				
				CompoundSupplier<R, U> childWork = new CompoundSupplier<R, U>("Child work", getExecutor(context)) {

					@Override
					protected R combine(List<U> results, ProgressMonitor progressMonitor) throws Exception {
						return CompoundFunction.this.combine(results, context, progressMonitor);
					}
				};
				
				for (SupplierFactory<U> cwf: cwfl) {
					childWork.add(cwf.create(context));
				}
				
				return new Supplier<R>() {
					
					@Override
					public Diagnostic diagnose(ProgressMonitor progressMonitor) {
						return Diagnostic.compose(
								"Compound function",
								argWork.diagnose(progressMonitor.split("Diagnosing arg work", argWork.size())),
								childWork.diagnose(progressMonitor.split("Diagnosing child work", childWork.size())));
					}
					
					@Override
					public void commit(ProgressMonitor progressMonitor) throws Exception {
						argWork.commit(progressMonitor.split("Committing arg work", argWork.size()));
						childWork.commit(progressMonitor.split("Committing child work", childWork.size()));
					}
					
					@Override
					public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
						boolean childRollbackResult = childWork.rollback(progressMonitor.split("Rolling child work", childWork.size()));
						boolean argRollbackResult = argWork.rollback(progressMonitor.split("Rolling back arg work", argWork.size()));
						return childRollbackResult && argRollbackResult;
					}
					
					@Override
					public void close() throws Exception {
						argWork.close();
						childWork.close();
					}

					@Override
					public R execute(ProgressMonitor progressMonitor) throws Exception {
						argReference.set(argWork.execute(progressMonitor.split("Executing arg work", argWork.size())));
						return childWork.execute(progressMonitor.split("Executing child work", childWork.size()));
					}

					@Override
					public double size() {
						return argWork.size() + childWork.size();
					}

					@Override
					public String name() {
						return CompoundFunction.this.getName();
					}
					
				};
			}
			
		};
	}
	
	protected abstract R combine(List<U> results, Context context, ProgressMonitor progressMonitor);	

}
