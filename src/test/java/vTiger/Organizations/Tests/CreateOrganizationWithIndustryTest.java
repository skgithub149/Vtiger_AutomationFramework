package vTiger.Organizations.Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
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
public class CreateOrganizationWithIndustryTest extends BaseClass {

	@Test
	public void createOrganizationWithIndustryTest() throws EncryptedDocumentException, IOException {

//		Step 1: Create instance of all required libraries
//		JavaUtility jUtil = new JavaUtility();
//		WebDriverUtility wUtil = new WebDriverUtility();
//		PropertyFileUtility pUtil = new PropertyFileUtility();
//		ExcelFileUtility eUtil = new ExcelFileUtility();

//		Step 2: Read all required data from external resource
//		String Browser = pUtil.readDataFromPropertyFile("browser");
//		String Url = pUtil.readDataFromPropertyFile("url");
//		String Username = pUtil.readDataFromPropertyFile("username");
//		String Password = pUtil.readDataFromPropertyFile("password");

		// Step 1:Read all required data from excel file
		String organizationName = eUtil.readDataFromExcelFile("Organizations", 1, 2) + jUtil.getRandomNumber();
		String industryName = eUtil.readDataFromExcelFile("Organizations", 4, 3);

//		Step 3: Launch the Browser
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

//		Step 4: Login to Application
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


		// Step 4: Create Organization with Industry and Save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(organizationName, industryName);
		Reporter.log("Create Organization with Industry and Save");


		// Step 5: Validate newly created organization
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String orgHeader = oip.getOrganizationHeader();
//		if (orgHeader.contains(organizationName)) {
//			System.out.println(orgHeader + " TestCase Passed");
//		} else {
//			System.out.println(orgHeader + " TestCase Fail");
//		}
		Assert.assertTrue(orgHeader.contains(organizationName));
		Reporter.log("Validate newly created organization");


		// Validating industry drop down
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
//		if (actualIndustry.contains(industryName))
//			System.out.println(actualIndustry + "\n" + "Organization Created--->TestCase Passed");
//		else
//			System.out.println(actualIndustry + "\n" + "Organization Created--->TestCase Failed");
		Assert.assertTrue(actualIndustry.contains(industryName));
		Reporter.log("Validating industry drop down");


//		Step 6: SignOut of the Application
//		hp.signoutOfApp(driver);

//		Step 7: Close the Browser
//		wUtil.closeBrowser(driver);

	}

}
