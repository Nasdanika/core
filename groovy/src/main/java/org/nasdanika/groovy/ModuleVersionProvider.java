package org.nasdanika.groovy;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import picocli.CommandLine.IVersionProvider;

public class ModuleVersionProvider implements IVersionProvider {
	
	protected Module getModule() {
		return getClass().getModule();
	}
	
	@Override
	public String[] getVersion() throws Exception {
		Module module = getModule();		
		List<String> version = new ArrayList<>();
		if (module != null) {
			version.add(module.getDescriptor().toNameAndVersion());
		
			InputStream gitPropertiesStream = module.getClassLoader().getResourceAsStream("META-INF/git.properties");
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
		}
		
		return version.toArray(new String[version.size()]);
	}

}