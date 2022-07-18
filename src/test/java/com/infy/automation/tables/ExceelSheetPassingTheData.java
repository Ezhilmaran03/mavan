package com.infy.automation.tables;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExceelSheetPassingTheData {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/automation-practice-form");
		File f = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\data.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet("Sheet3");
		int rowCount = s.getLastRowNum();
		int rowColcount = s.getRow(1).getLastCellNum();
		System.out.println(rowCount + " " + rowColcount);
		
		for (int i = 0; i <= rowCount; i++) {
			
			Row r = s.getRow(i);
			String fn = r.getCell(0).getStringCellValue();
			String ln = r.getCell(1).getStringCellValue();
			String em = r.getCell(2).getStringCellValue();
			// String mn=r.getCell(3).getStringCellValue();
			Long mn = (long) r.getCell(4).getNumericCellValue();
			String add = r.getCell(4).getStringCellValue();

			WebElement txtfn = driver.findElement(By.xpath("//input[@id='firstName']"));
			txtfn.click();
			txtfn.sendKeys(fn);
			WebElement txtLastName = driver.findElement(By.xpath("//input[@id='lastName']"));
			txtLastName.click();
			txtLastName.sendKeys(ln);
			WebElement txtEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
			txtEmail.click();
			txtEmail.sendKeys(em);
			WebElement txtMobNo = driver.findElement(By.xpath("//input[@id='userNumber']"));
			txtMobNo.click();
			String s1 = String.valueOf(mn);
			txtMobNo.sendKeys(s1);
			
			WebElement txtAdd = driver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));
			txtAdd.click();
			txtAdd.sendKeys(add);
		}
	}

}
