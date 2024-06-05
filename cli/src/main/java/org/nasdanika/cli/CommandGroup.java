package org.nasdanika.cli;

import picocli.CommandLine.ParameterException;
import picocli.CommandLine.ParentCommand;

/**
 * A base class for commands which are used for grouping and do not provide 
 * own functionality but may contain options which can be accessed by 
 * their children via a field annotated with {@link ParentCommand}. 
 * @author Pavel
 *
 */
public class CommandGroup extends CommandBase {

	@Override
	public Integer call() throws Exception {
		throw new ParameterException(spec.commandLine(), "Missing required subcommand");
	}

}
