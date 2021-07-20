package org.nasdanika.exec.tests;

import java.io.InputStream;
import java.util.Date;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class CustomSupplierFactoryThree implements SupplierFactory<InputStream> {
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Custom content supplier";
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				String result = new Date().toString();
				@SuppressWarnings("unchecked")
				java.util.function.Consumer<String> resultsCollector = context.get("results-collector", java.util.function.Consumer.class);
				if (resultsCollector != null) {
					resultsCollector.accept(result);
				}
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
