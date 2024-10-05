import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.groovy.GroovyShellCommandFactory;

module org.nasdanika.groovy{
	
	exports org.nasdanika.groovy;
	
	requires transitive org.nasdanika.cli;
	requires transitive org.apache.groovy.groovysh;
	requires transitive org.apache.groovy;
	
	opens org.nasdanika.groovy to info.picocli;
	
	provides CapabilityFactory with 
		GroovyShellCommandFactory;
	
}