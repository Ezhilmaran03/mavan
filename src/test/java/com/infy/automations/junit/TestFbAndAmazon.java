package com.infy.automations.junit;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;


public class TestFbAndAmazon extends BaseClass {

	@Test
	public void fb() throws IOException {
		launchBrowser();
		driver.get("https://www.facebook.com/");
		String[][] readExcel = readExcel("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\facebook.xlsx",
				"fb");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(readExcel[0][0]);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(readExcel[0][1]);
		driver.findElement(By.xpath("//button[@name='login']")).click();
		screenShot("fb");

	}

	@Test
	public void amazon() throws IOException {
		launchBrowser();
		driver.get("https://www.amazon.in/");
		String[][] readExcel = readExcel("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\amazon.xlsx",
				"amz");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(readExcel[0][0]);
		screenShot("amz");

	}
}
