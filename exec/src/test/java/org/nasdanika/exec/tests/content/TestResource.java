package org.nasdanika.exec.tests.content;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.nasdanika.common.Status;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestResource extends TestBase {
	
	@Test
	public void testFullDefinition() throws Exception {	
		load(
				"resource.yml", 
				obj -> {
					Resource resource = (Resource) obj;
					assertThat(resource.getDescription()).isEqualTo("Full resource definition");
					assertThat(resource.getLocation()).isEqualTo(getClass().getResource("hello.txt").toString());
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
	@Test
	public void testDefaultFeature() throws Exception {	
		load(
				"resource-default-feature.yml", 
				obj -> {
					Resource resource = (Resource) obj;
					assertThat(resource.getDescription()).isNull();
					assertThat(resource.getLocation()).isEqualTo(getClass().getResource("hello.txt").toString());
				}, 
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
	@Test(expected = ConfigurationException.class)	
	public void testMissingLocation() throws Exception {	
		load(
				"resource-no-location.yml", 
				obj -> {
					fail("Should not be called");
				},
				diagnostic -> {
					fail("Should not be called");
				});		
	}
	
	
}
