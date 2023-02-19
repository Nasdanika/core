package org.nasdanika.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.URI;
import org.json.JSONObject;
import org.nasdanika.common.Command;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;

/**
 * Base class for plugins which execute {@link Command}'s. 
 * This class creates a {@link Context} and a {@link ProgressMonitor} and then executes a command returned from createCommand() method.
 */
public abstract class AbstractCommandMojo extends AbstractMojo {

	/**
	 * File to output progress. If not set, progress is output to the log.
	 */
	@Parameter()	
	private File progressOutput;	

	/**
	 * File to output diagnostic. If not set, diagnostic is output to the log.
	 */
	@Parameter()	
	private File diagnosticOutput;	
	
	/**
	 * Statuses on which to fail execution. FAIL if empty. Available statuses: FAIL, ERROR, WARNING, SUCCESS, INFO, CANCEL
	 */
    @Parameter( )
    private Status[] failStatuses;
    
    /**
     * URL's of YAML or JSON resources which are loaded as contexts. if the URL ends with .yaml or .yml then it is treated as YAML, as a JSON object otherwise. 
     */
    @Parameter()
    private List<String> contexts;
    	
//	@Parameter(name = "json-progress")	
//	private boolean jsonProgress;	
	
	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	protected MavenProject project;	
	
	
	/**
	 * Creates a context with {@link MavenProject} service.
	 * Then composes it with contexts loaded from context 
	 * @return
	 * @throws IOException 
	 * @throws MojoExecutionException 
	 */
	@SuppressWarnings("unchecked")
	protected Context createContext() throws IOException, MojoExecutionException {
		Context context = Context.singleton(MavenProject.class, project);
		
		Map<Object, Object> pc = getPluginContext();
		if (pc != null) {
			Context wpc = Context.wrap(pc::get); 
			context = context.compose(wpc);
		}
		
		if (contexts != null && !contexts.isEmpty()) {
			File baseDir = project.getBasedir();
			URI baseDirURI = URI.createFileURI(baseDir.getAbsolutePath()).appendSegment("");
			
			for (String ctxLocation: contexts) {
				URI ctxURI = URI.createURI(ctxLocation).resolve(baseDirURI);
				String lastSegment = ctxURI.lastSegment().toLowerCase();
				if (lastSegment.endsWith(".yml") || lastSegment.endsWith(".yaml")) {
					Object yamlObj = DefaultConverter.INSTANCE.loadYAML(ctxURI);
					if (yamlObj instanceof Map) {
						Map<Object, Object> yamlMap = (Map<Object, Object>) yamlObj;						
						context = context.compose(Context.wrap(context.interpolate(yamlMap)::get));
					} else {
						String message = "Context is not a YAML map: " + ctxURI;
						getLog().error(message);
						throw new MojoExecutionException(message); 
					}
				} else {
					JSONObject jsonObject = DefaultConverter.INSTANCE.loadJSONObject(ctxURI);
					Map<String, Object> jsonMap = jsonObject.toMap();
					context = context.compose(Context.wrap(context.interpolate(jsonMap)::get));
				}
			}
		}
		
		return context;
	}
	
	/**
	 * Creates a progress monitor outputting either to the log or to a progress output file.
	 * @return
	 * @throws FileNotFoundException
	 */
	protected ProgressMonitor createProgressMonitor() throws FileNotFoundException {
		if (progressOutput == null) {
			return new LogProgressMonitor(getLog(), 0, 2);
		}
		
//		if (jsonProgress) {
//			// JSON output progress monitor
//			
//		}
		File parent = progressOutput.getParentFile();
		if (parent != null) {
			parent.mkdirs();
		}
		
		return new PrintStreamProgressMonitor(new PrintStream(progressOutput), 0, 2, true);
	}
	
	/**
	 * @return Command to be executed
	 */
	protected Command createCommand(Context context) {
		return new Command() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return AbstractCommandMojo.this.getClass().getName();
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) {
				AbstractCommandMojo.this.execute(context, progressMonitor);				
			}
			
		};
	}
	
	protected abstract void execute(Context context, ProgressMonitor progressMonitor);

	public void execute() throws MojoExecutionException {
		Status[] failStatusesArray =  failStatuses == null ? new Status[0] : failStatuses;
		try {
			logDiagnostic(Util.call(createCommand(createContext()), createProgressMonitor(), failStatusesArray));			
		} catch (DiagnosticException e) {
			MojoExecutionException mojoExecutionException = new MojoExecutionException("Diagnostic exception: " + e, e);
			try {
				logDiagnostic(e.getDiagnostic());
			} catch (FileNotFoundException e1) {
				mojoExecutionException.addSuppressed(e1);
				throw mojoExecutionException;
			}
			throw mojoExecutionException;
		} catch (IOException e) {
			throw new MojoExecutionException(e);
		}
	}
	
	protected void logDiagnostic(Diagnostic diagnostic) throws FileNotFoundException {
		if (diagnosticOutput == null) {
			log(diagnostic, 0, getLog());
		} else {
			File parent = diagnosticOutput.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			try (PrintStream out = new PrintStream(diagnosticOutput)) {
				diagnostic.dump(out, 0);
			}
		}		
	}	
	
	private static void log(Diagnostic diagnostic, int indent, Log log) {
		StringBuilder messageBuilder = new StringBuilder();
		for (int i=0; i < indent; ++i) {
			messageBuilder.append("  ");
		}
				
		messageBuilder.append(diagnostic.getMessage());

		List<Object> data = diagnostic.getData();
		if (data != null) {
			messageBuilder.append(" data=");
			messageBuilder.append(diagnostic.getData());
		}
		
		Status status = diagnostic.getStatus();
		if (status == Status.ERROR) {
			log.error(messageBuilder);
			if (data != null) {
				for (Object de: data) {
					if (de instanceof Throwable) {
						log.error((Throwable) de);
					}
				}
			}
		} else if (status == Status.WARNING) {
			log.warn(messageBuilder);
			if (data != null) {
				for (Object de: data) {
					if (de instanceof Throwable) {
						log.warn((Throwable) de);
					}
				}
			}
		} else if (status == Status.INFO) {
			log.info(messageBuilder);
			if (data != null) {
				for (Object de: data) {
					if (de instanceof Throwable) {
						log.info((Throwable) de);
					}
				}
			}
		} else {
			if (status != null) {
				messageBuilder.insert(0, "[" + status.name() + "] ");
			}
			log.debug(messageBuilder);
			if (data != null) {
				for (Object de: data) {
					if (de instanceof Throwable) {
						log.debug((Throwable) de);
					}
				}
			}
		}
		
	    List<Diagnostic> children = diagnostic.getChildren();
		if (children != null) {
	    	children.forEach(c -> log(c, indent + 1, log));
	    }
	}	
	
}
