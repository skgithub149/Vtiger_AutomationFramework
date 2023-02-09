package vTiger.Practice;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine {

	@Test
	public void read() {

		String BROWSER = System.getProperty("browser"); // it will accept run time parameters
		System.out.println(BROWSER);

		String URL = System.getProperty("url"); // it will accept run time parameters
		System.out.println(URL);

		String USERNAME = System.getProperty("username"); // it will accept run time parameters
		System.out.println(USERNAME);

		String PASSWORD = System.getProperty("password"); // it will accept run time parameters
		System.out.println(PASSWORD);

		System.out.println("Executed");

	}

}
