package org.nasdanika.emf;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Loads a model from URL, optionally validates it and then passes to consumer.
 * @author Pavel
 *
 */
public abstract class ModelCommand<T extends EObject> extends ResourceSetCommand {
	
	@Parameters(
			paramLabel = "URI", 
			description = "Model (object) URI resolved relative to the current directory. May include fragment to address a non-root object")
	protected String uri;
	
	@Option(names = {"-f", "--file"}, description = "URI is a file path")
	private boolean isFile;	
	
	
	/**
	 * @return Consumer factory which create a consumer to pass the loaded model to.
	 */
	protected abstract ConsumerFactory<T> getConsumerFactory();
	
	protected Supplier<T> createSupplier(Context context) {
		return new Supplier<T>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Loading model";
			}

			@SuppressWarnings("unchecked")
			@Override
			public T execute(ProgressMonitor progressMonitor) throws Exception {
				
				// Creating a resource set
				ResourceSet resourceSet = createResourceSet();

				URI modelUri = isFile ? URI.createFileURI(uri) : URI.createURI(new File(".").toURI().resolve(ModelCommand.this.uri).toString());
				Resource modelResource = resourceSet.getResource(modelUri, true);
				EObject target = modelUri.hasFragment() ? modelResource.getEObject(modelUri.fragment()) : modelResource.getContents().iterator().next();			
				
				Diagnostician diagnostician = new Diagnostician() {
					
					public Map<Object,Object> createDefaultContext() {
						Map<Object, Object> ctx = super.createDefaultContext();
						ctx.put(Context.class, context);
						return ctx;
					};
					
				};				
				Diagnostic validationResult = diagnostician.validate(target);
				if (validationResult.getSeverity() == Diagnostic.ERROR) {
					throw new DiagnosticException(validationResult);
				}
				return (T) target;
			}
		};
	}

	/**
	 * Supplier to Consumer. Supplier loads model from URL, consumer takes the model as an argument.
	 */
	@Override
	protected CommandFactory getCommandFactory() {
		return new CommandFactory() {
			
			@Override
			public org.nasdanika.common.Command create(Context context) throws Exception {
				return createSupplier(context).then(getConsumerFactory().create(context));
			}
		};
	}
	
	@Override
	protected void reportException(Exception e) {
		if (e instanceof DiagnosticException) {
			System.err.println("Diagnostic error:");
			dumpDiagnostic(((DiagnosticException) e).getDiagnostic(), 0);
		}
		super.reportException(e);
	}

	public static void dumpDiagnostic(Diagnostic d, int indent) {
		for (int i=0; i < indent; ++i) {
			System.err.print("    ");
		}
		System.err.println(toString(d));
	    if (d.getChildren() != null) {
	    	d.getChildren().forEach(c -> dumpDiagnostic(c, indent + 1));
	    }
		
	}
	
	static String toString(Diagnostic d) {
		StringBuilder result = new StringBuilder();
		switch (d.getSeverity()) {
		case Diagnostic.OK:
			result.append("OK");
			break;
		case Diagnostic.INFO:
			result.append("INFO");
			break;
		case Diagnostic.WARNING:
			result.append("WARNING");
			break;
		case Diagnostic.ERROR:
			result.append("ERROR");
			break;
		case Diagnostic.CANCEL:
			result.append("CANCEL");
			break;
		default:
			result.append(Integer.toHexString(d.getSeverity()));
			break;
		}

		result.append(" source=");
		result.append(d.getSource());

		result.append(" code=");
		result.append(d.getCode());

		result.append(' ');
		result.append(d.getMessage());

		if (d.getData() != null) {
			result.append(" data=");
			result.append(d.getData());
		}

		return result.toString();
	}	
	
}
