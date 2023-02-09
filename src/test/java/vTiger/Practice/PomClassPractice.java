package vTiger.Practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import objectRepository.LoginPage;

public class PomClassPractice {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("http://localhost:8888");

		LoginPage loginPage = new LoginPage(driver);
//		loginPage.getUserameEdt().sendKeys("admin");
//		loginPage.getPasswordEdt().sendKeys("manager");
//		loginPage.getLoginBtn().click();

		loginPage.loginToApp("admin", "manager"); // Optimized code to login to app
	}

}
