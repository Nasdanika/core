package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Status;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestPackage extends TestBase {
	
	@Test
	public void testSubPackage() throws Exception {	
		load(
				"sub-package.yml", 
				obj -> {
					org.nasdanika.flow.Package pkg = (org.nasdanika.flow.Package) obj;
					
					assertThat(pkg.getSubPackages()).hasSize(1);
					
					org.nasdanika.flow.Package common = pkg.getSubPackages().get("common");
					assertThat(common).isNotNull();
					assertThat(common.getName()).isEqualTo("Common definitions");										
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
