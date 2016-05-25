package com.k7es.test.manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.k7es.test.TestManager;

public class TestListener extends TestListenerAdapter{	
	WebDriver driver;
	
	@Override
	public void onTestFailure(ITestResult result){
		Object currentClass = result.getInstance();
		this.driver = ((TestManager) currentClass).getdriver();		
		TakeScreenShot(result);
		
		result.getParameters();
	}
	
	private void TakeScreenShot(ITestResult myresult){		
		String destFile1 = myresult.getMethod().getMethodName();
		String destFile2 = new SimpleDateFormat("YYYYMMDDHHmmssSSS").format(new Date());
		File failScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(failScreenShot, new File("E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\test-output\\ScreenShot\\"+destFile1+destFile2+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
