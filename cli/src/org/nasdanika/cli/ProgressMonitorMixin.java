package org.nasdanika.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ProgressRecorder;

import picocli.CommandLine.Option;

/**
 * Mixes in progress monitor.
 * @author Pavel
 *
 */
public class ProgressMonitorMixin {
	
	@Option(names = {"-p", "--progress"}, description = "Output file for progress monitor")
	private File progressOutput;
	
	@Option(names = {"-j", "--json"}, description = "Output progress in JSON")
	private boolean jsonProgress;
	
	public ProgressMonitor createProgressMonitor(double size) throws Exception {
		if (progressOutput == null) {
			if (jsonProgress) {
				return new ProgressRecorder() {
					
					@Override
					public void close() {
						System.out.println(toJSON().toString(4));
						super.close();
					}
					
				};			
			}
			return new PrintStreamProgressMonitor(System.out, 0, 4, false);
		}
		
		if (jsonProgress) {
			return new ProgressRecorder() {
				
				@Override
				public void close() {
					try (Writer w = new FileWriter(progressOutput)) {
						toJSON().write(w, 4, 0);
					} catch (IOException e) {
						System.err.println("Error closing progress writer");
						e.printStackTrace();
					}
					super.close();
				}
				
			};			
		}
		return new PrintStreamProgressMonitor(new PrintStream(progressOutput), 0, 4, true);		
	}	

}
