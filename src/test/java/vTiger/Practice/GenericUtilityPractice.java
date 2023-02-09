package vTiger.Practice;

import java.io.IOException;

import genericUtility.JavaUtility;
import genericUtility.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException {

		JavaUtility jUtil = new JavaUtility();
		int randomNumber = jUtil.getRandomNumber();
		System.out.println(randomNumber);

		String sysDate = jUtil.getSystemDate();
		System.out.println(sysDate);

		String sysDateInFormat = jUtil.getSystemDateInFormat();
		System.out.println(sysDateInFormat);
		
		PropertyFileUtility pUtil=new PropertyFileUtility();
		String browser = pUtil.readDataFromPropertyFile("browser");
		System.out.println(browser);
		System.out.println(pUtil.readDataFromPropertyFile("url"));
		System.out.println(pUtil.readDataFromPropertyFile("username"));
		System.out.println(pUtil.readDataFromPropertyFile("password"));
	}	
}
