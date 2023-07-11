package org.nasdanika.exec.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.util.ExecObjectLoaderSupplier;
import org.nasdanika.ncore.Marker;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestText {
	
	@Test
	public void testFullDefinition() throws Exception {	
		URI resourceURI = URI.createURI(getClass().getResource("text/text.yml").toString()); 
				
		@SuppressWarnings("resource")
		Supplier<EObject> textSupplier = new ExecObjectLoaderSupplier(resourceURI, Context.singleton("token", "World"), false);
		
		Consumer<EObject> textConsumer = new Consumer<EObject>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Testing text";
			}

			@Override
			public void execute(EObject obj, ProgressMonitor progressMonitor) {
				Text text = (Text) obj;
				assertThat(text.getDescription()).isEqualTo("Full text definition");
				assertThat(text.getContent()).isEqualTo("Hello World.");
				assertThat(text.isInterpolate()).isFalse();
				List<? extends Marker> markers = text.getMarkers();
				assertThat(markers).isNotNull().isNotEmpty();
			}
		};
		
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(textSupplier.then(textConsumer), new PrintStreamProgressMonitor());
			if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
			}
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}				
	}
	
	@Test
	public void testDefaultFeature() throws Exception {	
		URI resourceURI = URI.createURI(getClass().getResource("text/text-default-feature.yml").toString()); 
		
		@SuppressWarnings("resource")
		Supplier<EObject> textSupplier = new ExecObjectLoaderSupplier(resourceURI, Context.singleton("token", "World"), false);
		
		Consumer<EObject> textConsumer = new Consumer<EObject>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Testing text";
			}

			@Override
			public void execute(EObject obj, ProgressMonitor progressMonitor) {
				Text text = (Text) obj;
				assertThat(text.getDescription()).isNull();
				assertThat(text.getContent()).isEqualTo("Hello World.");
				assertThat(text.isInterpolate()).isTrue();
			}
		};
		
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(textSupplier.then(textConsumer), new PrintStreamProgressMonitor());
			if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
			}
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}				
	}
	
}
