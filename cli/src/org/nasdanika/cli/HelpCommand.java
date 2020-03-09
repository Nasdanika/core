package org.nasdanika.cli;

import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fusesource.jansi.HtmlAnsiOutputStream;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;

@Command(
		description = "Outputs usage for all registred commands to console or file as plain text or HTML",
		name = "help",
		versionProvider = BundleVersionProvider.class)
public class HelpCommand extends CommandBase {
	
	private CommandLine root;
	
	public HelpCommand(CommandLine root) {
		this.root = root;
	}
	
	@Option(names = "--output", description = "Output file")
	private File output;
	
	@Option(names = "--html", defaultValue = "false", description = "Output to HTML with ANSI styling")
	private boolean html;
	
	@Option(names = "--header-level", defaultValue = "1", description = "Starting level for HTML header tags in HTML output, the default value is ${DEFAULT_VALUE}.")
	private int level;
	
	private static final int WIDTH = 80;
	
	private void usage(
			List<String> cmdPath, 
			CommandLine cmd,
			PrintStream out) throws IOException {
		
		CommandSpec commandSpec = cmd.getCommandSpec();
		if (html) {
			int hLevel = Math.min(cmdPath.size() + level, 6);
			out.println("<h" + hLevel + ">" + cmd.getCommandName() + "</h" + hLevel + ">");
			out.print("<p><strong>Version:</strong>: ");
			for (String vs: commandSpec.version()) {
				out.print(vs);
				out.print(" ");
			}
			out.print("</p>");
			
			out.println("<pre style=\"background:black;color:white;width:" + WIDTH + "ch\">");
			try (HtmlAnsiOutputStream haos = new HtmlAnsiOutputStream(new FilterOutputStream(out) { @Override public void close() {} })) {
				try (PrintStream ps = new PrintStream(haos)) {
					cmd.usage(ps, Help.Ansi.ON);
				}
				haos.flush();
			}
			out.println("</pre>");
		} else {
			out.println();
			for (int i = 0; i < WIDTH; ++i) {
				out.print("*");
			}
			out.println();
			out.print("* ");
			StringBuilder hb = new StringBuilder();
			for (String ps: cmdPath) {
				hb.append(ps).append(" > ");
			}
			hb.append(cmd.getCommandName());
			hb.append(" v. ");
			for (String vs: commandSpec.version()) {
				hb.append(vs).append(" ");
			}
			
			for (int i=0, padding = (WIDTH - hb.length() - 3)/2; i < padding; ++i) {
				hb.append(" ");
			}
			for (int i = 0, padding = WIDTH - hb.length() -1; i < padding; ++i) {
				out.print(" ");
			}
			out.print(hb.toString());
			out.println("*");
			for (int i = 0; i < WIDTH; ++i) {
				out.print("*");
			}
			cmd.usage(out);
		}		
		
		List<String> cPath = new ArrayList<>();
		cPath.add(cmd.getCommandName());
		for (CommandLine subCommand: cmd.getSubcommands().values()) {
			usage(cPath, subCommand, out);
		}
	}
	
	@Override
	public Object call() throws Exception {
		if (output == null) {
			usage(Collections.emptyList(), root, System.out);
		} else {
			try (PrintStream out = new PrintStream(output)) {
				usage(Collections.emptyList(), root, out);
			}
		}
		return 0;
	}

}
