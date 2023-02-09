package organizations.genericUtility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class CreateOrganizationsWithIndustryAndTypeGU {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Step 1: Create instance of all required libraries
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();

		// Step 2: Read all required data from external resources
		String Browser = pUtil.readDataFromPropertyFile("browser");
		String Url = pUtil.readDataFromPropertyFile("url");
		String Username = pUtil.readDataFromPropertyFile("username");
		String Password = pUtil.readDataFromPropertyFile("password");

		String organizationName = eUtil.readDataFromExcelFile("Organizations", 1, 2) + jUtil.getRandomNumber();
		String industryName = eUtil.readDataFromExcelFile("Organizations", 4, 3);
		String typeName = eUtil.readDataFromExcelFile("Organizations", 10, 4);

		// Step 3: Launch the Browser
		WebDriver driver = null;
		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser Name");
		}
		wUtil.maximizeWindow(driver);
		driver.get(Url);
		wUtil.waitForPageLoad(driver);

		// Step 4: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();

		// Step 5: Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 6: Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 7: Create Organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(organizationName);

		// Step 8: Select "Healthcare" in industry drop down
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		wUtil.handleDropDown(industryDropdown, industryName);

		// Step 9: Select "Analyst" in Type drop down
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		wUtil.handleDropDown(typeDropdown, typeName);

		// Step 10: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 11: Validate newly created organization
		String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationHeader.contains(organizationName)) {
			System.out.println(organizationHeader + " PASS");
		} else {
			System.out.println("FAIL");
		}

		// industry + type drop down validation
		String expectedIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		String expectedType = driver.findElement(By.id("dtlview_Type")).getText();
		if ((expectedIndustry.contains(industryName)) && (expectedType.contains(typeName)))
			System.out.println(expectedIndustry + expectedType + "TestCase Passed");
		else
			System.out.println(expectedIndustry + expectedType + "TestCase Failed");

		// Step 12: SignOut of the Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 13: Close the Browser
		wUtil.closeBrowser(driver);

	}

}
