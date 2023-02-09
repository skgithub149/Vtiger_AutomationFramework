package contacts.genericUtility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class CreateContactsWithOrganizationGU {
	
	public static void main(String[] args) throws IOException {

		// Step 1: Create object of all required libraries
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
		String lastName = eUtil.readDataFromExcelFile("Contacts", 4, 2) + jUtil.getRandomNumber();

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

		// Step 8: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 9: Validate newly created organization
		String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationHeader.contains(organizationName))
			System.out.println(organizationHeader + "Organization created--->TestCase Passed");
		else
			System.out.println(organizationHeader + "Organization not created--->TestCase Failed");

		// Step 10: Click on Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// Step 11: Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 12: Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		// Step 13: Select Organization from Organization Name lookup
		driver.findElement(By.xpath("//input[@name='account_name'] /following-sibling::img[@title='Select']")).click();
		wUtil.switchToWindow(driver, "Accounts");
		driver.findElement(By.id("search_txt")).sendKeys(organizationName); // Wipro123
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + organizationName + "']")).click();
		// Dynamic xPath: a[text()='Wipro123']//During Runtime xPath is getting formulated

		// Step 14: Click on Save button
		wUtil.switchToWindow(driver, "Contacts");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 15: Validate newly created contact
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText(); // capturing the text from the element
																										 
																										 
		if (contactHeader.contains(lastName)) {
			System.out.println(contactHeader + "Contact created---->Testcase Passed");
		} else {
			System.out.println(contactHeader + "Contact not Created---->Testcase Failed");
		}

		// Step 16: SignOut of the Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 17: Close the Browser
		wUtil.closeBrowser(driver);

	}

}
