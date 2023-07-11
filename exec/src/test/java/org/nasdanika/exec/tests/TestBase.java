package org.nasdanika.exec.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.nasdanika.common.Command;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.exec.util.ExecObjectLoaderExecutionParticipant;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * Common methods for testing
 * @author Pavel
 *
 */
public class TestBase {
	
	public static void dumpToYaml(EObject eObject) {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK); dumperOptions.setIndent(4); 
		new Yaml(dumperOptions).dump(dump(eObject), new OutputStreamWriter(System.out));
	}
	
	/**
	 * Dumps {@link EObject} to {@link Map} for to further dump to YAML. 
	 * Outputs class, path, URI, and name.
	 * @param eObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> dump(EObject eObject) {
		Map<String,Object> ret = new LinkedHashMap<>(); 
		ret.put("class", eObject.eClass().getName());
		for (EReference ref: eObject.eClass().getEAllReferences()) {
			if (ref. isContainment()) { 
				if (ref.isMany()) {
					Collection<Map<String,Object>> elements = new ArrayList<>(); 
					for (EObject el: ((Collection<EObject>) eObject.eGet(ref))) { 
						if (el != null) {
							elements.add(dump(el));
						}
					}
					if (!elements.isEmpty()) {
						ret.put(ref.getName(), elements);
					}
				} else {
					EObject val = (EObject) eObject.eGet(ref); 
					if (val != null) {
						ret.put(ref.getName(), dump(val));
					}
				}
			}
		}
		return ret;
	}
	
	// TODO - supplier, consumer, command tests - chaining with then
	/**
	 * Loads resource and passes its root to the consumer.
	 * @param resource Resource location relative to the test class.
	 * @param consumer Consumer of the resource root - typically with assertions.
	 * @param diagnosticConsumer Consumer of diagnostic to validate status.
	 * @throws Exception
	 */
	protected void load(
			String resource, 
			Consumer<EObject> consumer, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		
		load(resource, Context.EMPTY_CONTEXT, consumer, diagnosticConsumer);
	}

	/**
	 * Loads resource and passes its root to the consumer.
	 * @param resource Resource location relative to the test class.
	 * @param consumer Consumer of the resource root - typically with assertions.
	 * @param diagnosticConsumer Consumer of diagnostic to validate status.
	 * @throws Exception
	 */
	protected void load(
			String resource,
			Context context, 
			Consumer<EObject> consumer, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {	
		// Outputs to console, send to file if desired.
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		class TestCommand extends ExecObjectLoaderExecutionParticipant implements Command {

			public TestCommand(Context context) {
				super(context, false);
			}

			@Override
			protected Collection<URI> getResources() {				
				return Collections.singleton(URI.createURI(TestBase.this.getClass().getResource(resource).toString()));
			}

			@Override
			protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
				ResourceSet rs = super.createResourceSet(progressMonitor);
				rs.getURIConverter().getURIHandlers().add(new URIHandlerImpl() {

					@Override
					public boolean canHandle(URI uri) {
						return uri != null && "classpath".equals(uri.scheme());
					}

					@Override
					public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
						return getClass().getClassLoader().getResourceAsStream(uri.path());
					}
					
				});
				return rs;
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) {
				assertEquals(1, roots.size());
				if (consumer != null) {
					consumer.accept(roots.iterator().next());
				}
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(new TestCommand(context), progressMonitor);
			if (diagnosticConsumer != null) {
				diagnosticConsumer.accept(diagnostic);
			}
			if (diagnostic.getStatus() == Status.WARNING || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.ERROR, Status.WARNING);
			}
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}
		
	}

}
