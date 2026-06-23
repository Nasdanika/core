import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.groovy.GroovyCompiledScriptResourceContentsHandlerCapabilityFactory;
import org.nasdanika.groovy.GroovyResourceFactoryCapabilityFactory;

module org.nasdanika.groovy{
	
	exports org.nasdanika.groovy;
	
	requires transitive org.apache.groovy.jsr223;
	requires transitive org.apache.groovy;
	requires transitive org.nasdanika.capability;
	requires transitive java.scripting;
	
	opens org.nasdanika.groovy to info.picocli;
	
	provides CapabilityFactory with 
		GroovyCompiledScriptResourceContentsHandlerCapabilityFactory,
		GroovyResourceFactoryCapabilityFactory;
	
}