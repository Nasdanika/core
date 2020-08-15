package org.nasdanika.exec.content;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader.Factory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

/**
 * Loads content from URL.
 * @author Pavel
 *
 */
public class Resource implements SupplierFactory<InputStream> {
	
	private String url;

	public Resource(Object spec) {
		if (spec instanceof String) {
			this.url = (String) spec;
		}
		throw new IllegalArgumentException("Unsupported resource configuration: " + spec);
	}

	public Resource(Factory factory, String type, Object config, URL base, ProgressMonitor progressMonitor) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		URL base = new File(".").toURI().toURL();
		URL theURL = new URL(base, context.interpolateToString(url));
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Resource: " + theURL;
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				return theURL.openStream();
			}
			
		};
	}

}
