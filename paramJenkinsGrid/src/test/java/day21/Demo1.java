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
	 * sending the gridUrl and appURL as parameters from jenkins to test method.
	 */
	@Parameters({"gridURL","appURL"})
	@Test
	public void testA(String gridURL,String appURL) throws InterruptedException, MalformedURLException
	{
		Reporter.log("test testA method of demo2 class",true);

		//in real time url is the ip address of the remote system 
		URL url=new URL(gridURL);
		//browser 
		ChromeOptions options=new ChromeOptions();
		//using remoteWebdriver class to run the script in RemoteSystem Selenium grid.
		WebDriver driver = new RemoteWebDriver(url,options);
		driver.get(appURL);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		driver.findElement(By.xpath("//div[text()='Login ']")).click();
		System.out.println(driver.getTitle());
		Thread.sleep(7000);
		System.out.println(driver.getTitle());
		driver.findElement(By.id("logoutLink")).click();
		
		driver.quit();
	}
}
