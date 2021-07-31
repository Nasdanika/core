package org.nasdanika.exec.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Status;
import org.nasdanika.exec.Fail;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestFail extends TestBase {
	
	@Test
	public void testMessage() throws Exception {	
		load(
				"fail/fail.yml", 
				obj -> {
					assertThat(obj).isInstanceOf(Fail.class);
					Fail fail = (Fail) obj;
					assertThat(fail.getMessage()).isEqualTo("Something is wrong");
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
