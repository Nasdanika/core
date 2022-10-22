package org.nasdanika.exec.tests.resources;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Status;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestResources extends TestBase {
	
	@Test
	public void testResources() throws Exception {	
		load(
				"resources.yml", 
				obj -> {
					assertThat(obj).isInstanceOf(Container.class);
					Container container = (Container) obj;
					assertThat(container.getName()).isEqualTo("my-container");
					assertThat(container.getContents())
						.singleElement()
						.isInstanceOf(File.class)
						.hasFieldOrPropertyWithValue("name", "my-file.txt");
					
					assertThat(((File) container.getContents().iterator().next()).getContents())
						.singleElement()
						.isInstanceOf(Text.class);						
					
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
