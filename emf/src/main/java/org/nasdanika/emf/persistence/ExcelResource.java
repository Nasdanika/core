package org.nasdanika.emf.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.persistence.ExcelResourceFactory.UpdateAdapter;

/**
 * Loads EMF classes from MS Excel files/resources. 
 * Saving is supported via {@link UpdateAdapter}. All contents is iterated and for each element with UpdateAdapter the adapter's update method is invoked.
 * The load logic is responsible for attaching adapters to model elements. 
 * @author Pavel
 *
 */
abstract class ExcelResource extends ResourceImpl {
	
	/**
	 * Last loaded/saved state.
	 */
	private byte[] workbookCache;
	
	public ExcelResource(URI uri) {
		super(uri);
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (inputStream; baos) {
			inputStream.transferTo(baos);
		}
		workbookCache = baos.toByteArray();
		try (XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(workbookCache))) {
			loadWorkbook(workbook, workbook.getCreationHelper().createFormulaEvaluator());
		}		
	}
	
	abstract void loadWorkbook(XSSFWorkbook workbook, XSSFFormulaEvaluator createFormulaEvaluator);

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		try (XSSFWorkbook workbook = workbookCache == null ? new XSSFWorkbook() : new XSSFWorkbook(new ByteArrayInputStream(workbookCache))) {
			updateWorkbook(workbook);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (baos) {
				workbook.write(baos);
			}
			workbookCache = baos.toByteArray();
			outputStream.write(workbookCache);
		}
	}

	protected void updateWorkbook(XSSFWorkbook workbook) {
		TreeIterator<EObject> cit = getAllContents();
		while (cit.hasNext()) {
			UpdateAdapter updateAdapter = EObjectAdaptable.adaptTo(cit.next(), UpdateAdapter.class);
			if (updateAdapter != null) {
				updateAdapter.update(workbook);
			}
		}
	};
	
}