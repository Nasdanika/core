package org.nasdanika.exec.gen.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;
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
				"resource.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!");
	}
		
}
