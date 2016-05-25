package com.k7es.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.k7es.po.LoginPage;
import com.k7es.userdata.ULoginPage;


@Listeners({com.k7es.test.manager.TestListener.class})
public class TestBed extends TestManager
{
	
	private LoginPage poLoginpage;	
	
	@BeforeTest
	public void initializePO(){
		poLoginpage = PageFactory.initElements(driver, LoginPage.class);
	}
	
	@Test(priority=1)
	public void userNameAssertion()
	{	
		
		Assert.assertTrue(poLoginpage.getUserName().toLowerCase().equals("administrator"),
				"Expected : administraotr, Actual: "+poLoginpage.getUserName());	
	}
	
	
	@Test(priority=2,dataProvider="users", dataProviderClass=ULoginPage.class)
	public void newUserName(String userName)
	{
		
		poLoginpage.setUserName(userName);
		Assert.assertTrue(poLoginpage.getUserName().equals(userName),
				"Expected : "+userName+", Actual: "+poLoginpage.getUserName());
	}
	
	@Test(priority=3,dataProvider="password", dataProviderClass=ULoginPage.class)
	public void loginFail(String userName, String password, String loginError)
	{
		
		poLoginpage = poLoginpage.loginFailure(userName, password);
		Assert.assertTrue(poLoginpage.getLoginError().equals(loginError),
				"Expected :"+loginError+" Actual: "+poLoginpage.getLoginError());
	}

}
