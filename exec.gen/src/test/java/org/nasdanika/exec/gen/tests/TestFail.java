package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Status;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestFail extends TestBase {
	
	@Test(expected = NasdanikaException.class)
	public void testFail() throws Exception {	
		loadInputStream(
				"fail/fail.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
	}
		
}
