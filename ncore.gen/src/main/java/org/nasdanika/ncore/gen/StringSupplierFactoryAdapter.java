package org.nasdanika.ncore.gen;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

/**
 * For Ncore String.
 * @author Pavel
 *
 */
public class StringSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {

	public StringSupplierFactoryAdapter(org.nasdanika.ncore.String string) {
		setTarget(string);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}

	@Override
	public Supplier<InputStream> create(Context context) {
		return new Supplier<InputStream>() {
			
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Ncore String";
			}
	
			@Override
			public InputStream execute(ProgressMonitor progressMonitor) {
				org.nasdanika.ncore.String target = (org.nasdanika.ncore.String) getTarget();
				String text = target.getValue();
				try {
					return Util.toStream(context, text);
				} catch (IOException e) {
					throw new ExecutionException(e, this);
				}
			}
		
		};
		
	}

}
