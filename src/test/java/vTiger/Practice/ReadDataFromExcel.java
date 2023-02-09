package vTiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Step 1: Convert the excel file into java readable object using file input stream
		// Load the excel file into file input stream
		FileInputStream fis = new FileInputStream("./src/test/resources/TestDataOnline.xlsx");

		// Step 2: Create Workbook
		Workbook workbook = WorkbookFactory.create(fis);

		// Step 3: Navigate to required Sheet
		Sheet sheet = workbook.getSheet("Organizatons");

		// Step 4: Navigate to required Row
		Row row = sheet.getRow(0);

		// Step 5: Navigate to required Cell
		Cell cell1 = row.getCell(0);
		Cell cell2 = row.getCell(2);

		// Step 6: Capture the data present inside the cell
		String cellValue = cell1.getStringCellValue();
		System.out.println(cellValue);
		System.out.println(cell2.getStringCellValue());
	}
}
