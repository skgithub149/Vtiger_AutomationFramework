package vTiger.Practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseClassPractice3 {

	@BeforeSuite
	public void bsConfig() {
		System.out.println("beforeSuite3");
	}

	@AfterSuite
	public void asConfig() {
		System.out.println("afterSuite3");
	}

	@BeforeTest
	public void btConfig() {
		System.out.println("beforeTest3");
	}

	@AfterTest
	public void atConfig() {
		System.out.println("afterTest3");
	}

	@BeforeClass
	public void bcConfig() {
		System.out.println("beforeClass3");
	}

	@AfterClass
	public void acConfig() {
		System.out.println("afterClass3");
	}

	@BeforeMethod
	public void bmConfig() {
		System.out.println("beforeMethod3");
	}

	@AfterMethod
	public void amConfig() {
		System.out.println("afterMethod3");
	}

	@Test
	public void test() {
		System.out.println("test3");
	}

}
