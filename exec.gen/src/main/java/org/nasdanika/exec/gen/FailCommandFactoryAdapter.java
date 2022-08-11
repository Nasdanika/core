package org.nasdanika.exec.gen;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.exec.Fail;

public class FailCommandFactoryAdapter extends AdapterImpl implements CommandFactory {
	
	public FailCommandFactoryAdapter(Fail fail) {
		setTarget(fail);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}

	@Override
	public Command create(Context context) {
		String message = context.interpolateToString(((Fail) getTarget()).getMessage());
		
		return new Command() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "[Fail] " + message;
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) {
				throw new NasdanikaException(message);
			}
		};
	}
	
	

}
