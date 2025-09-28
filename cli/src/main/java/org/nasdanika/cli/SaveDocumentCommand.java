package org.nasdanika.cli;

import java.io.File;
import java.nio.file.Files;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Description;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Saves diagram to a file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "save")
@ParentCommands(Document.Supplier.class)
@Description(icon = "https://img.icons8.com/dusk/20/save--v1.png")
public class SaveDocumentCommand extends CommandBase {

	protected SaveDocumentCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@Parameters(
		index =  "0",	
		arity = "1",
		description = "Output file")
	private File output;
	
	@Option(
			names = "--compress", 
			description = "Compress output",
			negatable = true,
			defaultValue = "true")
	private boolean compress;	

	@ParentCommand
	private Document.Supplier documentSupplier;
	
	@Mixin
	private ProgressMonitorMixIn progressMonitorMixIn;

	@Override
	public Integer call() throws Exception {
		ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1);
		String docStr = documentSupplier.getDocument(progressMonitor).save(compress);
		Files.writeString(output.toPath(), docStr);
		return 0;
	}	

}
