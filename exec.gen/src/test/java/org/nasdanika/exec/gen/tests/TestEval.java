package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestEval extends TestBase {
	
	@Test
	public void testBindings() throws Exception {	
		InputStream in = loadInputStream(
				"eval/bindings.yml",
				diagnostic -> {					
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("99.0");
	}
		
}
