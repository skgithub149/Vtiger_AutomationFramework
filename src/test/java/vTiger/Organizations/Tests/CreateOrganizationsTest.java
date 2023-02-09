package vTiger.Organizations.Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class CreateOrganizationsTest extends BaseClass {

	@Test(groups = "RegressionTest")
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {

//		Step 1:Create instance of all required libraries
//		JavaUtility jUtil = new JavaUtility();
//		WebDriverUtility wUtil = new WebDriverUtility();
//		PropertyFileUtility pUtil = new PropertyFileUtility();
//		ExcelFileUtility eUtil = new ExcelFileUtility();

//		Step 2:Read all required data from external resources
//		String Browser = pUtil.readDataFromPropertyFile("browser");
//		String Url = pUtil.readDataFromPropertyFile("url");
//		String Username = pUtil.readDataFromPropertyFile("username");
//		String Password = pUtil.readDataFromPropertyFile("password");

		// Step 1: Read all required data from excel file
		String organizationName = eUtil.readDataFromExcelFile("Organizations", 1, 2) + jUtil.getRandomNumber();

//		Step 3:Launch the Browser
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

//		Step 4:Login to Application
//		LoginPage lp = new LoginPage(driver);
//		lp.loginToApp(Username, Password);

		// Step 2:Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLnk();
		Reporter.log("Click on Organizations link");

		// Step 3:Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrganizationLookupImage();
		Reporter.log("Click on Create Organization lookup images");


		// Step 4:Create Organization with mandatory field and save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(organizationName);
		Reporter.log("Create Organization with mandatory field and save");


		// Step 5:Validate newly created organization
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String orgHeader = oip.getOrganizationHeader();
//		if (orgHeader.contains(organizationName)) {
//			System.out.println(orgHeader + " TestCase Passed");
//		} else {
//			System.out.println(orgHeader + " TestCase Fail");
//		}
		Assert.assertTrue(orgHeader.contains(organizationName));
		Reporter.log("Validate newly created organization");


//		Step 9:SignOut of the Application
//		hp.signoutOfApp(driver);

//		Step 10:Close the Browser
//		wUtil.closeBrowser(driver);

	}

	@Test(groups = "SmokeTest")
	public void test1() throws InterruptedException {
		System.out.println("Hi..Suraj");
		Thread.sleep(3000);
	}

	@Test(groups = { "SmokeTest", "RegressionTest" })
	public void test2() throws InterruptedException {
		System.out.println("Hi..Chandan");
		Thread.sleep(3000);
	}

	@Test
	public void test3() throws InterruptedException {
		System.out.println("Hi..Harsh");
		Thread.sleep(3000);
	}

	@Test
	public void test4() throws InterruptedException {
		System.out.println("Hi..Gungun");
		Thread.sleep(3000);
	}

}
