package com.ebank.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ebank.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass 
{


	@Test(priority =1)
	public void loginTest() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		BasicConfigurator.configure();
		test =extent.createTest("TS_001");
		
		test.pass("Browser is opened");
		log.info("Browser is opened");
		Thread.sleep(3000);
		lp.setUserName(username);
		test.pass("Username is entered");
		log.info("Username is entered");
		
		lp.setPassword(password);
		log.info("Password is entered");
		test.pass("Password is entered");
		
		lp.clickSubmit();
		test.pass("Clicked on SUbmit");
		log.info("Clicked on SUbmit");
		//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	@Test(priority =2)
	public void VerifyTitle() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		BasicConfigurator.configure();
		test =extent.createTest("TS_002");
		Thread.sleep(3000);
		try {
		Assert.assertEquals(driver.getTitle(), "Guru99");
		test.pass("Verified Title");
		log.info("Verified Title");
		}catch(Exception e)
			{
			e.printStackTrace();
			test.pass("Not Verified Title");
			log.info("Verified Title");
		}
		test.info("Verified Title");
		log.info("Verified Title");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	
	@Test(priority =3)
	public void ToSkip() throws InterruptedException
	{
		test =extent.createTest("TS_002");
		Thread.sleep(3000);
		log.info("To Skip");
		
	}
}
