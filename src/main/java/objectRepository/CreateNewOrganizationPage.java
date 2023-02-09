package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {

	// Declaration
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name = "industry")
	private WebElement industryDropdown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropdown;

	// Initialization
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// Utilization
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}


	// Business Library
	public void createNewOrganization(String OrgName) {
		orgNameEdt.sendKeys(OrgName);
		saveBtn.click();
	}

	public void createNewOrganization(String OrgName, String industry) {
		orgNameEdt.sendKeys(OrgName);
		handleDropDown(industryDropdown, industry);
		saveBtn.click();
	}
	
	public void createNewOrganization(String Orgname, String industry, String type) {
		orgNameEdt.sendKeys(Orgname);
		handleDropDown(industryDropdown, industry);
		handleDropDown(typeDropdown, type);
		saveBtn.click();
	}

}
