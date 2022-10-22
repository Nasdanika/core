package org.nasdanika.exec.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Status;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.content.Text;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestEval extends TestBase {
	
	@Test
	public void testBindings() throws Exception {	
		load(
				"eval/bindings.yml", 
				obj -> {
					Eval eval = (Eval) obj;
					assertThat(eval.getScript()).isInstanceOf(Text.class);
					EMap<String, EObject> properties = eval.getBindings();
					assertThat(properties).hasSize(1);
					assertThat(properties.get("val")).isNotNull().isInstanceOf(Text.class);
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
