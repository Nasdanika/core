package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.nasdanika.common.Status;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.StringProperty;

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
					EMap<String, Diagram> representations = core.getRepresentations();
					Diagram diagram = representations.get("package");
					EList<Property> properties = diagram.getProperties();
					for (Property property: properties) {
						dump(property, 0);
					}
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
	
	private static void dump(Property property, int indent) {
		for (int i = 0; i < indent; ++i) {
			System.out.print("  ");
		}
		System.out.print(property.getName() + " " + property.eClass().getName());
		if (property instanceof StringProperty) {
			System.out.println(" " + ((StringProperty) property).getValue());
		} else if (property instanceof MapProperty) {
			System.out.println();
			for (Property subProperty: ((MapProperty) property).getValue()) {
				dump(subProperty, indent + 1);
			}
		}		
	}
	
}
