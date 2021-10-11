package com.ebank.TestCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ebank.Utilities.readConfig;

public class BaseClass {
	
	readConfig rc = new readConfig();
	
	
	public String URL = rc.getApplicationURL();
	public String username = rc.getUsername();
	public String password = rc.getPassword();
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(BaseClass.class);
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeSuite
	public void preSetUp()
	{
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		//String repName="Test-Report-"+timeStamp+".html";

		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/extent-reports/Test-Report.html");//specify location of the report
		htmlReporter.config().setDocumentTitle("Automation Report"); //Title of the report
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Dheeba");
		extent.setSystemInfo("Browser Name", "Chrome");
		System.out.println("Settingup extent report");
	}

	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser)
	{
		System.out.println("Open the driver");
		log.info("Open the driver");
		
		System.setProperty("webdriver.chrome.driver",rc.getChromePath());
		driver = new ChromeDriver();
		
		log.info("Browser is launched");
		System.out.println("Browser is launched");
		
		driver.get(URL);
		
		log.info("Url is launched");
		System.out.println("Url is launched");
		
		driver.manage().window().maximize();
		
		log.info("Browser is maximized");
		System.out.println("Browser is maximized");


	}


	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "Test Case Failed is " +result.getName()); // To add name in extent report
			test.log(Status.FAIL, "Test Case Failed is " +result.getThrowable()); //To add error or exception 

			String ScreenshotPath = TC_LoginTest_001.getScreenshot(driver, result.getName());

			test.addScreenCaptureFromPath(ScreenshotPath);
		} else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, "Test Case Skipped is " +result.getName());
		}else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "Test Case Passed is " +result.getName());
		}
		driver.quit();
	}	


	@AfterSuite
	public void endReport()
	{
		extent.flush();

	}
	public static String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir")+"/Screenshots/"+ScreenshotName +dateName+".png" ;
		File finalDestination = new File(destination);
		FileUtils.copyFile(src, finalDestination);
		return destination;


	}
	
}
	
	/*@BeforeMethod
	public void setup()
	{
	
		System.out.println("Open the driver");
		log.info("Open the driver");
		
		System.setProperty("webdriver.chrome.driver",rc.getChromePath());
		driver = new ChromeDriver();
		log.info("Browser is launched");
		System.out.println("Browser is launched");
		
		driver.manage().window().maximize();
		log.info("Browser is maximized");
		System.out.println("Browser is maximized");
		
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		log.info("Url is launched");
		System.out.println("Url is launched");
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
}*/
