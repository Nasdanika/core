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
public abstract class CompoundFunction<T,R,U> implements Function<T,R> {
	
	protected List<Function<T,U>> children = new ArrayList<>();
	
	public void add(Function<T,U> child) {
		children.add(child);
	}
	
	protected String getName() {
		return "Compound function";
	}
	
	protected Executor getExecutor(Context context) {
		return context.get(Executor.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkFactory<R> create(WorkFactory<T> arg) throws Exception {
		Object[] argResult = { null };
		
		List<WorkFactory<U>> cwfl = new ArrayList<>();
		for (Function<T, U> child: children) {
			cwfl.add(child.create(WorkFactory.from((T) argResult[0], "Argument", 1)));
		}
		return new WorkFactory<R>() {

			@Override
			public Work<R> create(Context context) throws Exception {
				Work<T> argWork = arg.create(context);
				
				CompoundWork<R, U> childWork = new CompoundWork<R, U>("Child work", getExecutor(context)) {

					@Override
					protected R combine(List<U> results, ProgressMonitor progressMonitor) throws Exception {
						return CompoundFunction.this.combine(results, context, progressMonitor);
					}
				};
				
				for (WorkFactory<U> cwf: cwfl) {
					childWork.add(cwf.create(context));
				}
				
				return new Work<R>() {
					
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
						return argWork.rollback(progressMonitor.split("Rolling back arg work", argWork.size()))
								&& childWork.rollback(progressMonitor.split("Rolling child work", childWork.size()));
					}
					
					@Override
					public void close() throws Exception {
						argWork.close();
						childWork.close();
					}

					@Override
					public R execute(ProgressMonitor progressMonitor) throws Exception {
						argResult[0] = argWork.execute(progressMonitor.split("Executing arg work", argWork.size()));
						return childWork.execute(progressMonitor.split("Executing child work", childWork.size()));
					}

					@Override
					public double size() {
						return argWork.size() + childWork.size();
					}

					@Override
					public String getName() {
						return CompoundFunction.this.getName();
					}
					
				};
			}
			
		};
	}
	
	protected abstract R combine(List<U> results, Context context, ProgressMonitor progressMonitor);	

}
