import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.cli.DrawioCommandFactory;
import org.nasdanika.cli.HelpCommandFactory;
import org.nasdanika.cli.RootCommandFactory;
import org.nasdanika.cli.SaveModelCommandFactory;

module org.nasdanika.cli {
	
	exports org.nasdanika.cli;
	
	requires transitive org.nasdanika.capability;
	requires transitive org.nasdanika.drawio;
	requires transitive info.picocli;
	requires org.apache.commons.text;
	requires transitive org.jline;
	
	opens org.nasdanika.cli to info.picocli, org.nasdanika.html.model.app.gen.cli;
	
	provides CapabilityFactory with 
		RootCommandFactory,
		HelpCommandFactory,
		DrawioCommandFactory,
		SaveModelCommandFactory;
	
}