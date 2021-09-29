package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Status;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.Transition;

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
					org.nasdanika.flow.Package core = (org.nasdanika.flow.Package) obj;
					
					assertThat(core.getParticipants()).hasSize(3);
					assertThat(core.getResources()).hasSize(5);
					assertThat(core.getArtifacts()).hasSize(3);
					assertThat(core.getActivities()).hasSize(1);

					// Cross-referencing.
					Flow flow = (Flow) core.getActivities().get("development");
					Activity<?> createActivity = (Activity<?>) flow.getElements().get("create");
					
					assertThat(createActivity.getParticipants()).singleElement();
					assertThat(createActivity.getParticipants().get(0).getName()).isEqualTo("Product Owner");
					
					assertThat(createActivity.getResources()).singleElement();
					assertThat(createActivity.getResources().get(0).getName()).isEqualTo("Issue Tracker");
					
					Activity<?> groomActivity = (Activity<?>) flow.getElements().get("groom");					
					Transition developOutput = groomActivity.getOutputs().get("develop");
					assertThat(developOutput.getPayload().get(0)).isEqualTo(core.getArtifacts().get("story"));
					
					Activity<?> developActivity = (Activity<?>) flow.getElements().get("develop");
					assertThat(developOutput.getTarget()).isEqualTo(developActivity);
					
					assertThat(developActivity.getInputArtifacts()).singleElement();
					assertThat(developActivity.getInputArtifacts().get(0).getName()).isEqualTo("User story");
					
					assertThat(developActivity.getOutputArtifacts()).singleElement();
					assertThat(developActivity.getOutputArtifacts().get(0).getName()).isEqualTo("Source code");
					
					
					// Instance
					
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
