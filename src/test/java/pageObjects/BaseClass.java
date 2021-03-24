package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();

	//common for every test case
	public String baseURL = readconfig.getApplicationURL();
	public String username= readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		 logger = Logger.getLogger("NetBankingProject");
		 PropertyConfigurator.configure("log4j.properties");
		 
		 if(br.equalsIgnoreCase("chrome")) {	 
			 System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			 driver = new ChromeDriver();
		 }else if (br.equalsIgnoreCase("firefox")) {
			 System.setProperty("webdriver.gecko.driver", readconfig.getfirefoxPath());
			 driver = new FirefoxDriver();
		 } 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 driver.get(baseURL);
		 
		 
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomstring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public String randomnum() {
		String generatednum = RandomStringUtils.randomNumeric(4);
		return generatednum;
	}
	
}
