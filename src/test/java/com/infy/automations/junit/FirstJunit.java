package com.infy.automations.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstJunit {
	static WebDriver driver;
	static String url = "https://www.facebook.com/";
	static WebElement txtUserName;
	static WebElement txtPass;
	static WebElement txtLogin;
	static WebElement invalid1;
	static WebElement invalid2;
	static Workbook w;
	static File f;
	static String path = "D:\\\\ezhil\\\\course\\\\FristMaven\\\\src\\\\test\\\\resources\\\\TestData\\\\data.xlsx";
	List<String> li = new ArrayList<>();

	@BeforeClass
	public static void launchBrowser() {
		Date d = new Date();
		System.out.println("Launch browser and URL" + " " + d);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		txtUserName = driver.findElement(By.xpath("//input[@name='email']"));
		txtPass = driver.findElement(By.xpath("//input[@name='pass']"));
		txtLogin = driver.findElement(By.xpath("//button[@name='login']"));

	}

	@Before
	public void readExcel() throws IOException {
		System.out.println("hi");
		f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet("fbLogin");
		Row rowZero;
		Cell rowCol;
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			rowZero = s.getRow(i);
			for (int j = 0; j < rowZero.getPhysicalNumberOfCells(); j++) {
				rowCol = rowZero.getCell(j);
				li.add(rowCol.getStringCellValue());
			}
		}

	}

	@Test
	public void fb() {
		txtUserName.sendKeys(li.get(0));
		txtPass.sendKeys(li.get(1));
		txtLogin.click();

	}

	@After
	public void info() throws IOException, Exception {
		Thread.sleep(5000);
		invalid1 = driver.findElement(By.xpath("//div[contains(text(),'Wrong credentials')]"));
		invalid2 = driver.findElement(By.xpath("//div[contains(text(),'Invalid')]"));
		f = new File(path);
		w = new XSSFWorkbook();
		Sheet s = w.createSheet("invalid");
		Row rowZero = s.createRow(0);
		Cell rowCell2 = rowZero.createCell(0);
		rowCell2.setCellValue(invalid1.getText() + " " + invalid2.getText());
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);
		fos.close();
	}

	@AfterClass
	public static void closeBrowser() {
		driver.close();
	}

}
