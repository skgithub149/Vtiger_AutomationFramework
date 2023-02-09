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

public class BaseClassPractice1 {

	@BeforeSuite
	public void bsConfig() {
		System.out.println("beforeSuite1");
	}

	@AfterSuite
	public void asConfig() {
		System.out.println("afterSuite1");
	}

	@BeforeTest
	public void btConfig() {
		System.out.println("beforeTest1");
	}

	@AfterTest
	public void atConfig() {
		System.out.println("afterTest1");
	}

	@BeforeClass
	public void bcConfig() {
		System.out.println("beforeClass1");
	}

	@AfterClass
	public void acConfig() {
		System.out.println("afterClass1");
	}

	@BeforeMethod
	public void bmConfig() {
		System.out.println("beforeMethod1");
	}

	@AfterMethod
	public void amConfig() {
		System.out.println("afterMethod1");
	}

	@Test
	public void test() {
		System.out.println("test1");
	}

}
