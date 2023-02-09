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

public class BaseClassPractice2 {

	@BeforeSuite
	public void bsConfig() {
		System.out.println("beforeSuite2");
	}

	@AfterSuite
	public void asConfig() {
		System.out.println("afterSuite2");
	}

	@BeforeTest
	public void btConfig() {
		System.out.println("beforeTest2");
	}

	@AfterTest
	public void atConfig() {
		System.out.println("afterTest2");
	}

	@BeforeClass
	public void bcConfig() {
		System.out.println("beforeClass2");
	}

	@AfterClass
	public void acConfig() {
		System.out.println("afterClass2");
	}

	@BeforeMethod
	public void bmConfig() {
		System.out.println("beforeMethod2");
	}

	@AfterMethod
	public void amConfig() {
		System.out.println("afterMethod2");
	}

	@Test
	public void test1() {
		System.out.println("test2a");
	}

	@Test
	public void test2() {
		System.out.println("test2b");
	}

}
