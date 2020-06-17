package com.automation.samples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YouTrackIssueFilter {

	static WebDriver driver;

	public static void main(String[] args) {
		String str = "fgdgghDashboardghgh";
		if(!str.contains("Dashboard")) {
			System.out.println("jbdj fff dffddf fdfd");
		}
	}
	
	public static void main1(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/purpletalk/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://youtrack.xcubelabs.com");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);
		FileInputStream file = null;
		try {
			file = new FileInputStream("data.xlsx");
		} catch (FileNotFoundException e) {
			System.out.println("File not exist..");
			e.printStackTrace();
		}
		XSSFWorkbook wb = null;
		String username = "";
		String password = "";
		try {
			wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(0);
			Cell uname = sheet.getRow(1).getCell(0);
			uname.setCellType(Cell.CELL_TYPE_STRING);
			Cell pwd = sheet.getRow(1).getCell(1);
			pwd.setCellType(Cell.CELL_TYPE_STRING);
			username = uname.getStringCellValue();
			password = pwd.getStringCellValue();
		} catch (IOException e) {
			System.out.println("File dosen't exists  " + e.getMessage());
			username = "@xcubelabs.com"; // we can pass directly to darwinbox credentials..
			password = "xxx";
		}

		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
		// Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		// Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/form/div[2]/div[2]/button")).click();
		// Thread.sleep(2000);

		List<WebElement> issuesId = driver.findElements(By.cssSelector("div > a.ring-link.youtrack-issues__issue-id"));

		List<WebElement> issuesName = driver
				.findElements(By.cssSelector("div > a.ring-link.youtrack-issues__issue-summary"));

		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("YouTrackIssues");
		
		Row rowH = sheet.createRow(0);
		rowH.createCell(0).setCellValue("Priority");
		rowH.createCell(1).setCellValue("Issue_ID");
		rowH.createCell(2).setCellValue("Issue_Name");
		int rowCount = 1;
		sheet = createRowWithPriority(issuesId, issuesName,sheet,rowCount,"H");
		
		sheet = createRowWithPriority(issuesId, issuesName,sheet,rowCount,"N");
		
		sheet = createRowWithPriority(issuesId, issuesName,sheet,rowCount,"L");

		try {
			// this Writes the workbook gfgcontribute
			FileOutputStream out = new FileOutputStream(new File("YouTrackIssues.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("YouTrackIssues.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static XSSFSheet createRowWithPriority(List<WebElement> issuesId, List<WebElement> issuesName, XSSFSheet sheet, int rowCount, String pri) {
		Row row = sheet.createRow(rowCount);
		for (int i = 0, j = 1; i < issuesId.size(); i++, j++) {
			String priority = driver
					.findElement(By.cssSelector(
							"div > div.widget__content > div:nth-child(2) > div:nth-child(" + j + ") > span"))
					.getText();
			//Adding Issues for low priority
			if (priority.contains(pri)) {
				row = sheet.createRow(rowCount);
				row.createCell(0).setCellValue(priority);
				row.createCell(1).setCellValue(issuesId.get(i).getText());
				row.createCell(2).setCellValue(issuesName.get(i).getText());

				System.out.println("Elements : " + priority + " , " + issuesId.get(i).getText() + " , "
						+ issuesName.get(i).getText());
				rowCount++;
			}
		}
		return sheet;
	}

}
