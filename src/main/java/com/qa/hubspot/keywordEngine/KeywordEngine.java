package com.qa.hubspot.keywordEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubsopt.base.Base;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	public Base b = new Base();
	public static Workbook book;
	public static Sheet sheet;
	
	public final String SCENARIO_SHEET_PATH="D:\\Selenium WorkSpace\\KeywordDriven\\src\\main\\java\\com\\qa\\hubspot\\config\\KeywordDriven.xlsx";
	
	public void startExceution(String sheetname)
	{
		FileInputStream file=null;
		
		String locatorName=null;
		String locatorvalue=null;
		
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book= WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetname);
		
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			String locator =sheet.getRow(i+1).getCell(i+1).toString().trim();
			if(!locator.equalsIgnoreCase("NA"))
			{
				locatorName = locator.split("=")[0].trim();
				locatorvalue= locator.split("=")[1].trim();
				
				String action = sheet.getRow(i+1).getCell(i+2).toString().trim();
				String value = sheet.getRow(i+1).getCell(i+3).toString().trim();
				
				if(action.equals("open browser"))
				{
					prop=new Properties();
					if(value.isEmpty() || value.equals("NA"))
					{
						driver=b.initalize(prop.getProperty("browser"));
					}
					else
					{
						driver=b.initalize(value);
					}
					
				}
				else if(action.equals("enter url"))
				{
					if(value.isEmpty() || value.equals("NA"))
					{
						driver=b.initalize(prop.getProperty("url"));
					}
					else
					{
						driver.get(value);
					}
				}
				
				else if(action.equals("quit"))
				{
					driver.quit();
				}
				
				else
				{
					System.out.println("Invalid Action !!!");
				}
				
				/* Locator Name
				 * 
				 */
				
				if(locatorName.equals("id"))
				{
					WebElement element=driver.findElement(By.id(locatorvalue));
					
					if(action.equalsIgnoreCase("sendkeys"))
					{
						element.clear();
						element.sendKeys(prop.getProperty(value));
					}
					else if(action.equalsIgnoreCase("click"))
					{
						element.click();
					}
					
					locatorName=null;
				}
				
				else if(locatorName.equals("linkText"))
				{
					WebElement element=driver.findElement(By.id(locatorvalue));
					element.click();
					locatorName=null;
				}
						
			}
		}
	}

}
