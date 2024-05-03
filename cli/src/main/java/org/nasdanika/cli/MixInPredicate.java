package org.nasdanika.cli;

import picocli.CommandLine;

/**
 * Commands can implement this interface to accept mix-ins
 */
public interface MixInPredicate {
	
	boolean acceptMixIn(CommandLine commandLine, String name, Object mixIn);

}
