package com.ebank.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ebank.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass 

{
	@Test
	public void loginTest() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		BasicConfigurator.configure();
		test =extent.createTest("TS_001");
		
		test.pass("Browser is opened");
		log.info("Browser is opened");
		
		lp.setUserName(username);
		test.pass("Username is entered");
		log.info("Username is entered");
		
		lp.setPassword(password);
		log.info("Password is entered");
		test.pass("Password is entered");
		
		lp.clickSubmit();
		test.pass("Clicked on SUbmit");
		//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	
	
	
	

}
