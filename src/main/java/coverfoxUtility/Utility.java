package coverfoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	// screenshot
		public static void takescreenshot(WebDriver driver, String fileName) throws IOException {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
			File dest = new File(System.getProperty("user.dir")+"\\ScreenShots\\" + fileName
					+ timeStamp + ".png");
			FileHandler.copy(source, dest);
		}

		// excel reading
		public static String readingDataFromExcel(String excelpath, String sheetname, int rownum, int cellnum)
				throws EncryptedDocumentException, IOException {
			FileInputStream myfile = new FileInputStream(excelpath);
			String value = WorkbookFactory.create(myfile).getSheet(sheetname).getRow(rownum).getCell(cellnum)
					.getStringCellValue();
			return value;
		}

		// properties file
		public static String readDataFromPropertyFile(String key) throws IOException {
			Properties properties = new Properties();
			FileInputStream myfile = new FileInputStream(System.getProperty("user.dir") + "//config.properties");
			properties.load(myfile);
			String value = properties.getProperty(key);
			return value;

		}

}
