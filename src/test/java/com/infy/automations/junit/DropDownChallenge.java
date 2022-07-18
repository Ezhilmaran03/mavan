package com.infy.automations.junit;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownChallenge {

	public static void main(String[] args) {
		System.out.println("Enter the Country if u want: ");
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		System.out.println("u entered the country : " + userInput);
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.html-code-generator.com/html/drop-down/state-name");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1200)");
		WebElement userCountry = driver.findElement(By.xpath("//select[@id='user-country']"));
		Select s = new Select(userCountry);
		s.selectByVisibleText(userInput);
		WebElement userState = driver.findElement(By.xpath("//select[@id='user-state']"));
		Select s1 = new Select(userState);
		List<WebElement> allOptions = s1.getOptions();
		String lastOption = "";
		for (WebElement option : allOptions) {
			lastOption = option.getText();
		}
		System.out.println(lastOption);
		WebElement selectUserState=driver.findElement(By.xpath("//select[@id='user-state']"));
		Select s2 = new Select(selectUserState);
		s2.selectByIndex(10);
	}

}
