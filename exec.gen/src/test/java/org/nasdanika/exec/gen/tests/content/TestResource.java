package org.nasdanika.exec.gen.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;
import org.nasdanika.exec.gen.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestResource extends TestBase {
	
	@Test
	public void testFullDefinition() throws Exception {	
		InputStream in = loadInputStream(
				"resource/resource.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isNotEqualTo(Status.ERROR);
					assertThat(diagnostic.getStatus()).isNotEqualTo(Status.WARNING);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!");
	}
	
	@Test
	@Disabled // In the modular Java requires opening a module/package
	public void testClasspathResource() throws Exception {	
		InputStream in = loadInputStream(
				"resource/classpath-resource.yml",
				diagnostic -> {					
					Status status = diagnostic.getStatus();
					if (status == Status.ERROR || status == Status.WARNING) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!");
	}
		
}
