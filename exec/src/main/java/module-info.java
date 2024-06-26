import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.exec.content.util.ContentEPackageResourceSetCapabilityFactory;
import org.nasdanika.exec.resources.util.ResourcesEPackageResourceSetCapabilityFactory;
import org.nasdanika.exec.util.ExecEPackageResourceSetCapabilityFactory;

module org.nasdanika.exec {
	exports org.nasdanika.exec;
	exports org.nasdanika.exec.content;
	exports org.nasdanika.exec.content.impl;
	exports org.nasdanika.exec.content.util;
	exports org.nasdanika.exec.impl;
	exports org.nasdanika.exec.resources;
	exports org.nasdanika.exec.resources.impl;
	exports org.nasdanika.exec.resources.util;
	exports org.nasdanika.exec.util;
	
	requires transitive org.nasdanika.emf;
	requires info.picocli;
	
	opens org.nasdanika.exec.util to info.picocli;
	
	provides CapabilityFactory with
		ExecEPackageResourceSetCapabilityFactory,
		ContentEPackageResourceSetCapabilityFactory,
		ResourcesEPackageResourceSetCapabilityFactory;
}