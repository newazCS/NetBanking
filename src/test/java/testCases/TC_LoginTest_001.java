package testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		
		
		logger.info("URL is opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickSubmit();
		logger.info("Clicked Login");
		
		String actualTitle = driver.getTitle();
		String expectedTitle ="Guru99 Bank Manager HomePage";
		
		if(actualTitle.equals(expectedTitle)) {
			Assert.assertTrue(true);
			logger.info("Test Passed");
		}else {
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Test failed");
		}
		
	}

}
