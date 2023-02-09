package organizations.hardcoded;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizations {

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

		// Step 4: Click on Create Organization lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5: Create Organizations with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("Qsp_BTM");

		// Step 6: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7: Validating newly created Organization
		String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationHeader.contains("Qsp_BTM"))
			System.out.println("Organization created--->TestCase Passed");
		else
			System.out.println("Organization not created--->TestCase Failed");

		// Step 8: SignOut of the Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 9: Close the Browser
		driver.quit();

	}

}
