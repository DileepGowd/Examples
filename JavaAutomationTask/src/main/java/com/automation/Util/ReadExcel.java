package com.automation.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static Map<String, String> readExcelFile() {
		FileInputStream file = null;
		try {
			file = new FileInputStream("data.xlsx");
		} catch (FileNotFoundException e) {
			System.out.println("File not exist..");
			e.printStackTrace();
		}
		Map<String, String> map = null;
		XSSFWorkbook wb = null;
		try {
			map = new HashMap<>();
			wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(0);
			Cell uname = sheet.getRow(1).getCell(0);
			Cell pwd = sheet.getRow(1).getCell(1);
			map.put("username", uname.getStringCellValue());
			map.put("password", pwd.getStringCellValue());
		} catch (IOException e) {
			System.out.println("File dosen't exists  " + e.getMessage());
		}
		return map;
	}
	
	
}
