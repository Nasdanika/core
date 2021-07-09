package org.nasdanika.exec.tests;

import java.io.InputStream;
import java.util.Date;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class CustomSupplierFactoryTwo implements SupplierFactory<InputStream> {
	
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
				ResultsCollector resultsCollector = context.get(ResultsCollector.class);
				if (resultsCollector != null) {
					resultsCollector.onMyResult(result);
				}
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
