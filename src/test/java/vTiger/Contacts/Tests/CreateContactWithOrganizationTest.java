package vTiger.Contacts.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class CreateContactWithOrganizationTest extends BaseClass {
	
	@Test(groups = "SmokeTest")
	public void createContactWithOrganizationPOMTest() throws EncryptedDocumentException, IOException {
// 		Step 1: Create instance of all required libraries
//		JavaUtility jUtil = new JavaUtility();
//		WebDriverUtility wUtil = new WebDriverUtility();
//		PropertyFileUtility pUtil = new PropertyFileUtility();
//		ExcelFileUtility eUtil = new ExcelFileUtility();

//		Step 2: Read all required data from the external resources
//		String Browser = pUtil.readDataFromPropertyFile("browser");
//		String Url = pUtil.readDataFromPropertyFile("url");
//		String Username = pUtil.readDataFromPropertyFile("username");
//		String Password = pUtil.readDataFromPropertyFile("password");

		// Step 1: Read all required data from excel file
		String organizationName = eUtil.readDataFromExcelFile("Organizations", 1, 2) + jUtil.getRandomNumber();
		String lastName = eUtil.readDataFromExcelFile("Contacts", 4, 2) + jUtil.getRandomNumber();

// 		Step 3: Launch the Browser
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

//		Step 2: Login to Application
//		LoginPage lp = new LoginPage(driver);
//		lp.loginToApp(Username, Password);

		// Step 2: Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLnk();
		Reporter.log("Click on Organizations link");


		// Step 3: Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrganizationLookupImage();
		Reporter.log("Click on Create Organization lookup image");


		// Step 4: Create Organization with mandatory fields and save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(organizationName);
		Reporter.log("Create Organization with mandatory fields and save- "+organizationName);

		// Step 5: Validate newly created organization
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String orgHeader = oip.getOrganizationHeader();
//		if (orgHeader.contains(organizationName)) {
//			System.out.println(orgHeader + "\n" + "TestCase Passed");
//		} else {
//			System.out.println(orgHeader + "\n" + "TestCase Failed");
//		}
		assertTrue(orgHeader.contains(organizationName));
		Reporter.log("Validate newly created organization");

		// Step 6: Click on Contacts link
		hp.clickOnContactsLink();
		Reporter.log("Click on Contacts link");

		// Step 7: Click on Create Contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookupImg();
		Reporter.log("Click on Contacts link");

		// Step 8: Create Contact with Organization and Save
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(driver, lastName, "Accounts", organizationName, "Contacts");
		Reporter.log("Create Contact with Organization and Save");

		// Step 9: Validate newly created contact
		ContactInfoPage cif = new ContactInfoPage(driver);
		String contactHeader = cif.getContactHeader();
//		if (contactHeader.contains(lastName)) {
//			System.out.println(contactHeader + "\n" + "TestCase Passed");
//		} else {
//			System.out.println(contactHeader + "\n" + "TestCase Failed");
//		}
		assertTrue(contactHeader.contains(lastName));
		Reporter.log("Validate newly created contact");

//		Step 13: Logout Of the Application
//		hp.signoutOfApp(driver);

//		Step 14: Close the Browser
//		wUtil.closeBrowser(driver);

	}

}
