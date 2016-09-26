package com.k7es.po;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.k7es.test.Verify;

public class LoginPage {
	
	WebDriver loginDriver;
	PageObjectHelper helper;
	
	
	
	@FindBy(name="uname")
	WebElement inpTxtLoginUserName;
	
	
	@FindBy(name="upwd")
	WebElement inpTxtLoginPassword;
	
	
	@FindBy(css="input[value='Login']")
	WebElement btnSubmit;
	
	@FindBy(id="staySignedIn")
	WebElement chkStaySign;
	
	
	@FindBy(id="af")
	WebElement lblLoginFail;
	
	
	@FindBy(id="fg_pwd")
	WebElement lnkForgotPassword;
	
	
	@FindBy(name="win_un")
	WebElement inpTxtWindowsUserName;
	
	
	@FindBy(name="win_pwd")
	WebElement inpTxtWindowsLoginPassword;
	
	//text box to enter new password for K7ES
	
	@FindBy(name="new_pwd")
	WebElement inpTxtSetK7ESPassword;
	
	//Button to reset K7ES web console password
	
	@FindBy (id="popup_ok")
	WebElement btnResetPassword;	
	
	//Button to cancel K7ES reset web console password
	
	@FindBy (id="popcancel")
	WebElement btnCancelResetPassword;
	
	public LoginPage(WebDriver driver){
		loginDriver = driver;
		PageObjectHelper helper = new PageObjectHelper(driver);
		this.helper = helper;
	}
	
	public String getUserName(){
		try{
			return helper.getTextInputBox(inpTxtLoginUserName, 4);
			//return helper.getTextAutoInput(inpTxtLoginUserName, "Administrator");
		}
		catch(TimeoutException e){
			return null;
		}		
	}
	
	private void clearUserName(){
		helper.clear(inpTxtLoginUserName);
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
	
	public LoginPage loginFailureCase(String user, String password){
		setUserName(user);
		setPassword(password);
		helper.clickButton(btnSubmit, 4);
		return PageFactory.initElements(loginDriver, LoginPage.class);
	}
	
	public String getLoginError(int i){
		int attempt=0;
		if(i>0){attempt =i;}
		if(attempt<4){
			try{
				return helper.getTextElement(lblLoginFail);
			}
			catch(StaleElementReferenceException e){
				getLoginError(i++);
			}
		}
		return helper.getTextElement(lblLoginFail);		
	}
	
	public Dashboard loginSuccessCase(String user, String password){
		setUserName(user);
		setPassword(password);
		helper.clickButton(chkStaySign);
		helper.clickButton(btnSubmit, 4);		
		return PageFactory.initElements(loginDriver, Dashboard.class);
	}
	
	public void goForgotPassword(){
		helper.clickButton(lnkForgotPassword);
	}
	
	public String getWindowsUserNameTxtBoxValue(){
		return helper.getTextInputBox(inpTxtWindowsUserName);
	}
	
	public String getWindowsPasswordTxtBoxValue(){
		return helper.getTextInputBox(inpTxtWindowsLoginPassword);
	}
	
	public String getNewPasswordTxtBoxValue(){
		return helper.getTextInputBox(inpTxtSetK7ESPassword);
	}
	
	public LoginPage cancelForgotPassword(){
		helper.clickButton(btnCancelResetPassword);
		return this;
	}

}
