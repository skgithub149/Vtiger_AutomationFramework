package vTiger.Contacts.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws IOException {
		// Step 1:Create instance of all the required libraries
//		JavaUtility jUtil = new JavaUtility();
//		WebDriverUtility wUtil = new WebDriverUtility();
//		PropertyFileUtility pUtil = new PropertyFileUtility();
//		ExcelFileUtility eUtil = new ExcelFileUtility();

		// Step 2:Read all required data from external resources
//		String Browser = pUtil.readDataFromPropertyFile("browser");
//		String Url = pUtil.readDataFromPropertyFile("url");
//		String Username = pUtil.readDataFromPropertyFile("username");
//		String Password = pUtil.readDataFromPropertyFile("password");

		// Step 1:Read the data from excel file
		String lastName = eUtil.readDataFromExcelFile("Contacts", 4, 2) + jUtil.getRandomNumber();

		// Step 3:Launch the Browser
//		WebDriver driver = null;
//		if (Browser.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
//		} else if (Browser.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			System.out.println("Invalid Browser Name");
//		}
//		wUtil.maximizeWindow(driver);
//		driver.get(Url);
//		wUtil.waitForPageLoad(driver);

		// Step 4:Login to Application
//		LoginPage lp = new LoginPage(driver);
//		lp.loginToApp(Username, Password);

		// Step 2:Click on Contacts link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactsLink();
		Reporter.log("Click on Contact link");

		// Step 3:Click on Create Contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookupImg();
		Reporter.log("Click on Create Contact lookup image");

		// Step 4:Create Contact with mandatory fields and save
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(lastName);
		Reporter.log("Create Contact with mandatory fields and save- "+lastName);

		// Step 5:Validate newly created contact
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactHeader = cip.getContactHeader();
//		if (contactHeader.contains(lastName)) {
//			System.out.println(contactHeader + "===>Testcase Passed");
//		} else {
//			System.out.println(contactHeader + "===>Testcase Failed");
//		}
		Assert.assertTrue(contactHeader.contains(lastName));
		Reporter.log("Validate newly created contact");

		// Step 9:SignOut of the Application
//		hp.signoutOfApp(driver);

		// Step 10: Close the Browser
//		wUtil.closeBrowser(driver);

	}
}
