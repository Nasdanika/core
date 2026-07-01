import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.groovy.CompiledScriptResourceContentsHandlerCapabilityFactory;
import org.nasdanika.groovy.DslResourceContentsHandlerCapabilityFactory;
import org.nasdanika.groovy.EcoreResourceContentsHandlerCapabilityFactory;
import org.nasdanika.groovy.GroovyResourceFactoryCapabilityFactory;

module org.nasdanika.groovy {

	exports org.nasdanika.groovy;
	exports org.nasdanika.groovy.dsl;
	opens org.nasdanika.groovy.dsl;
	
	requires transitive org.apache.groovy.jsr223;
	requires transitive org.apache.groovy;
	requires transitive org.nasdanika.capability;
	requires transitive java.scripting;
	requires org.apache.commons.lang3;
	requires org.eclipse.emf.ecore;
	
	provides CapabilityFactory with 
		CompiledScriptResourceContentsHandlerCapabilityFactory,
		DslResourceContentsHandlerCapabilityFactory,
		EcoreResourceContentsHandlerCapabilityFactory,
		GroovyResourceFactoryCapabilityFactory;

}