package testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.LoginPage;
import utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username provided");
		Thread.sleep(3000);
		lp.setPassword(pwd);
		logger.info("Password provided");
		Thread.sleep(3000);
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}

	}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {

		//String path = "D:\\Java\\NetBankingProject\\testData\\LoginData.xlsx";
			
	    String path = System.getProperty("user.dir") + "//testData//LoginData.xlsx";

	    int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);

		String logindata [][]= new String[rownum][colcount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0; j<colcount; j++) {
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
				
			}
		}

		return logindata;
	}

}
