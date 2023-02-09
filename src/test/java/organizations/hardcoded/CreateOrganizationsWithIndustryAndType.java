package organizations.hardcoded;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationsWithIndustryAndType {

	public static void main(String[] args) {
		// Step 1: Launch the Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/index.php");

		// Step 2: Login to Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();

		// Step 3: Click on Organizations Link
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Step 4: Click on create Organization lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5: Create Organizations with Industry and Type DropDown
		driver.findElement(By.name("accountname")).sendKeys("Jsp_OldAirport");
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select select = new Select(industryDropdown);
		select.selectByValue("Energy");

		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select select2 = new Select(typeDropdown);
		select2.selectByValue("Customer");

		// Step 6: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7: Validating newly created Organization
		String expectedIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		String expectedType = driver.findElement(By.id("dtlview_Type")).getText();
		if ((expectedIndustry.contains("Jsp_OldAirport"))&&(expectedType.contains("Customer")))
			System.out.println("TestCase Passed");
		else
			System.out.println("TestCase Failed");

		// Step 8: SignOut of the Application
		WebElement signoutIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signoutIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 9: Close the Browser
		driver.close();

	}

}
