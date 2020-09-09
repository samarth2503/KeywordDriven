package com.qa.hubsopt.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initalize(String browsername)
	{
		if(browsername.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","D:\\Selenium WorkSpace\\KeywordDriven\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes"))
			{
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--headless");
				driver = (WebDriver) new ChromeDriver(opt);
			}
			else
			{
				driver=(WebDriver) new ChromeDriver();
			}
		}
		
		return driver;
	}
	
	public Properties init_Properties()
	{
		try {
			FileInputStream fis = new FileInputStream("D:\\Selenium WorkSpace\\KeywordDriven\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop= new Properties();
			prop.load(fis);
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
	

}
