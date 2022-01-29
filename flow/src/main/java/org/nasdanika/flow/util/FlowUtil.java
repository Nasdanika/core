package org.nasdanika.flow.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.nasdanika.common.Command;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.GitMarkerFactory;

public final class FlowUtil {

	private FlowUtil() {
		// Singleton
	}
	
//	/**
//	 * Loads resource and passes its root to the consumer.
//	 * @param resource Resource location relative to the test class.
//	 * @param consumer Consumer of the resource root - typically with assertions.
//	 * @param diagnosticConsumer Consumer of diagnostic to validate status.
//	 * @throws Exception
//	 */
//	public static void load(
//			String resource,
//			Context context, 
//			Consumer<EObject> consumer, 
//			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
//			ProgressMonitor progressMonitor) throws Exception {	
//
//		URI resourceURI = URI.createURI(TestBase.this.getClass().getResource(resource).toString());
//
//		class TestCommand extends FlowYamlLoadingExecutionParticipant implements Command {
//
//			public TestCommand(Context context) {
//				super(context);
//			}
//
//			@Override
//			protected Collection<URI> getResources() {				
//				return Collections.singleton(resourceURI);
//			}
//
//			@Override
//			public void execute(ProgressMonitor progressMonitor) throws Exception {
//				if (consumer != null) {
//					consumer.accept(resourceSet.getResource(resourceURI, false).getContents().get(0));
//				}
//			}
//			
//			@Override
//			protected boolean isDiagnoseModel() {
//				return false;
//			}
//			
//		};
//		
//		// Diagnosing loaded resources. 
//		try {
//			org.nasdanika.common.Diagnostic diagnostic = Util.call(new TestCommand(context), progressMonitor);
//			if (diagnosticConsumer != null) {
//				diagnosticConsumer.accept(diagnostic);
//			}
//			if (diagnostic.getStatus() == Status.WARNING || diagnostic.getStatus() == Status.ERROR) {
//				System.err.println("***********************");
//				System.err.println("*      Diagnostic     *");
//				System.err.println("***********************");
//				diagnostic.dump(System.err, 4, Status.ERROR, Status.WARNING);
//			}
//		} catch (DiagnosticException e) {
//			System.err.println("******************************");
//			System.err.println("*      Diagnostic failed     *");
//			System.err.println("******************************");
//			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
//			throw e;
//		}
//		
//	}
	
}
