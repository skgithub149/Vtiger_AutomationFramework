package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;

/**
 * This class contains all basic configuration annotations
 * @author DHARMENDRA KUMAR
 *
 */
public class BaseClass {

	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver; 								// this is used for taking screenshots in listeners

	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void bsConfig() {
		System.out.println("=====Database Connection Successful=====");
	}
	
	// @Parameters("browser")										//Invoke during XBrowser Execution
	// @BeforeTest													//Invoke during Distributed Parallel & XBrowser Execution
	@BeforeClass(groups = { "SmokeTest", "RegressionTest" })	
	public void bcConfig(/*String Browser*/) throws IOException {	//Invoke during XBrowser Execution
		String Browser = pUtil.readDataFromPropertyFile("browser");	//Comment during XBrowser Execution
		String Url = pUtil.readDataFromPropertyFile("url");

		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			sdriver = driver;										// this is used for taking screenshots in listeners
			System.out.println("=====" + Browser + "Browser Launched Successfully=====");
		} else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			sdriver = driver;										// this is used for taking screenshots in listeners
			System.out.println("=====" + Browser + "Browser Launched Successfully=====");
		} else {
			System.out.println("Invalid Browser Name");
		}
		wUtil.maximizeWindow(driver);
		driver.get(Url);
		wUtil.waitForPageLoad(driver);
	}

	@BeforeMethod(groups = { "SmokeTest", "RegressionTest" })
	public void bmconfig() throws IOException {
		String Username = pUtil.readDataFromPropertyFile("username");
		String Password = pUtil.readDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(Username, Password);
		System.out.println("=====Login to Application Successful=====");

	}

	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void amConfig() {
		HomePage hp = new HomePage(driver);
		hp.signoutOfApp(driver);
		System.out.println("=====Logout from Application Successful=====");
	}

	// @AfterTest
	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void acConfig() {
		wUtil.closeBrowser(driver);
		System.out.println("=====Bowser Closed Successfully=====");
	}

	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void asConfig() {
		System.out.println("=====Database Closed Successfully=====");
	}

}
