package org.nasdanika.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;
import org.nasdanika.emf.persistence.ExcelResourceFactory;
import org.nasdanika.flow.FlowFactory;
import org.nasdanika.flow.Participant;

public class TestExcelResource {
	
	@Test
	public void testExcelResource() throws Exception {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xlsx", new ExcelResourceFactory() {
			
			@Override
			protected void loadRow(Resource resource, Row row, FormulaEvaluator formulaEvaluator) {
				if ("Participants".equals(row.getSheet().getSheetName())) {
					Participant participant = FlowFactory.eINSTANCE.createParticipant();
					participant.setName(row.getCell(0).getStringCellValue());
					participant.setDescription(row.getCell(1).getStringCellValue());
					resource.getContents().add(participant);
				}
			}
			
		});
		File modelFile = new File("src/test/resources/org/nasdanika/flow/tests/model.xlsx");
		assertThat(modelFile.isFile());
		Resource modelResource = resourceSet.getResource(URI.createFileURI(modelFile.getCanonicalPath()), true);
		EList<EObject> contents = modelResource.getContents();
		assertThat(contents).hasSize(2);
		
		Participant p1 = (Participant) contents.get(0);
		assertThat(p1.getName()).isEqualTo("Participant 1");
		
		Participant p2 = (Participant) contents.get(1);
		assertThat(p2.getDescription()).isEqualTo("Description 2");
		
	}

}
