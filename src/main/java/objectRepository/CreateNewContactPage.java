package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {

	// Declaration
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement organizationLookupImg;

	@FindBy(id = "search_txt")
	private WebElement searchEdt;

	@FindBy(name = "search")
	private WebElement searchBtn;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getOrganizationLookupImg() {
		return organizationLookupImg;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// Business Library
	public void createNewContact(String LASTNAME) {
		lastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}

	public void createNewContact(WebDriver driver, String LASTNAME, String partialTitleForOrganization, String orgName, String partialTitleForContact) {
		lastNameEdt.sendKeys(LASTNAME);
		organizationLookupImg.click();
		switchToWindow(driver, partialTitleForOrganization);
		searchEdt.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		switchToWindow(driver, partialTitleForContact);
		saveBtn.click();

	}

}
