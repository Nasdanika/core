import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.cli.DocumentToModelCommandFactory;
//import org.nasdanika.cli.CallableElementInvocableCommandFactory;
import org.nasdanika.cli.DrawioCommandFactory;
import org.nasdanika.cli.ElementInvocableCommandFactory;
import org.nasdanika.cli.HelpCommandFactory;
import org.nasdanika.cli.InvokeCommandFactory;
import org.nasdanika.cli.RootCommandFactory;
import org.nasdanika.cli.SaveDocumentCommandFactory;
import org.nasdanika.cli.SaveModelCommandFactory;

module org.nasdanika.cli {
			
	exports org.nasdanika.cli;
	
	requires transitive org.nasdanika.telemetry;
	requires transitive org.nasdanika.drawio;
//	requires transitive info.picocli;
	requires org.apache.commons.text;
	requires transitive org.jline;
	requires transitive io.opentelemetry.context;
	
	opens org.nasdanika.cli;
	
	provides CapabilityFactory with 
		RootCommandFactory,
		HelpCommandFactory,
		DrawioCommandFactory,
		SaveModelCommandFactory,
		SaveDocumentCommandFactory,
		DocumentToModelCommandFactory,
		InvokeCommandFactory,
//		CallableElementInvocableCommandFactory, Invocable factory below is more flexible.
		ElementInvocableCommandFactory;
	
	// Waiting for Picocli 4.8.0 with Nasdanika contributions
	// Bundling picocli with this module in the meantime
    exports picocli;
    requires static java.sql;	
	
}