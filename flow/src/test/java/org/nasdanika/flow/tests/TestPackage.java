package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.nasdanika.common.Status;
import org.nasdanika.flow.Package;

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
					
					EMap<String, Package> subPackages = pkg.getSubPackages();
					assertThat(subPackages).hasSize(2);
					
					org.nasdanika.flow.Package common = subPackages.get("common");
					assertThat(common).isNotNull();
					assertThat(common.getName()).isEqualTo("Common definitions");
					
					System.out.println(subPackages.keySet());
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
	
	@Test
	public void testInheritance() throws Exception {	
		load(
				"extended.yml", 
				obj -> {
					org.nasdanika.flow.Package extended = (org.nasdanika.flow.Package) obj;				
					assertThat(extended.getUri()).isEqualTo("nasdanika:extended");
					EList<Package> baseList = extended.getExtends();
					assertThat(baseList).singleElement();
					Package base = baseList.get(0);
					assertThat(base.getUri()).isEqualTo("nasdanika:base");
										
					EMap<String, Package> subPackages = extended.getSubPackages();
					assertThat(subPackages).hasSize(1);
					
					EList<Package> extensions = base.getExtensions();
					assertThat(extensions).singleElement();
					assertThat(extensions.get(0)).isEqualTo(extended);
					
					org.nasdanika.flow.Package common = subPackages.get("common");
					assertThat(common).isNotNull();
					assertThat(common.getName()).isEqualTo("Extended common definitions");
					
					System.out.println(subPackages.keySet());
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
