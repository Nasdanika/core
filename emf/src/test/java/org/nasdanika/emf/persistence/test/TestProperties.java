package org.nasdanika.emf.persistence.test;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.nasdanika.common.Status;
import static org.assertj.core.api.Assertions.assertThat;

public class TestProperties extends TestBase {

	@Test
	public void testMap() throws Exception {
		load(
				"map.yml", 
				obj -> {
					org.nasdanika.ncore.Map map = (org.nasdanika.ncore.Map) obj;
//					EList<EObject> tryBlock = block.getTry();
//					assertThat(tryBlock)
//						.hasSize(1)
//						.first()
//							.isInstanceOf(Resource.class);
//					
//					EList<EObject> catchBlock = block.getCatch();
//					assertThat(catchBlock)
//						.hasSize(1)
//						.first()
//							.isInstanceOf(Resource.class);
//					
//					EList<EObject> finallyBlock = block.getFinally();
//					assertThat(finallyBlock)
//						.hasSize(1)
//						.first()
//							.isInstanceOf(Text.class);
//					
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
		
	}
	

}
