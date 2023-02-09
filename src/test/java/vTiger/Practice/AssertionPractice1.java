package vTiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice1 {

	@Test
	public void assertTest1() {
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");
		Assert.assertEquals(false, true);
		System.out.println("Step 5");
		System.out.println("Step 6");
		System.out.println("Step 7");
		System.out.println("Step 8");
	}

	@Test
	public void assertTest2() {
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");
		Assert.assertTrue(false);
		System.out.println("Step 5");
		System.out.println("Step 6");
		System.out.println("Step 7");
		System.out.println("Step 8");
	}

	@Test
	public void assertTest3() {
		SoftAssert sf = new SoftAssert();

		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");
		sf.assertEquals(false, true); // failed
		System.out.println("Step 5");
		System.out.println("Step 6");
		sf.assertTrue(false); // failed
		System.out.println("Step 7");
		System.out.println("Step 8");
		sf.assertAll();
	}

	@Test
	public void assertTest4() {
		SoftAssert sf = new SoftAssert();

		System.out.println("Step 1");
		System.out.println("Step 2");
		Assert.assertTrue(false); // failed
		System.out.println("Step 3");
		System.out.println("Step 4");
		sf.assertEquals(false, true); // failed
		System.out.println("Step 5");
		System.out.println("Step 6");
		sf.assertTrue(true); // pass
		System.out.println("Step 7");
		System.out.println("Step 8");
		sf.assertAll();
	}

	@Test
	public void assertTest5() {
		SoftAssert sf = new SoftAssert();

		System.out.println("Step 1");
		System.out.println("Step 2");
		sf.assertEquals(false, true); // failed
		System.out.println("Step 3");
		System.out.println("Step 4");
		sf.assertTrue(false); // failed
		System.out.println("Step 5");
		System.out.println("Step 6");
		Assert.assertTrue(false); // failed
		System.out.println("Step 7");
		System.out.println("Step 8");
		sf.assertAll(); // not getting executed, soft assert failure not getting logged
	}

}
