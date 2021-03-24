package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AddCustomerPage;
import pageObjects.BaseClass;
import pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details....");
		
		addcust.custName("Newaz");
		addcust.custgender("male");
		addcust.custdob("06","02","1995");
		Thread.sleep(5000);
		addcust.custaddress("USA");
		addcust.custcity("Brooklyn");
		addcust.custstate("NY");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("9293057878");
		
		String email=randomstring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
			
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
	
	
}
