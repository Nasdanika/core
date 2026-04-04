import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.plantuml.ClassDiagramCommandFactory;
import org.nasdanika.plantuml.PlantUmlCommandFactory;

module org.nasdanika.plantuml {
	
	exports org.nasdanika.plantuml;
	
	requires transitive org.nasdanika.cli;
	requires transitive net.sourceforge.plantuml;	
	
	opens org.nasdanika.plantuml to org.nasdanika.cli, info.picocli;
	
	provides CapabilityFactory with 
		PlantUmlCommandFactory,
		ClassDiagramCommandFactory;
	
}