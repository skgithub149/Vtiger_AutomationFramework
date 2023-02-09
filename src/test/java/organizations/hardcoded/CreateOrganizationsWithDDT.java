package organizations.hardcoded;

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

public class CreateOrganizationsWithDDT {
	
	public static void main(String[] args) throws IOException {
		
		//Generates random number_appended to the organization name
		Random ran=new Random();
		int random = ran.nextInt(1000);
		
		//Step 1: Read all the data from external resources
		/*Reading data from property file*/
		FileInputStream fisp=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pObj=new Properties();
		pObj.load(fisp);
		String Browser = pObj.getProperty("browser");
		String Url = pObj.getProperty("url");
		String Username = pObj.getProperty("username");
		String Password = pObj.getProperty("password");
		
		/*Read data from excel file*/
		FileInputStream fise=new FileInputStream("./src/test/resources/TestDataOnline.xlsx");
		Workbook workbook = WorkbookFactory.create(fise);
		String organizationName = workbook.getSheet("Organizations").getRow(1).getCell(2).getStringCellValue()+random;
				
		//Step 2: Launch the Browser_RuntimePolymorphism_driver
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

		//Step 4: Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5: Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
 
		//Step 6: Create Organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(organizationName);

		//Step 7: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//Step 8: Validate newly created organization
		String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationHeader.contains(organizationName))
			System.out.println(organizationHeader+"Organization created--->TestCase Passed");
		else
			System.out.println(organizationHeader+"Organization not created--->TestCase Failed");

		//Step 9: SignOut of the Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 10: Close Browser
		driver.quit();

	}

}
