import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.groovy.GroovyCompiledScriptResourceContentsHandlerCapabilityFactory;
import org.nasdanika.groovy.GroovyResourceFactoryCapabilityFactory;

module org.nasdanika.models.productmanagement.dsl.groovy {

	exports org.nasdanika.groovy;
	exports org.nasdanika.groovy.dsl;
	opens org.nasdanika.groovy.dsl;
	
	requires transitive org.apache.groovy.jsr223;
	requires transitive org.apache.groovy;
	requires transitive org.nasdanika.capability;
	requires transitive java.scripting;
	
	provides CapabilityFactory with 
		GroovyCompiledScriptResourceContentsHandlerCapabilityFactory,
		GroovyResourceFactoryCapabilityFactory;

}