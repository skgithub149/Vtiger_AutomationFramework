package vTiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerPractice {
	
	@Test(retryAnalyzer = genericUtility.RetryAnalyzerImplementation.class)
	public void demoTest() {
		System.out.println("Demo test executed");
		Assert.fail();
	}		
	
	@Test
	public void sampleTest() {
		System.out.println("Sample test executed");
	}
}
