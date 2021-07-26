package org.nasdanika.exec.gen.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;
import org.nasdanika.exec.gen.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestText extends TestBase {
	
	@Test
	public void testFullDefinition() throws Exception {	
		InputStream in = loadInputStream(
				"text/text.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("token", "World"));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello ${token}.");
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
		
}
