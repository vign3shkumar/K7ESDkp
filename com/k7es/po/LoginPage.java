package com.k7es.po;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class LoginPage {
	
	WebDriver loginDriver;
	POHelper helper;
	
	
	@CacheLookup
	@FindBy(name="uname")
	WebElement inpTxtLoginUserName;
	
	@CacheLookup
	@FindBy(name="upwd")
	WebElement inpTxtLoginPassword;
	
	@CacheLookup
	@FindBy(css="input[value='Login']")
	WebElement btnSubmit;
	
	@CacheLookup
	@FindBy(id="af")
	WebElement lblLoginFail;
	
	@CacheLookup
	@FindBy(id="fg_pwd")
	WebElement lnkForgotPassword;
	
	@CacheLookup
	@FindBy(name="win_un")
	WebElement inpTxtWindowsUserName;
	
	@CacheLookup
	@FindBy(name="win_pwd")
	WebElement inpTxtWindowsLoginPassword;
	
	//text box to enter new password for K7ES
	@CacheLookup
	@FindBy(name="new_pwd")
	WebElement inpTxtSetK7ESPassword;
	
	//Button to reset K7ES web console password
	@CacheLookup
	@FindBy (id="popup_ok")
	WebElement btnResetPassword;	
	
	public LoginPage(WebDriver driver){
		loginDriver = driver;
		POHelper helper = new POHelper(driver);
		this.helper = helper;
	}
	
	public String getUserName(){
		try{
			return helper.getTextInputBox(inpTxtLoginUserName, 4);
		}
		catch(TimeoutException e){
			return null;
		}		
	}
	
	private void clearUserName(){
		inpTxtLoginUserName.clear();
	}
	
	public LoginPage setUserName(String user){
		clearUserName();
		helper.writeTextInputBox(inpTxtLoginUserName, user, 4);
		return this;
	}
	
	private void clearPassword(){
		inpTxtLoginPassword.clear();
	}
	
	public LoginPage setPassword(String password){
		clearPassword();
		helper.writeTextInputBox(inpTxtLoginPassword, password, 4);
		return this;
	}
	
	public LoginPage loginFailure(String user, String password){
		setUserName(user);
		setPassword(password);
		helper.clickButton(btnSubmit, 4);
		return PageFactory.initElements(loginDriver, LoginPage.class);
	}
	
	public String getLoginError(){
		return helper.getTextElement(lblLoginFail);
	}
	
	public Dashboard loginSuccess(String user, String password){
		setUserName(user);
		setPassword(password);
		helper.clickButton(btnSubmit, 4);
		return PageFactory.initElements(loginDriver, Dashboard.class);
	}

}
