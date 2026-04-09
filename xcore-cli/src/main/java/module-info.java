import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.xcore.cli.XcoreCommandFactory;

module org.nasdanika.xcore.cli {
	
	requires transitive org.nasdanika.cli;
	requires org.nasdanika.xtext;
	requires org.eclipse.emf.ecore.xcore;
	requires com.google.guice;
	requires org.eclipse.xtext.common.types;
	requires org.apache.log4j;
	
	opens org.nasdanika.xcore.cli to info.picocli, org.nasdanika.cli;

	provides CapabilityFactory with XcoreCommandFactory;
		
}