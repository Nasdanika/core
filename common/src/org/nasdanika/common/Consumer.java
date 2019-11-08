package org.nasdanika.common;

/**
 * Consumer creates a work factory which in turn creates work. The work may be applied to the argument and does not
 * return result. This interface is similar to {@link java.util.function.Consumer}, but allows to perform execution in
 * a {@link Context} with progress reporting and ability to cancel via {@link ProgressMonitor}.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Consumer<T> extends Function<T,Void> {
	
	static <T> Consumer<T> from(java.util.function.Consumer<T> consumer) {
		return new Consumer<T>() {

			@Override
			public WorkFactory<Void> create(WorkFactory<T> arg) throws Exception {
				return new WorkFactory<Void>() {

					@Override
					public Work<Void> create(Context context) throws Exception {
						return arg.create(context).adapt(r -> {
							consumer.accept(r);
							return null;
						});
					}
				};
			}
			
		};
	}

}
