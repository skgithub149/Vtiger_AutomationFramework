package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains Generic methods related to Property File
 * @author DHARMENDRA KUMAR
 *
 */
public class PropertyFileUtility {
	
	/**
	 * This method will read the data from Property file and return the value
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException {							
		FileInputStream fis=new FileInputStream(IConstantsUtility.PROPERTY_FILE_PATH);
		Properties pObj=new Properties();
		pObj.load(fis);
		String value = pObj.getProperty(key);
		return value;
	}
}
