package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Ignore;
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
public class TestConfigurator extends TestBase {
	
	@Test
	public void testSupplier() throws Exception {	
		InputStream in = loadInputStream(
				"configurator/supplier.yml",
				diagnostic -> {					
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("name", "Milky Way"));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, Extended Milky Way");
	}
	
	@Test
	public void testConsumer() throws Exception {	
		BinaryEntityContainer container = new EphemeralBinaryEntityContainer();
		Diagnostic generationDiagnostic = generate(
				"configurator/consumer.yml",
				container,
				Context.EMPTY_CONTEXT,
				Context.singleton("name", "Milky Way"),				
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
		assertThat(myContainer).isNotNull();
		assertThat(myContainer.exists(progressMonitor)).isTrue();
		
		BinaryEntity file = myContainer.get("my-file.txt", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(file.getState(progressMonitor))).isEqualTo("Hello, Extended Milky Way!\nHow are you doing tonight?\n");
	}
	
	@Test
	@Ignore
	public void testCommand() throws Exception {
		// TODO
	}
		
}
