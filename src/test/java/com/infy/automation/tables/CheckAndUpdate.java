package com.infy.automation.tables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CheckAndUpdate {

	public static void main(String[] args) throws IOException {
		File f = new File("D:\\ezhil\\course\\FristMaven\\src\\test\\resources\\TestData\\data.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet("Sheet2");
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			Row r = s.getRow(i);
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c = r.getCell(j);
				if (c.getStringCellValue().equals("ezhil")) {
					c.setCellValue("java");
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);
	}

}
