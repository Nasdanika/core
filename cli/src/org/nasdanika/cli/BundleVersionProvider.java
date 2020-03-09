package org.nasdanika.cli;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import picocli.CommandLine.IVersionProvider;

public class BundleVersionProvider implements IVersionProvider {
	
	private String[] version;

	public BundleVersionProvider() {
		this(BundleVersionProvider.class);
	}

	protected BundleVersionProvider(Class<?> clazz) {
		Bundle bundle = FrameworkUtil.getBundle(clazz);
		version = new String[] { bundle == null ? "" : bundle.getSymbolicName() + " " + bundle.getVersion() };
	}
	
	@Override
	public String[] getVersion() throws Exception {
		return version;
	}

}
