package org.nasdanika.exec.gen.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.gen.ExecGenObjectLoaderSupplier;
import org.nasdanika.exec.gen.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestText extends TestBase {

	@Test
	public void testFullDefinition() throws Exception {	
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			Context context = Context.singleton("token", "World");
			URI resourceURI = URI.createURI(getClass().getResource("text/text.yml").toString()); 		
			try {
				Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				};
				EObject eObject = Objects.requireNonNull(Util.call(new ExecGenObjectLoaderSupplier(resourceURI, context, false), progressMonitor, diagnosticConsumer), "Loaded null from " + resourceURI);
				SupplierFactory<InputStream> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(eObject, InputStream.class), "Cannot adapt to SupplierFactory");
				InputStream in = Util.call(supplierFactory.create(context), progressMonitor, diagnosticConsumer);
				assertThat(in).isNotNull();
				assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello World.");
			} catch (DiagnosticException e) {
				System.err.println("******************************");
				System.err.println("*      Diagnostic failed     *");
				System.err.println("******************************");
				e.getDiagnostic().dump(System.err, 4, Status.FAIL);
				throw e;
			}		
		}		
	}
	
	@Test
	public void testClasspathResource() throws Exception {	
		InputStream in = loadInputStream(
				"text/text-default-feature.yml",
				diagnostic -> {					
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("token", "World"));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello World.");
	}
	
	@Test
	public void testHello() throws Exception {	
		InputStream in = loadInputStream(
				"text/hello.yml",
				diagnostic -> {					
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("name", "World"));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!\nHow are you doing tonight?\n");
	}
		
}
