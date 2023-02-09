package vTiger.Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Step 1: Convert the excel file into java readable object using file input stream
		FileInputStream fis = new FileInputStream("./src/test/resources/TestDataOnline.xlsx");

		// Step 2: Create workbook
		Workbook workbook = WorkbookFactory.create(fis);

		// Step 3: Navigate to required sheet
		Sheet sheet = workbook.getSheet("Organizatons");

		// Step 4: Navigate to required row
		Row row = sheet.getRow(4);

		// Step 5: Create cell
		Cell cell = row.createCell(7);

		// Step 6: Set the value in created cell
		cell.setCellValue("Qspiders");

		// Step 7: Convert the excel file into java writable object using file output
		// stream
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestDataOnline.xlsx");

		// Step 8: Write the data into excel
		workbook.write(fos);
	}
}
