package com.k7es.test;

import java.io.IOException;
import java.util.List;

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
import com.k7es.userdata.L10NObjHelper;
import com.k7es.userdata.ULoginPage;


@Listeners({com.k7es.test.manager.TestListener.class})
public class TestBed extends TestManager
{
	
	private LoginPage poLoginpage;	
	private Dashboard poDashboardPage;
	private Dashboard poDashboardPage2;
	private L10NObjHelper dshbrdObjRep;
	
	@BeforeClass
	public void initializePO() throws IOException{
		poLoginpage = PageFactory.initElements(driver, LoginPage.class);		
		String inp = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\Dashboard.properties";
		L10NObjHelper dshbrdObjRep = new L10NObjHelper(inp);
		this.dshbrdObjRep = dshbrdObjRep;
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
	
	@Test(priority=10)
	public void activityWidget(){
		
		Verify verify =  new Verify();
		List<String> lWdgtAct30 = dshbrdObjRep.getProps("WDGT_ACT30");
		List<String> lWidgets = dshbrdObjRep.getProps("WDGTS");
		
		verify.verifyTrue(poDashboardPage.getActivityWdgtTitle().equals(lWidgets.get(2)),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtTitle()
					+" Expected: "+lWidgets.get(2));
		
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_THREAT).equals(lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_THREAT)),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_THREAT)
					+" Expected: "+lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_THREAT));
			
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_DEVBLK).equals(lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_DEVBLK)),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_DEVBLK)
					+" Expected: "+lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_DEVBLK));
			
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_APPBLK).equals(lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_APPBLK)),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_APPBLK)
					+" Expected: "+lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_APPBLK));
			
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_WEBBLK).equals(lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_WEBBLK)),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_WEBBLK)
					+" Expected: "+lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_WEBBLK));
			
			verify.verifyTrue(poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_VULN).equals(lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_VULN)),
					"Test Fail! Actual: "+poDashboardPage.getActivityWdgtLabel(Dashboard.LBL_ACTVTY_WDGT_VULN)
					+" Expected: "+lWdgtAct30.get(Dashboard.LBL_ACTVTY_WDGT_VULN));			
		
		verify.commitVerifyErrors();
	}
	
	public void TestMenu(){
		
	}
	
}
