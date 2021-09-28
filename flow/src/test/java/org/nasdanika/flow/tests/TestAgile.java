package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Status;

/**
 * Tests of agile flows.
 * @author Pavel
 *
 */
public class TestAgile extends TestBase {
	
	@Test
	public void testCore() throws Exception {	
		load(
				"agile/core.yml", 
				obj -> {
					org.nasdanika.flow.Package pkg = (org.nasdanika.flow.Package) obj;
					
					assertThat(pkg.getParticipants().get("product-owner").getDescription()).isEqualTo("<p>");
					
					assertThat(pkg.getParticipants()).hasSize(3);
					assertThat(pkg.getResources()).hasSize(5);
					assertThat(pkg.getArtifacts()).hasSize(3);
					assertThat(pkg.getActivities()).hasSize(1);
					
				},
				diagnostic -> {
					if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
						System.err.println("***********************");
						System.err.println("*      Diagnostic     *");
						System.err.println("***********************");
						diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
					}
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
