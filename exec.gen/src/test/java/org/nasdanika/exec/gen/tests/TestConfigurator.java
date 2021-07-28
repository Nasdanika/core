package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;

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
	@Ignore
	public void testConsumer() throws Exception {
		// TODO
	}
	
	@Test
	@Ignore
	public void testCommand() throws Exception {
		// TODO
	}
		
}
