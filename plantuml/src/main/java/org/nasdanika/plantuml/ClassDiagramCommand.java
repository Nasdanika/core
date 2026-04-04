package org.nasdanika.plantuml;

import java.util.Collection;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.EModelElementSupplier;
import org.nasdanika.common.ProgressMonitor;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Converts class diagrams to Ecore model elements",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "class")
@ParentCommands(BlockUmlSupplier.class)
public class ClassDiagramCommand extends CommandGroup implements EModelElementSupplier<EModelElement> {
	
	@ParentCommand
	BlockUmlSupplier blockUmlSupplier;

	@Override
	public Collection<EModelElement> getEObjects(ProgressMonitor progressMonitor) {
		return blockUmlSupplier.get().stream()
				.map(BlockUml::getDiagram)
				.filter(ClassDiagram.class::isInstance)
				.map(ClassDiagram.class::cast)
				.map(this::loadClassDiagram)
				.toList(); 
	}
	
	// nsURI and name - map, linked
	
	protected EModelElement loadClassDiagram(ClassDiagram classDiagram) {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName("Test");
		return ePackage;
	}

}
