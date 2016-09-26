package com.k7es.test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.k7es.constants.DashboardWidgets;
import com.k7es.po.Dashboard;
import com.k7es.po.LoginPage;
import com.k7es.userdata.Dashboard2;
import com.k7es.userdata.ULoginPage;


@Listeners({com.k7es.test.manager.TestListener.class})
public class TestBed extends TestManager
{
	
	private LoginPage poLoginpage;	
	private Dashboard poDashboardPage;
	private Dashboard poDashboardPage2;
	
	@BeforeClass
	public void initializePO(){
		poLoginpage = PageFactory.initElements(driver, LoginPage.class);
	}
	
	@Test(priority=1)
	public void userNameAssertion()
	{
		
		Assert.assertTrue(poLoginpage.getUserName().toLowerCase().equals(""),
				"Expected : administrator, Actual: "+poLoginpage.getUserName());	
	}
	
	
	@Test(priority=2,dataProvider="users", dataProviderClass=ULoginPage.class, enabled=true)
	public void newUserName(String userName)
	{
		
		poLoginpage.setUserName(userName);
		Assert.assertTrue(poLoginpage.getUserName().equals(userName),
				"Expected : "+userName+", Actual: "+poLoginpage.getUserName());
	}
	
	@Test(priority=3,enabled=true,dataProvider="password", dataProviderClass=ULoginPage.class)
	public void loginFail(String userName, String password, String loginError)
	{
		
		poLoginpage = poLoginpage.loginFailureCase(userName, password);
		Assert.assertTrue(poLoginpage.getLoginError(0).equals(loginError),
				"Expected :"+loginError+" Actual: "+poLoginpage.getLoginError(0));
	}
	
	@Test(priority=4,dataProvider="loginSuccess", dataProviderClass=ULoginPage.class)
	public void loginSuccess(String password){
		String user="Administrator";
		poDashboardPage = poLoginpage.loginSuccessCase(user, password);
		System.out.println("widget size is "+poDashboardPage.getWidgetsCount());
		poLoginpage = poDashboardPage.logOff();
		
	}
	
	@Test(priority=5,enabled=false)
	public void verifyForgotPasswordDialog(){
		poLoginpage.goForgotPassword();
		Verify verify = new Verify();
		verify.verifyTrue(poLoginpage.getWindowsUserNameTxtBoxValue().equals(""), 
				"Windows user is not empty :"+poLoginpage.getWindowsUserNameTxtBoxValue());
		verify.verifyTrue(poLoginpage.getWindowsPasswordTxtBoxValue().equals(""),
				"Password is not empty : "+poLoginpage.getWindowsPasswordTxtBoxValue());
		verify.commitVerifyErrors();
		poLoginpage =poLoginpage.cancelForgotPassword();
	}
	
	@Test(priority=6,dataProvider="wdgtSize",dataProviderClass=Dashboard2.class)
	public void getwidgetWidth(Dimension dmn){
		Dimension dmnNotification;		
		poDashboardPage=poLoginpage.loginSuccessCase("administrator", "test123!@#");
		//System.out.println(poDashboardPage.strNotificationTitle());
		for(DashboardWidgets widget:DashboardWidgets.values()){
			Verify verify = new Verify();
			if(( dmnNotification = poDashboardPage.getWidgetWidth(widget))!= null){
				verify.verifyTrue(dmn.getHeight() == dmnNotification.getHeight(),
						widget+" Expected Height: "+dmn.getHeight()+"Actual: " +dmnNotification.getHeight());
				verify.verifyTrue(dmn.getWidth() == dmnNotification.getWidth(),
						widget+" Expected Width: "+dmn.getWidth()+"Actual: " +dmnNotification.getWidth());
			}
			verify.commitVerifyErrors();
		}
		
		
		//poLoginpage = poDashboardPage.logOff();
	}
	
	@Test(priority=7,enabled=false)
	public void moveWidgets() throws InterruptedException{
		Verify verify = new Verify();
		verify.verifyTrue(poDashboardPage.moveWidgetTo(DashboardWidgets.SECURITYSTATUS),
				"Security Status Widget move is failed");
		Thread.sleep(5000);
		verify.verifyTrue(poDashboardPage.moveWidgetTo(DashboardWidgets.ACTIVITYIN30DAYS),
				"ActivityIN30Days Widget move is failed");
		Thread.sleep(5000);
		verify.verifyTrue(poDashboardPage.moveWidgetTo(DashboardWidgets.THREATIN24HR),
				"ThreatIN24HR widget move is failed");
		Thread.sleep(5000);
		verify.commitVerifyErrors();
		//poDashboardPage.moveWidgetTo("SecurityStatus");		
	}
	
	@Test(priority=8)
	public void notificationWidget(){
		String NtfyTitle = "Notification";
		String NtfyText = "There are no notifications.";
		Verify verify = new Verify();
		verify.verifyTrue(poDashboardPage.getNtfyWdgtTitle().equals(NtfyTitle),
				"Test Fail! Actual: "+poDashboardPage.getNtfyWdgtTitle()+"Expected: "+NtfyTitle);
		verify.verifyTrue(poDashboardPage.notificationNtfyDefault().equals(NtfyText),
				"Test Fail! Actual: "+poDashboardPage.notificationNtfyDefault()+"Expected: "+NtfyText);
		verify.commitVerifyErrors();
	}
	
	@Test(priority=9)
	public void securityStatus(){
		String sStatusTitle="Security Status";
		String sStatusText = "You can start deploying and managing Endpoint security.";
		String sStatusOpt1 = "Install Protection";
		String sStatusOpt2 = "Create Policy";
		String sStatusOpt3 = "Create Group";
		
		Verify verify = new Verify();
		verify.verifyTrue(poDashboardPage.getLblwdgtTitleSecurityStatus().equals(sStatusTitle),
				"Test Fail! Actual: "+poDashboardPage.getLblwdgtTitleSecurityStatus()
				+" Expected: "+ sStatusTitle);
		
		verify.verifyTrue(poDashboardPage.getSecurityStatusLblOOB().equals(sStatusText),
				"Test Fail! Actual: "+poDashboardPage.getSecurityStatusLblOOB()
				+" Expected: "+sStatusText);
		
		verify.verifyTrue(poDashboardPage.securitySettingsWidgetOptionsDef(Dashboard.LNK_INSTALLPROT).equals(sStatusOpt1),
				"Test Fail! Actual: "+poDashboardPage.securitySettingsWidgetOptionsDef(Dashboard.LNK_INSTALLPROT)
				+"Expected: "+sStatusOpt1);
		
		verify.verifyTrue(poDashboardPage.securitySettingsWidgetOptionsDef(Dashboard.LNK_CREATEPLCY).equals(sStatusOpt2),
				"Test Fail! Actual: "+poDashboardPage.securitySettingsWidgetOptionsDef(Dashboard.LNK_CREATEPLCY)
				+"Expected: "+sStatusOpt2);
		
		verify.verifyTrue(poDashboardPage.securitySettingsWidgetOptionsDef(Dashboard.LNK_CREATEGRP).equals(sStatusOpt3),
				"Test Fail! Actual: "+poDashboardPage.securitySettingsWidgetOptionsDef(Dashboard.LNK_CREATEGRP)
				+"Expected: "+sStatusOpt3);
		verify.commitVerifyErrors();
	}
	
	@Test(priority=10,dataProvider="activityWidget",dataProviderClass=Dashboard2.class)
	public void activityWidget(String sI, String sActivity){
		
		Verify verify =  new Verify();
		int i;
		i=Integer.parseInt(sI);
		switch(i){
		case 0:
			verify.verifyTrue(poDashboardPage.getActivityWdgtTitle().equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtTitle()
					+" Expected: "+sActivity);
			break;
		case 1:
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_THREAT).equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_THREAT)
					+" Expected: "+sActivity);
			break;
		case 2:
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_DEVBLK).equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_DEVBLK)
					+" Expected: "+sActivity);
			break;
		case 3:
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_APPBLK).equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_APPBLK)
					+" Expected: "+sActivity);
			break;
		case 4:
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_WEBBLK).equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_WEBBLK)
					+" Expected: "+sActivity);
			break;
		case 5:
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_VULN).equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_VULN)
					+" Expected: "+sActivity);
			break;
		case 11:
			verify.verifyTrue(poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_THREAT_COUNT)
					.equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_THREAT_COUNT)
					+" Expected: "+sActivity);
			break;
		
		case 22:
			verify.verifyTrue(poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_DEVBLK_COUNT)
					.equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_DEVBLK_COUNT)
					+" Expected: "+sActivity);
			break;
		
		case 33:
			verify.verifyTrue(poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_APPBLK_COUNT)
					.equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_APPBLK_COUNT)
					+" Expected: "+sActivity);
			break;
		case 44:
			verify.verifyTrue(poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_WEBBLK_COUNT)
					.equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_WEBBLK_COUNT)
					+" Expected: "+sActivity);
			break;
		case 55:
			verify.verifyTrue(poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_VULN_COUNT)
					.equals(sActivity),
					"Test Fail! Actual: "+poDashboardPage.getCountActivityWdgt(Dashboard.LBL_ACTVTY_WDGT_VULN_COUNT)
					+" Expected: "+sActivity);
			break;
		}
		
		verify.commitVerifyErrors();
	}
	
	
}
