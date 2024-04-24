package org.nasdanika.cli;

import java.util.concurrent.Callable;

import picocli.CommandLine.Command;

/**
 * Base class for Nasdanika CLI commands
 * @author Pavel
 *
 */
@Command(mixinStandardHelpOptions = true)
public abstract class CommandBase implements Callable<Integer> {

}
