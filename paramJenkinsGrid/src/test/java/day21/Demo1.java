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

public class Demo1 {
	/*
	 * sending the gridUrl as parameters from jenkins to test method.
	 */
	@Parameters({"gridURL"})
	@Test
	public void testA(String gridURL) throws InterruptedException, MalformedURLException
	{
		Reporter.log("test testA method of demo2 class",true);

		//in real time url is the ip address of the remote system 
		URL url=new URL(gridURL);
		//browser 
		ChromeOptions options=new ChromeOptions();
		//using remoteWebdriver class to run the script in RemoteSystem Selenium grid.
		WebDriver driver = new RemoteWebDriver(url,options);
		driver.get("http://demo.actitime.com");
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		driver.findElement(By.xpath("//div[text()='Login ']")).click();
		System.out.println(driver.getTitle());
		Thread.sleep(7000);
		driver.findElement(By.id("logoutLink")).click();
		System.out.println(driver.getTitle());
		driver.quit();
	}
}
