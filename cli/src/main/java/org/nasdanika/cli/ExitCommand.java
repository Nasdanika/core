package org.nasdanika.cli;

import java.util.function.Supplier;

import picocli.CommandLine.Command;

@Command(
		description = "Exits shell",
		name = "exit",
		versionProvider = ModuleVersionProvider.class)
public class ExitCommand extends CommandBase implements Supplier<Boolean> {
	
	private boolean value;
	
	public Boolean get() {
		return value;
	}
	
	@Override
	public Integer call() throws Exception {
		value = true;
		return 0;
	}

}
