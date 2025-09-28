package org.nasdanika.cli;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Converts diagram to diagram model",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "to-model")
@ParentCommands(Document.Supplier.class)
public class DocumentToModelCommand extends CommandGroup implements EObjectSupplier<EObject> {

	protected DocumentToModelCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@ParentCommand
	private Document.Supplier documentSupplier;

	@Override
	public Collection<EObject> getEObjects(ProgressMonitor progressMonitor) {
		try {
			return Collections.singleton(documentSupplier.getDocument(progressMonitor).toModelDocument());
		} catch (TransformerException | IOException e) {
			throw new CommandLine.ExecutionException(spec.commandLine(), "Error loading document: " + e, e);
		}
	}	

}
