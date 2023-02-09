package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of methods related to WebDriver actions
 * 
 * @author DHARMENDRA KUMAR
 *
 */
public class WebDriverUtility {
	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait for 20 for all the elements to get loaded
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	/**
	 * This element will wait until the particular element become visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This element will wait until the particular element become clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle dropdown using index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method will handle dropdown using value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method will handle dropdown using visible text
	 * @param text
	 * @param element
	 */
	public void handleDropDown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * This method will perform mousehover action on a specified element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element) {     
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform double click anywhere on page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on a specified element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform right click anywhere on page
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}
	
	/**
	 * This method will perform right click on a specified element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop from source to destination
	 * @param driver
	 * @param srcElement
	 * @param destElement
	 */
	public void dragAndDropAction(WebDriver driver, WebElement srcElement, WebElement destElement) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(srcElement, destElement).perform();
	}
	
	/**
	 * This method will switch to frame based on index
	 * @param driver
	 * @param frameIndex
	 */
	public void switchToFrame(WebDriver driver, int frameIndex) {
		driver.switchTo().frame(frameIndex);
	}
	
	/**
	 * This method will switch to frame based on frame name or frame id
	 * @param driver
	 * @param frameNameOrId
	 */
	public void switchToFrame(WebDriver driver, String frameNameOrId) {
		driver.switchTo().frame(frameNameOrId);
	}
	
	/**
	 * This method will switch to element based on web element
	 * @param driver
	 * @param frameElement
	 */
	public void switchToFrame(WebDriver driver, WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	/**
	 * This method will switch back to immediate parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will switch back to default frame
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will accept the alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will dismiss the alert popup
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will get the text from alert popup and return it to caller
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will switch to window based on partial window title
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToWindow(WebDriver driver, String partialTitle) {
		Set<String> winIds = driver.getWindowHandles();
		for (String winId : winIds) {
			String currentTitle = driver.switchTo().window(winId).getTitle();
			if (currentTitle.contains(partialTitle)) {
				break;
			}
		}

	}
	
	/**
	 * This method will take the screenshot and store it in a folder
	 * @param driver
	 * @param screenshotsName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver, String screenshotsName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShots/" + screenshotsName + ".png");
		FileUtils.copyFile(src, dest);

		return dest.getAbsolutePath();
	}
	
	/**
	 * This method will scroll down randomly
	 * @param driver
	 */
	public void scrollAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}
	
	/**
	 * This method will scroll down until the specific element
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element) {
		int y = element.getLocation().getY();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + y + ")", element);
	}
	
	/**
	 * This method will close the browser
	 * @param driver
	 */
	public void closeBrowser(WebDriver driver) {
		driver.quit();
	}


}
