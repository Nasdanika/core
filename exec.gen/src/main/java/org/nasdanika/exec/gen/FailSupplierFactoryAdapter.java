package org.nasdanika.exec.gen;

import java.io.InputStream;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.exec.Fail;

public class FailSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {
	
	public FailSupplierFactoryAdapter(Fail fail) {
		setTarget(fail);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}

	@Override
	public Supplier<InputStream> create(Context context) {
		String message = context.interpolateToString(((Fail) getTarget()).getMessage());
		
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "[Fail] " + message;
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) {
				throw new ExecutionException(message, this);
			}
		};
	}
	
	

}
