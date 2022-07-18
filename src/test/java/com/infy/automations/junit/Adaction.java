package com.infy.automations.junit;

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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Adaction {

	static WebDriver driver;
	static String url = "https://adactinhotelapp.com/index.php";

	@BeforeClass
	public static void launchBrowser() {
		System.out.println(" From the launchBrowser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test
	public void adaction() throws InterruptedException {
		System.out.println("test method");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("ezhil121212");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("qwerty123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		driver.findElement(By.xpath("//select[@id='location']")).click();
		WebElement location = driver.findElement(By.xpath("//select[@id='location']"));
		Select s1 = new Select(location);
		s1.selectByValue("Sydney");
		WebElement hotels = driver.findElement(By.xpath("//select[@id='hotels']"));
		Select s2 = new Select(hotels);
		s2.selectByValue("Hotel Hervey");
		WebElement roomType = driver.findElement(By.xpath("//select[@id='room_type']"));
		Select s3 = new Select(roomType);
		s3.selectByValue("Super Deluxe");
		WebElement noOfRooms = driver.findElement(By.xpath("//select[@id='room_nos']"));
		Select s4 = new Select(noOfRooms);
		s4.selectByValue("1");
		WebElement checkInDate = driver.findElement(By.xpath("//input[@id='datepick_in']"));
		checkInDate.clear();
		checkInDate.sendKeys("01/07/2022");
		WebElement checkOutDate = driver.findElement(By.xpath("//input[@id='datepick_out']"));
		checkOutDate.clear();
		checkOutDate.sendKeys("02/07/2022");
		WebElement AdultsPerRoom = driver.findElement(By.xpath("//select[@id='adult_room']"));
		Select s5 = new Select(AdultsPerRoom);
		s5.selectByValue("2");
		WebElement ChildrenPerRoom = driver.findElement(By.xpath("//select[@id='child_room']"));
		Select s6 = new Select(ChildrenPerRoom);
		s6.selectByValue("1");
		driver.findElement(By.xpath("//input[@id='Submit']")).click();
		driver.findElement(By.xpath("//input[@id='radiobutton_0']")).click();
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("ezhil");
		driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("r");
		driver.findElement(By.xpath("//textarea[@id='address']")).sendKeys("xoxoxoxox");
		driver.findElement(By.xpath("//input[@id='cc_num']")).sendKeys("1234567891011121");
		WebElement CreditCardType = driver.findElement(By.xpath("//select[@id='cc_type']"));
		Select s7 = new Select(CreditCardType);
		s7.selectByValue("AMEX");
		WebElement cc_exp_month = driver.findElement(By.xpath("//select[@id='cc_exp_month']"));
		Select s8 = new Select(cc_exp_month);
		s8.selectByValue("3");
		WebElement cc_exp_year = driver.findElement(By.xpath("//select[@id='cc_exp_year']"));
		Select s9 = new Select(cc_exp_year);
		s9.selectByValue("2022");
		driver.findElement(By.xpath("//input[@id='cc_cvv']")).sendKeys("2241");
		driver.findElement(By.xpath("//input[@id='book_now']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@id='my_itinerary']")).click();
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@id='check_all']")).click();
	}

	@After
	public void writeExcel() throws IOException {
		File f = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\data.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("adaction");

		List<WebElement> allRows = driver.findElements(By.xpath("(//tbody)[5]//tr"));
		for (int row = 0; row < allRows.size(); row++) {

			List<WebElement> eachCol = driver.findElements(By.xpath("(//tbody)[5]//tr[" + (row + 1) + "]//td"));
			Row r = s.createRow(row);
			for (int col = 0; col < eachCol.size(); col++) {
				String cellValue = "(//tbody)[5]//tr[" + (row + 1) + "]//td[" + (col + 1) + "]";
				String eachCell = driver.findElement(By.xpath(cellValue)).getText();
				Cell c = r.createCell(col);
				c.setCellValue(eachCell);
				System.out.print(eachCell + " ");
			}
			System.out.println();
		}
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);
		fos.close();
	}

	@AfterClass
	public static void close() {
		System.out.println("driver close");
		driver.quit();
	}

}
