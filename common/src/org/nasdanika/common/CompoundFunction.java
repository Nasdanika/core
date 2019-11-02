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
	
	protected String getName(Context context) {
		return "Compound function";
	}
	
	protected Executor getExecutor(Context context) {
		return context.get(Executor.class);
	}

	@Override
	public WorkFactory<R> create(T arg) throws Exception {
		List<WorkFactory<U>> cwfl = new ArrayList<>();
		for (Function<T, U> child: children) {
			cwfl.add(child.create(arg));
		}
		return new WorkFactory<R>() {

			@Override
			public Work<R> create(Context context) throws Exception {
				CompoundWork<R, U> ret = new CompoundWork<R, U>(getName(context), getExecutor(context)) {

					@Override
					protected R combine(List<U> results, ProgressMonitor progressMonitor) throws Exception {
						return CompoundFunction.this.combine(results, context, progressMonitor);
					}
				};
				
				for (WorkFactory<U> cwf: cwfl) {
					ret.add(cwf.create(context));
				}
				
				return ret;
			}
			
		};
	}
	
	protected abstract R combine(List<U> results, Context context, ProgressMonitor progressMonitor);	

}
