package organizations.pom;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationWithIndustryAndTypePOM {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Step 1: Create instance of all required libraries
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();

		// Step 2: Read all required data from external resource
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
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(Username, Password);

		// Step 5: Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLnk();

		// Step 6: Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrganizationLookupImage();

		// Step 7: Create Organization with Industry and Type and Save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(organizationName, industryName, typeName);

		// Step 8: Validate newly created organization
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String orgHeader = oip.getOrganizationHeader();
		if (orgHeader.contains(organizationName)) {
			System.out.println(orgHeader + " TestCase Passed");
		} else {
			System.out.println(orgHeader + " TestCase Fail");
		}

		// Validate industry and type drop down
		String expectedIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		String expectedType = driver.findElement(By.id("dtlview_Type")).getText();
		if ((expectedIndustry.contains(industryName)) && (expectedType.contains(typeName)))
			System.out.println(expectedIndustry + "-" + expectedType + "-" + "TestCase Passed");
		else
			System.out.println(expectedIndustry + "-" + expectedType + "-" + "TestCase Failed");

		// Step 9: SignOut of the Application
		hp.signoutOfApp(driver);

		// Step 10: Close the Browser
		wUtil.closeBrowser(driver);
	}

}
