package com.k7es.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.k7es.po.Dashboard;
import com.k7es.po.LoginPage;

public class DummyTests extends TestManager {
	
	private LoginPage poLoginpage;	
	private Dashboard poDashboardPage;
	private Dashboard poDashboardPage2;
	
	@BeforeClass
	public void initializePO(){
		poLoginpage = PageFactory.initElements(driver, LoginPage.class);
	}
	
	@Test
	public void dummies(){
		poDashboardPage = poLoginpage.loginSuccessCase("Administrator", "test123!@#");
		poDashboardPage.securitySettingsDefOptions();
	}
	

}
