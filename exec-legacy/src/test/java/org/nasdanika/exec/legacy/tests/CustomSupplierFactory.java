package org.nasdanika.exec.tests;

import java.io.InputStream;
import java.util.Date;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class CustomSupplierFactory implements SupplierFactory<InputStream> {
	
	private Consumer<String> resultsCollector;

	public CustomSupplierFactory(java.util.function.Consumer<String> resultsCollector) {
		this.resultsCollector = resultsCollector;
	}

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
				resultsCollector.accept(result);
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
