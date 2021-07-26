package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;

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
		
}
