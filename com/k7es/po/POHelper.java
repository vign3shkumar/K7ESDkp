package com.k7es.po;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class POHelper {
	WebDriver helperDriver;
	final int timeOut = 4;
	
	public POHelper(WebDriver driver){
		helperDriver = driver;
	}
	
	//wait for element to be visible for 4 seconds and get text
	public String getTextElement(WebElement element) throws TimeoutException{
		try{
			if (waitByElement(element ,timeOut)){
				return element.getText();
			}
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
		return null;
	}	
	
	//wait for element to be visible for t seconds and get text.
	public String getTextElement(WebElement element, int time) throws TimeoutException{
		try{
			if (waitByElement(element ,time)){
				return element.getText();
			}
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
		return null;
	}
	
	//wait for element to be visible for timeout seconds and writes input text
	public void writeTextInputBox(WebElement element, String input) throws TimeoutException{
		try{
			if(waitByElement(element,timeOut)){
				element.sendKeys(input);
			}
			}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
	}
	
	//wait for element to be visible for t seconds and writes input text.
	public void writeTextInputBox(WebElement element, String input, int time) throws TimeoutException{
		try{
			if(waitByElement(element,time)){
				element.sendKeys(input);
			}
			}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
	}
	
	//wait for input box element for default seconds and returns text.
		public String getTextInputBox(WebElement element) throws TimeoutException{
			try{
				if (waitByElement(element ,timeOut)){
					return element.getAttribute("value");
				}
			}
			catch(TimeoutException e){			
				throw new TimeoutException(e);
			}
			return null;
		}
	
	//wait for input box element for t seconds and returns text.
	public String getTextInputBox(WebElement element, int time) throws TimeoutException{
		try{
			if (waitByElement(element ,time)){
				return element.getAttribute("value");
			}
		}
		catch(TimeoutException e){			
			throw new TimeoutException(e);
		}
		return null;
	}
	
	//wait for element for default seconds and click.
	public void clickButton(WebElement element) throws TimeoutException{
		try{
			if (waitByElement(element,timeOut)){
				element.click();
			}
		}
		catch(TimeoutException e ){
			throw new TimeoutException(e);
		}
	}
	
	//wait for element for t seconds and click.
	public void clickButton(WebElement element, int time) throws TimeoutException{
		try{
			if (waitByElement(element,time)){
				element.click();
			}
		}
		catch(TimeoutException e ){
			throw new TimeoutException(e);
		}
	}
	
	private boolean waitByElement(WebElement element, int time) throws TimeoutException{
		try{
			WebDriverWait wait = new WebDriverWait(helperDriver,time);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(TimeoutException e){
			Reporter.log("Timed out Exception occurred while identifing element, "+element.toString());
			throw new TimeoutException(e);
		}	
	}

}
