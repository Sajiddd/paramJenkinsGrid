package day21;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Demo2 {
	/*
	 * integrate ExtentReports in jenkins. 
	 * sending the gridUrl and appURL as parameters from jenkins to test method.
	 */
	@Parameters({"gridURL","appURL"})
	@Test
	public void testA(String gridURL,String appURL) throws InterruptedException, MalformedURLException
	{
		ExtentReports report=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter("report/Spark.html");
		report.attachReporter(spark);
		ExtentTest test = report.createTest("Demo2");
		
		Reporter.log("test testA method of demo2 class",true);
		//in real time url is the ip address of the remote system 
		URL url=new URL(gridURL);
		//browser 
		ChromeOptions options=new ChromeOptions();
		//using remoteWebdriver class to run the script in RemoteSystem Selenium grid.
		WebDriver driver = new RemoteWebDriver(url,options);
		test.info("Entering the URL");
		driver.get(appURL);
		test.info("getting the title of the login page");
		System.out.println(driver.getTitle());
		test.info("Entering the username");
		driver.findElement(By.id("username")).sendKeys("admin");
		test.info("Entering the password");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		test.info("clicking on login button");
		driver.findElement(By.xpath("//div[text()='Login ']")).click();
		Thread.sleep(7000);
		test.info("getting the title of the HomePage");
		System.out.println(driver.getTitle());
		test.info("clicking on logoutLink button");
		driver.findElement(By.id("logoutLink")).click();
		test.info("closing the browser");
		driver.quit();
		//generate the extent report
		report.flush();
	}
}
