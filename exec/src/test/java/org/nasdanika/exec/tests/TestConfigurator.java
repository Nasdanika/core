package org.nasdanika.exec.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.nasdanika.common.Status;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestConfigurator extends TestBase {
	
	@Test
	public void testConfigurator() throws Exception {	
		load(
				"configurator/configurator.yml", 
				obj -> {
					Configurator configurator = (Configurator) obj;
					assertThat(configurator.getTarget()).isInstanceOf(Resource.class);
					EMap<String, EObject> properties = configurator.getProperties();
					assertThat(properties).hasSize(1);
					assertThat(properties.get("name")).isNotNull().isInstanceOf(Text.class);
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
	@Test
	public void testURI() throws Exception {	
		load(
				"configurator/configurator-with-uri.yml", 
				obj -> {
					Configurator configurator = (Configurator) obj;
					assertThat(configurator.getTarget()).isInstanceOf(Resource.class);
					EMap<String, EObject> properties = configurator.getProperties();
					assertThat(properties).hasSize(1);
					EObject nameProperty = properties.get("name");
					assertThat(nameProperty).isNotNull().isInstanceOf(Text.class);
					String namePropertyURI = ((Text) nameProperty).getUri();
					assertThat(namePropertyURI).isNotNull();
					assertThat(namePropertyURI).isEqualTo("nasdanika:exec/configurator/properties/name");
				},
				diagnostic -> {
					if (diagnostic.getStatus() == Status.ERROR) {
						System.err.println("***********************");
						System.err.println("*      Diagnostic     *");
						System.err.println("***********************");
						diagnostic.dump(System.err, 4, Status.ERROR);
					}
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
	
}
