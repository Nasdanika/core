package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.ncore.Value;

public class ValueSupplierFactory extends SupplierSupplierFactory<Value> {
	
	public ValueSupplierFactory(Value target) {
		super(target);
	}
		
	// TODO - proper implementation.
	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return new Supplier<Object>() {

			@Override
			public Object execute(ProgressMonitor progressMonitor) throws Exception {
				return target.isInterpolate() ? context.interpolate(target.getValue()) : target.getValue();
			}

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return target.getTitle();
			}
			
		};
	}
	
}