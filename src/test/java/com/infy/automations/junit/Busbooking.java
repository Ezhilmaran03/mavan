package com.infy.automations.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Busbooking {
	static WebDriver driver;
	static String url = "https://in.bookmyshow.com/";
	// static WebElement fromCity;
	List<String> li = new ArrayList<>();

	@BeforeClass
	public static void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test
	public void makeMyTrip() throws InterruptedException {
		System.out.println("from testmethod");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='wzrk-cancel']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//img[@class='sc-bqjOQT aUKrX'])[8]")).click();
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.xpath("//div[@data-content='Rocketry']")).click();
		driver.findElement(By.xpath("(//span[text()='Book tickets'])[1]")).click();
		driver.findElement(By.xpath("(//div[@class='sc-vhz3gb-3 dRokFO'])[2]")).click();
		// driver.findElement(By.xpath("(//div[@class='date-day'])[3]")).click();
		driver.findElement(By.xpath("(//a[@class='showtime-pill'])[17]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='btnPopupAccept']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@id='pop_1']")).click();
		driver.findElement(By.xpath("//div[@id='proceed-Qty']")).click();
		driver.findElement(By.xpath("(//a[@class='_available'])[1]")).click();
		driver.findElement(By.xpath("(//span[@id='btnSTotal_reserve'])[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='basCheckbox']")).click();
		driver.findElement(By.xpath("//div[@id='prePay']")).click();
		Thread.sleep(5000);

	}

	@After
	public void readExcel() throws IOException {
		File f = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\data.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet("booking");

		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			Row rowZero = s.getRow(i);
			for (int j = 0; j < rowZero.getPhysicalNumberOfCells(); j++) {
				Cell rowCol = rowZero.getCell(j);
				 Long num = (long) rowCol.getNumericCellValue();
				 String s1 = String.valueOf(num);
				 li.add(s1);
				//li.add(rowCol.getStringCellValue());
				 System.out.println(s1);
				System.out.println(li.get(0));
				System.out.println(li.get(1));
			}
		}

		WebElement email = driver.findElement(By.xpath("//input[@id='txtEmail']"));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].value=’" + li.get(0) + "’;", email);
		email.sendKeys(li.get(0));
		WebElement mobNo = driver.findElement(By.xpath("//input[@id='txtMobile']"));

		mobNo.sendKeys(li.get(1));

		driver.findElement(By.xpath("(//a[@class='btn _cuatro'])[2]")).click();

	}

}
