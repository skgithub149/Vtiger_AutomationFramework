package contacts.hardcoded;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContacts {

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

		// Step 3: Click on Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// Step 4: Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 5: Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Chandan");

		// Step 6: Click on Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 7: Validating newly created Contact
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText(); // capturing the text from the element
		if (contactHeader.contains("Chandan")) {
			System.out.println("Contact created---->Testcase Passed");
		} else {
			System.out.println("Contact not Created---->Testcase Failed");
		}

		// Step 8: SignOut of Application
		WebElement signoutIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signoutIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// Step 9: Close the Browser
		driver.close();

	}

}
