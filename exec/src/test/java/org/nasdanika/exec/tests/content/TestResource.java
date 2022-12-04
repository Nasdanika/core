package org.nasdanika.exec.tests.content;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.tests.TestBase;
import org.nasdanika.exec.util.ExecObjectLoaderSupplier;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestResource extends TestBase {
	
	@Test
	public void testFullDefinition() throws Exception {	
		URI resourceURI = URI.createURI(getClass().getResource("resource/resource.yml").toString()); 
		
		@SuppressWarnings("resource")
		Supplier<EObject> resourceSupplier = new ExecObjectLoaderSupplier(resourceURI, Context.singleton("token", "World"));
		
		Consumer<EObject> resourceConsumer = new Consumer<EObject>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Testing resource";
			}

			@Override
			public void execute(EObject obj, ProgressMonitor progressMonitor) {
				Resource resource = (Resource) obj;
				assertThat(resource.getDescription()).isEqualTo("Full resource definition");
				assertThat(resource.getLocation()).isEqualTo(getClass().getResource("resource/hello.txt").toString());
			}
		};
		
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(resourceSupplier.then(resourceConsumer), new PrintStreamProgressMonitor());
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
		URI resourceURI = URI.createURI(getClass().getResource("resource/resource-default-feature.yml").toString()); 
		
		@SuppressWarnings("resource")
		Supplier<EObject> resourceSupplier = new ExecObjectLoaderSupplier(resourceURI, Context.singleton("token", "World"));
		
		Consumer<EObject> resourceConsumer = new Consumer<EObject>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Testing resource";
			}

			@Override
			public void execute(EObject obj, ProgressMonitor progressMonitor) {
				Resource resource = (Resource) obj;
				assertThat(resource.getDescription()).isNull();
				assertThat(resource.getLocation()).isEqualTo(getClass().getResource("resource/hello.txt").toString());
			}
		};
		
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(resourceSupplier.then(resourceConsumer), new PrintStreamProgressMonitor());
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
	public void testMissingLocation() throws Exception {	
		NasdanikaException thrown = assertThrows(NasdanikaException.class, () -> 
			load(
					"resource/resource-no-location.yml", 
					obj -> {
						fail("Should not be called");
					},
					diagnostic -> {
						fail("Should not be called");
					}));
	
		assertTrue(thrown != null);		
	}
	
	
}
