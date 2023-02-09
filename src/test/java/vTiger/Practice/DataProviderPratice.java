package vTiger.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPratice {

	@Test(dataProvider = "getData")
	public void test(String Phone, int Price, String Model) {
		System.out.println(Phone + " " + Price + " " + Model);
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];

		data[0][0] = "Apple";
		data[0][1] = 15000;
		data[0][2] = "A80";

		data[1][0] = "Samsung";
		data[1][1] = 12000;
		data[1][2] = "S11";
		
		return data;
		

	}
}
