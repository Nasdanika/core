package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

/**
 * Base class for commands which execute models by adapting them to a specific adapter type and then
 * adapting that adapter to {@link CommandFactory}.
 * @author Pavel
 *
 * @param <T>
 * @param <A>
 */
public abstract class AdapterModelCommand<T extends EObject, A> extends ModelCommand<T> {
	
	/**
	 * @return Type of the adapter used to execute the model.
	 */
	protected abstract Class<A> getAdapterType();
	
	protected abstract CommandFactory createCommandFactory(A adapter);

	@Override
	protected ConsumerFactory<T> getConsumerFactory() {
		return new ConsumerFactory<T>() {

			@Override
			public Consumer<T> create(Context context) throws Exception {
				return new Consumer<T>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Adapt and execute";
					}

					@Override
					public void execute(T element, ProgressMonitor progressMonitor) throws Exception {
						A adapter = EObjectAdaptable.adaptTo(element, getAdapterType());
						if (adapter == null) {
							throw new NasdanikaException("Cannot adapt " + element + " to " + getAdapterType());
						}
						CommandFactory cf = createCommandFactory(adapter);
						org.nasdanika.common.Command command = cf.create(context);
						command.splitAndExecute(size(), progressMonitor);
					}
					
				};
			}
		};
	}

}
