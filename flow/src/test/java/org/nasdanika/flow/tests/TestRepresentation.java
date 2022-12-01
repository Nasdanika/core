package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.common.util.EMap;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Status;

/**
 * Tests of representations.
 * @author Pavel
 *
 */
public class TestRepresentation extends TestBase {
	
	@Test
	public void testRepresentation() throws Exception {	
		load(
				"representation.yml", 
				obj -> {
					org.nasdanika.flow.Package core = (org.nasdanika.flow.Package) obj;					
					EMap<String, String> representations = core.getRepresentations();
					String diagram = representations.get("package");
					System.out.println(diagram);
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
