package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.Icon;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of Generic Methods related to Excel File
 * 
 * @author DHARMENDRA KUMAR
 *
 */
public class ExcelFileUtility {
	
	/**
	 * This method will read data from excel sheet and return value
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcelFile(String sheetname, int rownum, int cellnum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(IConstantsUtility.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		String value = cell.getStringCellValue();
		return value;
	}
	
	/**
	 * This method will write data into excel sheet
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetname, int rownum, int cellnum, Date value) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IConstantsUtility.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(value);
		
		FileOutputStream fos=new FileOutputStream(IConstantsUtility.EXCEL_FILE_PATH);
		workbook.write(fos);
	}
	
	/**
	 * This method will provide the row count for the given sheet name
	 * @param sheetname
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IConstantsUtility.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		sheet.getLastRowNum();
		
	}
	
	/**
	 * This method will read multiple from the excel sheet
	 * @param sheetname
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readMultipeDataFromExcel(String sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IConstantsUtility.EXCEL_DATA_PROVIDER);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		int lastRow = sheet.getLastRowNum();
		int lastcell = sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[lastRow][lastcell]; 
		
		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastcell; j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	
}
