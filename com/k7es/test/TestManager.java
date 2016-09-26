package com.k7es.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class TestManager {
	WebDriver driver;
	String url = "http://127.0.0.1:7070/k7biz/static/index.htm";
	
	public WebDriver getdriver(){
		return this.driver;
	}
	
	
	@BeforeClass
	public void LaunchBrowser(){
		driver.get(url);
	}
	
	
	@BeforeTest
	public void LaunchTest(){
		//Firefox Driver Initialization
		/*
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);
		this.driver = driver;
		*/
		//Chrome Driver Initialization.
		System.setProperty("webdriver.chrome.driver", "E:\\Products\\Eclipse\\WorkSpace\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		this.driver = driver;
		driver.manage().window().maximize();		
	}
	
	@AfterTest
	public void CloseTest(){
		driver.close();
		driver.quit();
	}

}
