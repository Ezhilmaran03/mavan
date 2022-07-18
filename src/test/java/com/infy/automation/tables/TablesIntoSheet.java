package com.infy.automation.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TablesIntoSheet {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.guru99.com/test/web-table-element.php");
		List<WebElement> allRows = driver.findElements(By.xpath("(//tbody)//tr"));
		File f = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\data.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("table");
		for (int row = 0; row < allRows.size(); row++) {
			List<WebElement> eachRowCol = driver.findElements(By.xpath("((//tbody)//tr[" + (row + 1) + "])//td"));
			Row r = s.createRow(row);
			for (int col = 0; col < eachRowCol.size(); col++) {
				String eachCell = "((//tbody[1])//tr[" + (row + 1) + "])//td[" + (col + 1) + "]";
				String eachCellText = driver.findElement(By.xpath(eachCell)).getText();
				Cell c = r.createCell(col);
				c.setCellValue(eachCellText);
			}
			System.out.println();
		}
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);

	}

}
