package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { // rule 1: create a pom class for every web page
							// rule 2: Class name should be the title of page and ending with page

	// Rule 3: Identify the web elements using @FindBY, @FindAll, @FindBys annotation
	@FindBy(name = "user_name")
	private WebElement userameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// Rule 4: Create a constructor to initialize the variables/web elements
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 5: Provide getters to access the web elements
	public WebElement getUserameEdt() {
		return userameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	// Business Library - Generic Methods - specific to this application only - not to be written in genericLibrary
	/**
	 * This method is used to login to application
	 * 
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME, String PASSWORD) {
		userameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		loginBtn.click();
	}

}
