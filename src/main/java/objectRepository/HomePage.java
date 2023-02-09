package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility {

	// Declaration
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLnk;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLnk;
	
	// Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getOrganizationsLnk() {
		return organizationsLnk;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignoutLnk() {
		return signoutLnk;
	}
	
	public WebElement getContactsLnk() {
		return contactsLnk;
	}

	//Business Libraries
	public void clickOnOrganizationLnk() {
		organizationsLnk.click();
	}
	
	public void signoutOfApp(WebDriver driver) {
		mouseHoverAction(driver, administratorImg);
		signoutLnk.click();
	}
	
	public void clickOnContactsLink() {
		contactsLnk.click();
	}

}
