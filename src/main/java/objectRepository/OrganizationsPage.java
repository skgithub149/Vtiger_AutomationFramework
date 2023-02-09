package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	// Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrganizationLookupImg;

	// Initialization
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getCreateOrganizationLookupImg() {
		return createOrganizationLookupImg;
	}

	// Business Library
	public void clickOnCreateOrganizationLookupImage() {
		createOrganizationLookupImg.click();
	}

}
