package genericUtility;

import java.util.Date;
import java.util.Random;

/**
 * This class contains Generic methods related to Java
 * 
 * @author DHARMENDRA KUMAR
 *
 */
public class JavaUtility {

	/**
	 * This method will generate random number
	 * 
	 * @return
	 */
	public int getRandomNumber() {
		Random rd = new Random();
		int value = rd.nextInt(1000);
		return value;
	}

	/**
	 * This method is used to get the system date
	 * 
	 * @return
	 */
	public String getSystemDate() {
		Date dt = new Date();
		String value = dt.toString();
		return value;
	}

	/**
	 * This method is used to get system date in specific format
	 * 
	 * @return
	 */
	public String getSystemDateInFormat() {
		Date dt = new Date();
		String dArr[] = dt.toString().split(" ");
		String date = dArr[2];
		String month = dArr[1];
		String year = dArr[5];
		String day = dArr[0];
		String time = dArr[3].replace(":", "-");
		String dateInFormat = date + " " + month + " " + year + " " + time;
		return dateInFormat;
	}
}
