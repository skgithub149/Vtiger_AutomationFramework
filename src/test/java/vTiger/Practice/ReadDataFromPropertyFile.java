package vTiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException {

		// Step 1: Convert the property file into java readable object using file input stream
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");

		// Step 2: Create the instance of properties class from java.util
		Properties pObj = new Properties();

		// Step 3: Load the file input stream in properties class object
		pObj.load(fis);

		// Step 4: Provide the key and read the value
		System.out.println(pObj.getProperty("browser") + "\n" + pObj.getProperty("url"));
		System.out.println(pObj.getProperty("username") + "\n" + pObj.getProperty("password"));
	}
}
