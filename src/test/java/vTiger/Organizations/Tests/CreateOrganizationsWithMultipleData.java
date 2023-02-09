package vTiger.Organizations.Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class CreateOrganizationsWithMultipleData extends BaseClass {

	@Test(dataProvider = "OrgName")
	public void createOrganizationsWithMultipleData(String organizationName, String industryName)
			throws EncryptedDocumentException, IOException {

//		Step 1:Read all required data from excel file
//		String organizationName = eUtil.readDataFromExcelFile("Organizations", 1, 2) + jUtil.getRandomNumber();
//		String industryName = eUtil.readDataFromExcelFile("Organizations", 4, 3);

		String orgName = organizationName + jUtil.getRandomNumber();

		// Step 1: Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLnk();
		Reporter.log("Click on Organizations link");

		// Step 2: Click on Create Organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrganizationLookupImage();
		Reporter.log("Click on Create Organization lookup image");


		// Step 3: Create Organization with Industry and Save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(orgName, industryName);
		Reporter.log("Create Organization with Industry and Save");


		// Step 4: Validate newly created organization
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
		if (actualIndustry.contains(industryName))
			System.out.println(actualIndustry + "\n" + "Organization Created--->TestCase Passed");
		else
			System.out.println(actualIndustry + "\n" + "Organization Created--->TestCase Failed");
		Assert.assertTrue(actualIndustry.contains(industryName));
		Reporter.log("Validating industry drop down");

	}

	@DataProvider(name = "OrgName")
	public Object[][] multipleDataForOrganization() throws EncryptedDocumentException, IOException {
		Object[][] data = eUtil.readMultipeDataFromExcel("Organizations");
		return data;

	}

}
