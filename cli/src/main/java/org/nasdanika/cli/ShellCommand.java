package org.nasdanika.cli;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Supplier;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Starts an interactive shell for the parent command. Adds exit command
 */
@Command(
		description = {
			"Starts an interactive shell",
			"or executes commands from input",
			"files or URL's"
		},
		name = "shell")
public class ShellCommand extends CommandBase {
	
	// TODO - input file(s) to execute
	// URL switch - not files, but URL's.
	// Overall progress monitor?
	
	// File format (enum) - text (default), json, yaml - derive from extensions
	
	// History file option
	
	/**
	 * @param root Root command for this shell
	 */
	public ShellCommand(CommandLine root) {
		this.root = root;
	}
	
	private CommandLine root;
	
	protected Supplier<Boolean> createExitCommand() {
		return new ExitCommand();
	}
	
	@Override
	public Integer call() throws Exception {
		Supplier<Boolean> exitCommand = createExitCommand();
		root.addSubcommand(exitCommand);
		PrintWriter oldOut = root.getOut();
        try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {
        	LineReader lineReader = LineReaderBuilder
        			.builder()
                    .terminal(terminal)
                    .build();
        	
        	PrintWriter terminalWriter = terminal.writer();
        	root.setOut(terminalWriter);
			terminalWriter.println(root.getCommandName() + " shell, type -h for help");
        	String prompt = root.getCommandName() + ">";
            while (!exitCommand.get()) {
                String line = lineReader.readLine(prompt);
                if (!Util.isBlank(line)) {
	                String[] lineArgs = parseLine(line); 
	                int result = root.execute(lineArgs);
	                terminalWriter.println(result);
                }
            }
        } finally {
        	root.setOut(oldOut);
        }
		
		return 0;
	}
	
	/**
	 * Copied from https://github.com/apache/ant/blob/master/src/main/org/apache/tools/ant/types/Commandline.java#L482
	 * @param toProcess
	 * @return
	 */
	public static String[] parseLine(String toProcess) {
		if (toProcess == null || toProcess.isEmpty()) {
			// no command? no string
			return new String[0];
		}
		// parse with a simple finite state machine

		final int normal = 0;
		final int inQuote = 1;
		final int inDoubleQuote = 2;
		int state = normal;
		final StringTokenizer tok = new StringTokenizer(toProcess, "\"' ", true);
		final ArrayList<String> result = new ArrayList<>();
		final StringBuilder current = new StringBuilder();
		boolean lastTokenHasBeenQuoted = false;

		while (tok.hasMoreTokens()) {
			String nextTok = tok.nextToken();
			switch (state) {
			case inQuote:
				if ("'".equals(nextTok)) {
					lastTokenHasBeenQuoted = true;
					state = normal;
				} else {
					current.append(nextTok);
				}
				break;
			case inDoubleQuote:
				if ("\"".equals(nextTok)) {
					lastTokenHasBeenQuoted = true;
					state = normal;
				} else {
					current.append(nextTok);
				}
				break;
			default:
				if ("'".equals(nextTok)) {
					state = inQuote;
				} else if ("\"".equals(nextTok)) {
					state = inDoubleQuote;
				} else if (" ".equals(nextTok)) {
					if (lastTokenHasBeenQuoted || current.length() > 0) {
						result.add(current.toString());
						current.setLength(0);
					}
				} else {
					current.append(nextTok);
				}
				lastTokenHasBeenQuoted = false;
				break;
			}
		}
		if (lastTokenHasBeenQuoted || current.length() > 0) {
			result.add(current.toString());
		}
		if (state == inQuote || state == inDoubleQuote) {
			throw new NasdanikaException("unbalanced quotes in " + toProcess);
		}
		return result.toArray(new String[0]);
	}	
	
}
