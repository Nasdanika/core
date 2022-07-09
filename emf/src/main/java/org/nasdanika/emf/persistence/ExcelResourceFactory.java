package org.nasdanika.emf.persistence;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * Creates {@link ExcelResource}
 * @author Pavel
 *
 */
public class ExcelResourceFactory extends ResourceFactoryImpl {
	
	@Override
	public Resource createResource(URI uri) {
		return new ExcelResource(uri) {
			
			@Override
			void loadWorkbook(XSSFWorkbook workbook, XSSFFormulaEvaluator createFormulaEvaluator) {
				ExcelResourceFactory.this.loadWorkbook(this, workbook, createFormulaEvaluator);				
			}
			
		};
	}
	
	protected void loadWorkbook(ExcelResource resource, XSSFWorkbook workbook, FormulaEvaluator formulaEvaluator) {
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		while (sheetIterator.hasNext()) {
			loadSheet(resource, sheetIterator.next(), formulaEvaluator);
		}
	};
	
	protected void loadSheet(ExcelResource resource, Sheet sheet, FormulaEvaluator formulaEvaluator) {
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			loadRow(resource, rowIterator.next(), formulaEvaluator);
		}
	};
	
	protected void loadRow(ExcelResource resource, Row row, FormulaEvaluator formulaEvaluator) {
		// NOP		
	};
	
	protected Object getCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case NUMERIC:
			return cell.getNumericCellValue();
		case STRING:
			return cell.getStringCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case BLANK:
			return null;
		case ERROR:
			return cell.getErrorCellValue();
		case FORMULA:
			CellValue cellValue = formulaEvaluator.evaluate(cell);
			if (cellValue == null) {
				return null;
			}
			switch (cellValue.getCellType()) {
			case NUMERIC:
				return cellValue.getNumberValue();
			case STRING:
				return cellValue.getStringValue();
			case BOOLEAN:
				return cellValue.getBooleanValue();
			case BLANK:
				return null;
			case ERROR:
				return cellValue.getErrorValue();
			default:
				throw new UnsupportedOperationException("Unsupported formula result type: " + cellValue.getCellType());				
			}
		default:
			throw new UnsupportedOperationException("Unsupported cell type: " + cell.getCellType());
		}
	}	
		
}
