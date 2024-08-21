package org.nasdanika.cli;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Saves model to a file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "save")
@ParentCommands(EObjectSupplier.class)
public class SaveModelCommand extends CommandBase {
	
	@Parameters(
		index =  "0",	
		arity = "1",
		description = "Output file")
	private File output;

	@ParentCommand
	EObjectSupplier<EObject> eObjectSupplier;
	
	@Mixin
	ProgressMonitorMixIn progressMonitorMixIn;	

	@Override
	public Integer call() throws Exception {
		ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1);
		System.out.println(eObjectSupplier.getEObject(progressMonitor));
		// TODO Auto-generated method stub
		return 0;
	}	

}
