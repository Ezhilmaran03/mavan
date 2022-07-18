package com.infy.automation.tables;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SheetReadIntoDemoSites {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/automation-practice-form");
		WebElement txtFirstName = driver.findElement(By.xpath("//input[@id='firstName']"));
		WebElement txtLastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		WebElement txtEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
		WebElement txtMobNo = driver.findElement(By.xpath("//input[@id='userNumber']"));
		WebElement txtAdd = driver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));
		File f = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\data.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet("Sheet1");
		Row row1 = s.getRow(1);
		
		Cell rowCol1 = row1.getCell(0);
		txtFirstName.click();
		txtFirstName.sendKeys(rowCol1.getStringCellValue());

		Cell rowCol2 = row1.getCell(1);
		txtLastName.click();
		txtLastName.sendKeys(rowCol2.getStringCellValue());

		Cell rowCol3 = row1.getCell(2);
		txtEmail.click();
		txtEmail.sendKeys(rowCol3.getStringCellValue());

		Cell rowCol4 = row1.getCell(3);
		txtMobNo.click();

		Long num = (long) rowCol4.getNumericCellValue();
		String s1 = String.valueOf(num);
		txtMobNo.sendKeys(s1);

		Cell rowCol5 = row1.getCell(4);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", txtAdd);
		txtAdd.sendKeys(rowCol5.getStringCellValue());

	}

}
