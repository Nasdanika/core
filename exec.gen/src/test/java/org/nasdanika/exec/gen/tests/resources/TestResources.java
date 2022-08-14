package org.nasdanika.exec.gen.tests.resources;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.resources.BinaryEntity;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.exec.gen.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestResources extends TestBase {
	
	@Test
	public void testResources() throws Exception {	
		BinaryEntityContainer container = new EphemeralBinaryEntityContainer();
		Diagnostic generationDiagnostic = generate(
				"resources.yml",
				container,
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
		assertThat(DefaultConverter.INSTANCE.toString(file.getState(progressMonitor)).trim()).isEqualTo("<p>Hello, <code>World</code>!</p>");
	}
		
}
