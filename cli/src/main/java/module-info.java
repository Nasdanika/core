import org.nasdanika.capability.CapabilityFactory;
//import org.nasdanika.cli.CallableElementInvocableCommandFactory;
import org.nasdanika.cli.DrawioCommandFactory;
import org.nasdanika.cli.ElementInvocableCommandFactory;
import org.nasdanika.cli.HelpCommandFactory;
import org.nasdanika.cli.InvokeCommandFactory;
import org.nasdanika.cli.RootCommandFactory;
import org.nasdanika.cli.SaveModelCommandFactory;

module org.nasdanika.cli {
	
	exports org.nasdanika.cli;
	
	requires transitive org.nasdanika.telemetry;
	requires transitive org.nasdanika.drawio;
	requires transitive info.picocli;
	requires org.apache.commons.text;
	requires transitive org.jline;
	requires io.opentelemetry.context;
	requires org.slf4j;
	
	opens org.nasdanika.cli;
	
	provides CapabilityFactory with 
		RootCommandFactory,
		HelpCommandFactory,
		DrawioCommandFactory,
		SaveModelCommandFactory,
		InvokeCommandFactory,
//		CallableElementInvocableCommandFactory, Invocable factory below is more flexible.
		ElementInvocableCommandFactory;
	
}