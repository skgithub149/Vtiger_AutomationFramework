package contacts.hardcoded;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactsWithDDT {

	public static void main(String[] args) throws IOException {
		
		Random ran=new Random();
		int random = ran.nextInt(1000);
		
		//Step 1: Read all the data from external resources
		/*Read data from Property file*/
		FileInputStream fisp=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pObj=new Properties();
		pObj.load(fisp);
		String Browser = pObj.getProperty("browser");
		String Url = pObj.getProperty("url");
		String Username = pObj.getProperty("username");
		String Password = pObj.getProperty("password");
		/*Read data from Excel file*/
		FileInputStream fise=new FileInputStream("./src/test/resources/TestDataOnline.xlsx");
		Workbook workbook = WorkbookFactory.create(fise);
		String lastName = workbook.getSheet("Contacts").getRow(4).getCell(2).getStringCellValue()+random;
		
		//Step 2: Launch the Browser
		WebDriver driver=null;
		if (Browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if (Browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();								
		}
		else {
			System.out.println("Invalid Browser Name");
		}
		driver.manage().window().maximize();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step 3: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();

		//Step 4: Click on Contacts Link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		//Step 5: Click on Contact lookup image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		//Step 6: Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		//Step 7: Click on Save Button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//Step 8: Validating newly created Contact
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText(); // capturing the text from the element
		if (contactHeader.contains(lastName)) {
			System.out.println(contactHeader+"Contact created---->Testcase Passed");
		} else {
			System.out.println(contactHeader+"Contact not Created---->Testcase Failed");
		}

		//Step 9: SignOut of the Application
		WebElement signoutIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signoutIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 10:Close the Browser
		driver.close();

	}

}
