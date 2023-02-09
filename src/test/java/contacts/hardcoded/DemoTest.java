package contacts.hardcoded;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;

@Listeners(genericUtility.ListenersImplementation.class)
public class DemoTest extends BaseClass {

	@Test()
	public void demoTest() throws InterruptedException {
		System.out.println("----DEMO----");
		Thread.sleep(2000);
	}

	@Test
	public void sampleTest() throws InterruptedException {
		System.out.println("---SAMPLE---");
		Thread.sleep(2000);
		Assert.fail();
	}

}
