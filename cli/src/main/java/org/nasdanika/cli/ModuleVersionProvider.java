package org.nasdanika.cli;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import picocli.CommandLine.IVersionProvider;

public class ModuleVersionProvider implements IVersionProvider {
	
	private String[] version;

	public ModuleVersionProvider() throws Exception {
		this(ModuleVersionProvider.class);
	}

	protected ModuleVersionProvider(Class<?> clazz) throws Exception {
		Module module = clazz.getModule();		
		List<String> version = new ArrayList<>();
		if (module != null) {
			version.add(module.getDescriptor().toNameAndVersion());
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