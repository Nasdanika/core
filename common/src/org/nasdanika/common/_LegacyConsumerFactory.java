package org.nasdanika.common;

/**
 * ConsumerFactory creates a work factory which in turn creates work. The work may be applied to the argument and does not
 * return result. This interface is similar to {@link java.util.function.Consumer}, but allows to perform execution in
 * a {@link Context} with progress reporting and ability to cancel via {@link ProgressMonitor}.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface _LegacyConsumerFactory<T> extends FunctionFactory<T,Void> {
	
	static <T> ConsumerFactory<T> from(java.util.function.Consumer<T> consumer) {
		return new ConsumerFactory<T>() {

			@Override
			public SupplierFactory<Void> create(SupplierFactory<T> arg) throws Exception {
				return new SupplierFactory<Void>() {

					@Override
					public Supplier<Void> create(Context context) throws Exception {
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
