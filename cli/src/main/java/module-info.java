import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.cli.HelpCommandFactory;
import org.nasdanika.cli.RootCommandFactory;

module org.nasdanika.cli {
	
	exports org.nasdanika.cli;
	
	requires transitive org.nasdanika.capability;
	requires transitive info.picocli;
	requires org.apache.commons.text;
	
	opens org.nasdanika.cli to info.picocli;
	
	provides CapabilityFactory with 
		RootCommandFactory,
		HelpCommandFactory;
	
}