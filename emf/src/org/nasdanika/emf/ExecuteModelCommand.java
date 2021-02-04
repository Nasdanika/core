package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CommandFactory;

import picocli.CommandLine.Command;

@Command(
		description = "Loads a model, adapts to CommandFactory, then creates and executes a command",
		name = "execute-model")
public class ExecuteModelCommand extends AdapterModelCommand<EObject,CommandFactory> {

	@Override
	protected Class<CommandFactory> getAdapterType() {
		return CommandFactory.class;
	}

	@Override
	protected CommandFactory createCommandFactory(CommandFactory adapter) {
		return adapter;
	}
	

}
