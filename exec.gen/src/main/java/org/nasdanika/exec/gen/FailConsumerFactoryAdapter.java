package org.nasdanika.exec.gen;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.exec.Fail;
import org.nasdanika.resources.BinaryEntityContainer;

public class FailConsumerFactoryAdapter extends AdapterImpl implements ConsumerFactory<BinaryEntityContainer> {
	
	public FailConsumerFactoryAdapter(Fail fail) {
		setTarget(fail);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}

	@Override
	public Consumer<BinaryEntityContainer> create(Context context) {
		String message = context.interpolateToString(((Fail) getTarget()).getMessage());
		
		return new Consumer<BinaryEntityContainer>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "[Fail] " + message;
			}

			@Override
			public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) {
				throw new NasdanikaException(message);
			}
		};
	}
	
	

}
