package org.nasdanika.cli.ext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import picocli.CommandLine.IVersionProvider;

public class BundleVersionProvider implements IVersionProvider {
	
	private String[] version;

	public BundleVersionProvider() throws Exception {
		this(BundleVersionProvider.class);
	}

	protected BundleVersionProvider(Class<?> clazz) throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(clazz);
		List<String> version = new ArrayList<>();
		if (bundle != null) {
			version.add(bundle.getSymbolicName() + " " + bundle.getVersion());
		}
		
		InputStream gitPropertiesStream = clazz.getClassLoader().getResourceAsStream("META-INF/git.properties");
		if (gitPropertiesStream != null) {
			Properties gitProperties = new Properties();
			try {
				gitProperties.load(gitPropertiesStream);
			} finally {
				gitPropertiesStream.close();
			}
						
			//git.commit.id
			//git.total.commit.count
			//git.commit.time
			
			version.add(gitProperties.getProperty("git.branch") + " " + gitProperties.getProperty("git.commit.id.abbrev"));
		}
		
		this.version = version.toArray(new String[version.size()]);
	}
	
	@Override
	public String[] getVersion() throws Exception {
		return version;
	}

}
