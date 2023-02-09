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
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationsWithIndustryWithDDT {
	
	public static void main(String[] args) throws IOException {

		Random ran = new Random();
		int random = ran.nextInt(1000);

		// Step 1: Read all the data from external resources
		FileInputStream fisp = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fisp);
		String Browser = pObj.getProperty("browser");
		String Url = pObj.getProperty("url");
		String Username = pObj.getProperty("username");
		String Password = pObj.getProperty("password");

		FileInputStream fise = new FileInputStream("./src/test/resources/TestDataOnline.xlsx");
		Workbook workbook = WorkbookFactory.create(fise);
		String organizationName = workbook.getSheet("Organizations").getRow(4).getCell(2).getStringCellValue() + random;
		String industryName = workbook.getSheet("Organizations").getRow(4).getCell(3).getStringCellValue();

		// Step 2: Launch the Browser
		WebDriver driver = null;
		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser Name");
		}
		driver.manage().window().maximize();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 3: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();

		// Step 4: Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 5: Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 6: Create Organization with mandatory field
		driver.findElement(By.name("accountname")).sendKeys(organizationName);

		// Step 7: Select "Healthcare" in industry drop down
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select select = new Select(industryDropdown);
		select.selectByValue(industryName);

		// Step 8: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 9: Validate newly created organization
		String expectedIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (expectedIndustry.contains(industryName))
			System.out.println(expectedIndustry + "Organization Created--->TestCase Passed");
		else
			System.out.println(expectedIndustry + "Organization Created--->TestCase Failed");

		// Step 10: SignOut of the Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 11: Close the Browser
		driver.close();

	}

}
