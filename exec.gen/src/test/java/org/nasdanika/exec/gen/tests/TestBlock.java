package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.EphemeralBinaryEntityContainer;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestBlock extends TestBase {
	
	@Test
	public void testContentFailing() throws Exception {	
		InputStream in = loadInputStream(
				"block/block-content-failing.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).matches("Erroneous .+World");
	}
	
	@Test
	public void testContentSuccessful() throws Exception {	
		InputStream in = loadInputStream(
				"block/block-content-successful.yml",
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
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!World");
	}
	
	@Test
	public void testResourcesSuccessful() throws Exception {	
		BinaryEntityContainer container = new EphemeralBinaryEntityContainer();
		Diagnostic generationDiagnostic = generate(
				"block/block-resources-successful.yml",
				container,				
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		if (generationDiagnostic.getStatus() != Status.SUCCESS) {
			generationDiagnostic.dump(System.out, 0);
		}

		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
		assertThat(myContainer).isNotNull();
		assertThat(myContainer.exists(progressMonitor)).isTrue();
		
		BinaryEntity file = myContainer.get("my-file.txt", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(file.getState(progressMonitor))).isEqualTo("Hello, World!");
		
		BinaryEntity log = container.get("log.txt", progressMonitor);
		assertThat(log).isNotNull();
		assertThat(log.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(log.getState(progressMonitor))).isEqualTo("All done!");		
	}

	@Test
	public void testResourcesFailinig() throws Exception {	
		BinaryEntityContainer container = new EphemeralBinaryEntityContainer();
		Diagnostic generationDiagnostic = generate(
				"block/block-resources-failing.yml",
				container,				
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		if (generationDiagnostic.getStatus() != Status.SUCCESS) {
			generationDiagnostic.dump(System.out, 0);
		}

		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
		assertThat(myContainer).isNotNull();
		assertThat(myContainer.exists(progressMonitor)).isTrue();
		
		BinaryEntity file = myContainer.get("my-file.txt", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(file.getState(progressMonitor))).startsWith("Good bye, World! Error: java.io.FileNotFoundException: ");
		
		BinaryEntity log = container.get("log.txt", progressMonitor);
		assertThat(log).isNotNull();
		assertThat(log.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(log.getState(progressMonitor))).isEqualTo("All done!");
		
	}
	
}
