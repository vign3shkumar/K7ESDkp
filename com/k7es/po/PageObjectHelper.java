package com.k7es.po;

import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class PageObjectHelper {
	WebDriver helperDriver;
	final int timeOut = 4;
	
	public PageObjectHelper(WebDriver driver){
		helperDriver = driver;
	}
	
	
	public void clear(WebElement element){
		if(waitVisibilityOfElement(element, timeOut)){
			element.clear();
		}
	}
	
	//wait for element to be visible for 4 seconds and get text
	public String getTextElement(WebElement element) throws TimeoutException{
		try{
			if (waitVisibilityOfElement(element ,timeOut)){
				return element.getText();
			}
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
		return null;
	}	
	
	public Boolean isAllElementActive(List<WebElement> element) throws TimeoutException{
		try{
			if (waitVisibilityOfAllElements(element ,timeOut)){
				return true;
			}
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
		return false;
		
	}
	
	public Boolean isElementActive(WebElement element) throws TimeoutException{
		try{
			if (waitVisibilityOfElement(element ,timeOut)){
				return true;
			}
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
		return false;
		
	}
	
	//wait for element to be visible for t seconds and get text.
	public String getTextElement(WebElement element, int time) throws TimeoutException{
		try{
			if (waitVisibilityOfElement(element ,time)){
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
			if(waitVisibilityOfElement(element,timeOut)){
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
			if(waitVisibilityOfElement(element,time)){
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
				if (waitVisibilityOfElement(element ,timeOut)){
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
			if (waitVisibilityOfElement(element ,time)){
				return element.getAttribute("value");
			}
		}
		catch(TimeoutException e){			
			throw new TimeoutException(e);
		}
		return null;
	}
	
	//wait for input box element with text auto-filled.
	public String getTextAutoInput(WebElement element, String text){
		try{
			if(waitVisibilityOfText(element,text)){
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
			if (waitVisibilityOfElement(element,timeOut)){
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
			if (waitVisibilityOfElement(element,time)){
				element.click();
			}
		}
		catch(TimeoutException e ){
			throw new TimeoutException(e);
		}
	}
	
	public boolean waitVisibilityOfElement(WebElement element, int time) throws TimeoutException{
		try{
			WebDriverWait wait = new WebDriverWait(helperDriver,time);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(TimeoutException e){
			Reporter.log("Timed out Exception occurred before visibility of element, "+element.toString());
			throw new TimeoutException(e);
		}	
	}
	
	public boolean waitVisibilityOfText(WebElement element, String text) throws TimeoutException{
		int time =timeOut;
		try{
			WebDriverWait wait = new WebDriverWait(helperDriver,time);
			wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
			return true;
		}
		catch(TimeoutException e){
			Reporter.log("Timed out Exception occurred before visibility of element, "+element.toString());
			throw new TimeoutException(e);
		}
	}
	
	public boolean waitVisibilityOfAllElements(List<WebElement> element, int time) throws TimeoutException{
		try{
			WebDriverWait wait = new WebDriverWait(helperDriver,time);
			//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dblen")));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			return true;
		}
		catch(TimeoutException e){
			Reporter.log("Timed out Exception occurred before visibility of element, "+element.toString());
			throw new TimeoutException(e);
		}	
	}
	public Boolean isTextBoxEnabled(WebElement element, int time) throws TimeoutException{ 
		try{
			waitVisibilityOfElement(element, time);
			return element.isEnabled();
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
	}
	
	public Boolean isTextBoxEnabled(WebElement element) throws TimeoutException{ 
		try{
			waitVisibilityOfElement(element, timeOut);
			return element.isEnabled();
		}
		catch(TimeoutException e){
			throw new TimeoutException(e);
		}
	}

}
