package org.nasdanika.exec.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Status;
import org.nasdanika.exec.Block;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestBlock extends TestBase {
	
	@Test
	public void testReferences() throws Exception {	
		load(
				"block/block.yml", 
				obj -> {
					Block block = (Block) obj;
					EList<EObject> tryBlock = block.getTry();
					assertThat(tryBlock)
						.hasSize(1)
						.first()
							.isInstanceOf(Resource.class);
					
					EList<EObject> catchBlock = block.getCatch();
					assertThat(catchBlock)
						.hasSize(1)
						.first()
							.isInstanceOf(Resource.class);
					
					EList<EObject> finallyBlock = block.getFinally();
					assertThat(finallyBlock)
						.hasSize(1)
						.first()
							.isInstanceOf(Text.class);
					
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
