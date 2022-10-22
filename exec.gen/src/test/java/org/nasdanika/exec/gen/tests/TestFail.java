package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Status;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestFail extends TestBase {
	
	@Test
	public void testFail() throws Exception {	
		NasdanikaException thrown = assertThrows(NasdanikaException.class, () -> 
			loadInputStream(
					"fail/fail.yml",
					diagnostic -> {
						assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
					}));
		
		assertTrue(thrown != null);
	}
		
}
