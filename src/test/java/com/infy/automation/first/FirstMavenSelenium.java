package com.infy.automation.first;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FirstMavenSelenium {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\ezhil\\course\\FirstSelenium\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchTextBox.click();
		Actions action = new Actions(driver);
		action.moveToElement(searchTextBox).sendKeys("Rolex").sendKeys(Keys.ENTER).build().perform();

		driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();

		Set<String> allTabs = driver.getWindowHandles();
		List<String> allTabsLi = new ArrayList<String>(allTabs);
		driver.switchTo().window(allTabsLi.get(1));
		System.out.println(driver.findElement(By.xpath("//span[@class='a-color-price']")).getText());
		Thread.sleep(3000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Src = ts.getScreenshotAs(OutputType.FILE);
		File Dst = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\screenshot//amazon.jpeg");
		FileUtils.copyFile(Src, Dst);
	}

}
