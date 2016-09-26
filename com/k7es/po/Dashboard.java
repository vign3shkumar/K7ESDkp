package com.k7es.po;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.k7es.constants.DashboardWidgets;
import com.k7es.userdata.DshbrdObj;
import com.k7es.userdata.L10NObjHelper;


public class Dashboard {
	
	//Constants to access 3 options under Security Status Widget.
	public static final int LNK_INSTALLPROT = 1;
	public static final int LNK_CREATEPLCY = 2;
	public static final int LNK_CREATEGRP = 3;
	
	//Constants to access all options under Activity In 30 Days Widget.
	public static final int LBL_ACTVTY_WDGT_THREAT = 1;
	public static final int LBL_ACTVTY_WDGT_DEVBLK = 2;
	public static final int LBL_ACTVTY_WDGT_APPBLK = 3;
	public static final int LBL_ACTVTY_WDGT_WEBBLK = 4;
	public static final int LBL_ACTVTY_WDGT_VULN = 5;
	
	public static final int LBL_ACTVTY_WDGT_THREAT_COUNT = 11;
	public static final int LBL_ACTVTY_WDGT_DEVBLK_COUNT = 22;
	public static final int LBL_ACTVTY_WDGT_APPBLK_COUNT = 33;
	public static final int LBL_ACTVTY_WDGT_WEBBLK_COUNT = 44;
	public static final int LBL_ACTVTY_WDGT_VULN_COUNT = 55;
	
	private WebDriver dashboardDriver;
	private PageObjectHelper helper;
	private L10NObjHelper dshbrdObjRep;
	
	public Dashboard(WebDriver driver){
		dashboardDriver = driver;
		PageObjectHelper helper = new PageObjectHelper(dashboardDriver);
		this.helper = helper;
	}
	
	private void DbrdObjReps(){
		String inp = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\Dashboard.properties";
		L10NObjHelper dshbrdObjRep = new L10NObjHelper(inp);
		this.dshbrdObjRep = dshbrdObjRep;
	}
	
	/**
	 * @param DashboardSelection - WebElement for Dashboard Tab Selection 
	 */
	@FindBy(linkText="Dashboard")
	private WebElement lnkDashboard;
	
	/**
	 * @param K7ESConsoleLogo -WebElement to conform Logged-in Page of K7ES Console by verifying the PageHeader
	 */	
	@FindBy(id = "logo_msg")
	private WebElement K7ESConsoleLogo;
	
	/**
	 * @param lnkManageClients - WebElement to navigate to Manage Clients tab.
	 */
	@FindBy(css= "#umm>li:nth-of-type(3)>.mc>a[href$='k7cmcs&1']")
	private WebElement lnkManageClients;
	
	/**
	 * @param lnkAppCtrl - WebElement to navigate to Application control tab.
	 */
	@FindBy(linkText="Application Control")
	private WebElement lnkAppCtrl;
	
	/**
	 * @param lnkServerSetting - WebElement to navigate to server setting tab.
	 */
	@FindBy(linkText="Server Settings")
	private WebElement lnkServerSetting;
	
	/**
	 * @param lnkReport - WebElement to navigate to report tab.
	 */
	@FindBy(linkText="Report")
	private WebElement lnkReport;
	
	
	/**
	 * allWidgets - WebElementS to get four widgets are displayed.
	 */	
	@FindBys({@FindBy(css=".dblen")})
	private List<WebElement> allWidgets;
	
	@FindBy(id="dbalert")
	public static WebElement WdgtNtfy;
	
	@FindBy(id="dw_sstat")
	public static WebElement WdgtSStatus;
	
	@FindBy(id="dw_summary")
	public static WebElement WdgtActvty;
	
	@FindBy(id="dw_topcomputer")
	public static WebElement WdgtThrtDetected;
	
	
	/**
	 * @param wdgtLblTitleNotification - WebElement to get Notification Widget title.
	 */	
	@FindBy(css = "#dbalert>#dbalertoutter>#divntfywidget")
	private WebElement LblwdgtTitleNotification;
	
	/**
	 * @param lbldashboardDefaultNtfyText -WebElement to get the text inside notification widget in out of box settings	
	 */
	@FindBy(css = "#dbalert>#dbalertoutter #dbalert_inner>div")
	private WebElement lbldashboardDefaultNtfyText;
	
	/**
	 * @param LblwdgtTitleSecurityStatus -WebElement to get the Security Status Widget title
	 */
	@FindBy(css = "#dw_sstat>.dbwidgetinner>.dbwidgethead")
	private WebElement LblwdgtTitleSecurityStatus;
	
	/**
	 * @param lstDbdSStatusDefOptions - WebElement to get Security Status widget options in OOB.
	 */
	@FindBy(css=".dbwidgetinner #sstatusouter>.bxinstall>.db_link")
	private List<WebElement> lstDbdSStatusDefOptions;
	
	/**
	 * @param dbdDefaultSStatusText - WebElement to get label in Security Status widget in OOB. 
	 */
	@FindBy(css=".dbwidgetinner #sstatusouter>.bxinstall>.bxinstall1")
	private WebElement dbdDefaultSStatusText;
	
	/**
	 * @param dbdSStatusOptInstall - WebElement link to install endpoint in Security status widget.
	 */
	@FindBy(css=".dbwidgetinner #sstatusouter>.bxinstall>.db_link:nth-of-type(2)")
	private WebElement dbdSStatusOptInstall;
	
	/**
	 * @param dbdSStatusOptPlcy - WebElement link to create policy in Security status widget.
	 */
	@FindBy(css=".dbwidgetinner #sstatusouter>.bxinstall>.db_link:nth-of-type(3)")
	private WebElement dbdSStatusOptPlcy;
	
	/**
	 * @param dbdSStatusOptGrp - WebElement link to create group in Security status widget.
	 */
	@FindBy(css=".dbwidgetinner #sstatusouter>.bxinstall>.db_link:nth-of-type(4)")
	private WebElement dbdSStatusOptGrp;
	
	/**
	 * @param wdgtLblTitleThreatDetection -WebElement to get the Threat Widget Title text
	 */
	@FindBy(css="#dw_topcomputer .dbwidgethead")
	private WebElement LblwdgtTitleThreatDetection;
	
	/**
	 * @param lblThreatIn24Hr -WebElement to get out-of-box string in Threat Detected in 24Hr widget
	 */
	@FindBy(css = ".dbwidgetinner #dbthreats>div")
	private WebElement lblDbdDefTextinWdgtThreatIn24Hr;
	
	/**
	 * @param LblwdgtTitleActivity -WebElement to get Activity in 30 days widget title.
	 */
	@FindBy(css="#dw_summary .dbwidgethead")
	private WebElement LblwdgtTitleActivity;	
	
	/**
	 * @param lblThreatEvntIn30Day - WebElement to get threat label in Activity widget.
	 */
	@FindBy(css= "#db_act_1>td:nth-of-type(1)")
	private WebElement lblThreatEvntIn30Day;
	
	/**
	 * @param lblThreatEvntCountIn30Day - WebElement to get threat count in Activity widget. 
	 */
	@FindBy(css= "#db_act_1>td.rightcolumn")
	private WebElement lblThreatEvntCountIn30Day;
	
	/**
	 * @param lblDevblokEvntIn30Day - WebElement to get device block label in Activity widget.
	 */
	@FindBy(css="#db_act_2>td:nth-of-type(1)" )
	private WebElement lblDevblokEvntIn30Day;
	
	
	/**
	 * @param lblDevblokEvntCountIn30Day - WebElement to get device block count in Activity widget.
	 */
	@FindBy(css= "#db_act_2>td.rightcolumn")
	private WebElement lblDevblokEvntCountIn30Day;
	
	/**
	 * @param lblAppblokEvntIn30Day - WebElement to get Application block label in Activity widget.
	 */
	@FindBy(css= "#db_act_3>td:nth-of-type(1)")
	private WebElement lblAppblokEvntIn30Day;
	
	/**
	 * @param lblAppblokEvntCountIn30Day - WebElement to get Application block count in Activity widget.
	 */
	@FindBy(css= "#db_act_3>td.rightcolumn")
	private WebElement lblAppblokEvntCountIn30Day;
	
	/**
	 * @param lblWebblokEvntIn30Day - WebElement to get website block label in activity widget.
	 */
	@FindBy(css= "#db_act_4>td:nth-of-type(1)")
	private WebElement lblWebblokEvntIn30Day;
	
	/**
	 * @param lblWebblokEvntCountIn30Day - WebElement to get website block count in Activity widget.
	 */
	@FindBy(css= "#db_act_4>td.rightcolumn")
	private WebElement lblWebblokEvntCountIn30Day;
	
	/**
	 * @param lblVulnDetektEvntIn30Day - WebElement to get vulnerability label in Activity widget.
	 */
	@FindBy(css= "#db_act_5>td:nth-of-type(1)")
	private WebElement lblVulnDetektEvntIn30Day;
	
	/**
	 * @param lblVulnDetekEvntCountIn30Day - WebElement to get vulnerability count in Activity widget.
	 */
	@FindBy(css= "#db_act_5>td.rightcolumn")
	private WebElement lblVulnDetektEvntCountIn30Day;	
	
	/**
	 *  @param lstUserSwitchMenu - WebElement to switch users and logoff.
	 */
	
	@FindBy(id="divUserName_TopLink")
	private WebElement lstUserSwitchMenu;
	
	/**
	 * @param logOff - WebElement button to log off from WebConsole
	 */
	@FindBy(css="#li_Logoff>a")
	private WebElement logOff;
	
	/**
	 * @param RefreshDasBrd - WebElement to refresh Dashboard.
	 */
	@FindBy(id="ImgRefresh")
	private WebElement RefreshDasBrd;
	
	/**
	 * @param btnRefreshSetting - WebElement to open refresh rate setting dialog of Dashboard.
	 */
	@FindBy(id="ImgRefInfo")
	private WebElement btnRefreshSetting;
	
	@FindBy(css="#umm .mc>a")
	private List<WebElement> lnkAllMenu;
	
	@FindBy(css="#umm>.menu .mc>a")
	private List<WebElement> lnkAllMenuNotSelected;	
	
	public int getWidgetsCount(){
		if(helper.waitVisibilityOfAllElements(allWidgets,10)){
			return allWidgets.size();
		}
		return 0;
	}
	
	
	/**
	 * @return size of widget as Dimension object
	 */
	public Dimension getWidgetWidth(DashboardWidgets widget){
		//JavascriptExecutor js= (JavascriptExecutor) dashboardDriver;
		WebElement element = widget.getDsbrdWdgtElement();
		if(helper.waitVisibilityOfElement(element, 10)){
			return element.getSize();
		}
		return null;
	}
	
	public Boolean moveWidgetTo(DashboardWidgets widget){
		switch(widget){
		case SECURITYSTATUS:
			moveWidgetTo(LblwdgtTitleNotification,LblwdgtTitleSecurityStatus);
			break;
		case ACTIVITYIN30DAYS:
			moveWidgetTo(LblwdgtTitleNotification,LblwdgtTitleActivity);
			break;
		case THREATIN24HR:
			moveWidgetTo(LblwdgtTitleNotification,LblwdgtTitleThreatDetection);
			break;
		default:
			break;
			
		}
		return false;
	}
	
	private Boolean moveWidgetTo(WebElement src,WebElement dst){
		if(helper.waitVisibilityOfElement(src, 10) && helper.waitVisibilityOfElement(dst, 10)){
			Point pDst = dst.getLocation();
			Actions action = new Actions(dashboardDriver);
			action.clickAndHold(src).perform();
			action.moveToElement(dst).build().perform();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			action.clickAndHold(src).perform();
			action.dragAndDropBy(src, pDst.x-10, pDst.y-10).build().perform();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			action.dragAndDropBy(src, pDst.x, pDst.y).build().perform();
			System.out.println(pDst.x+"and"+pDst.y);
			//action.dragAndDrop(src, dst).build().perform();
			if(src.getLocation().equals(pDst)){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Notification WIDGET
	 */
	
	/**
	 * 
	 * @return Notification widget title - String
	 */
	public String getNtfyWdgtTitle(){
		return helper.getTextElement(LblwdgtTitleNotification);		
	}
	
	/**
	 * 
	 * @return String - Notification widget text in OOB settings.
	 */
	public String notificationNtfyDefault(){
		return helper.getTextElement(lbldashboardDefaultNtfyText);		
	}
	
	
	/*
	 * SECURITY SETTINGS WIDGET
	 */
	
	/**
	 * 
	 * @return int - No. of Options available in Security Settings widget in OOB.
	 */
	public int securitySettingsDefOptions(){
		if (helper.isAllElementActive(lstDbdSStatusDefOptions)){
			return lstDbdSStatusDefOptions.size();
		}
		return 0;
	}
	
	public String getLblwdgtTitleSecurityStatus(){
		return helper.getTextElement(LblwdgtTitleSecurityStatus);
	}
	
	public String getSecurityStatusLblOOB(){
		return helper.getTextElement(dbdDefaultSStatusText);
	}
	
	/**
	 * 
	 * @param opt Int - Index of options from 1-3 under SS widget.
	 * @return String - Text from the input Index element in Security Status Widget.
	 */
	public String securitySettingsWidgetOptionsDef(int opt){
		if(opt == Dashboard.LNK_INSTALLPROT){
		return helper.getTextElement(dbdSStatusOptInstall);
		}
		else if (opt == Dashboard.LNK_CREATEPLCY){
			return  helper.getTextElement(dbdSStatusOptPlcy);
		}
		else if (opt == Dashboard.LNK_CREATEGRP){
			return helper.getTextElement(dbdSStatusOptGrp);
		}
		return null;
	}
	
	/*
	 * Activity In 30 Days Widget
	 */
	public String getActivityWdgtTitle(){
		return helper.getTextElement(LblwdgtTitleActivity);
	}
	
	public String getActivityWdgtLabel(int opt){
		if(opt == 1){
			return helper.getTextElement(lblThreatEvntIn30Day);
		}
		else if (opt == 2){
			return helper.getTextElement(lblDevblokEvntIn30Day);
		}
		else if(opt == 3){
			return helper.getTextElement(lblAppblokEvntIn30Day);
		}
		else if(opt == 4){
			return helper.getTextElement(lblWebblokEvntIn30Day);		
		}
		else if(opt == 5){
			return helper.getTextElement(lblVulnDetektEvntIn30Day);
		}
		return null;
	}
	
	public String getCountActivityWdgt(int opt){
		if(opt == 11){
				return helper.getTextElement(lblThreatEvntCountIn30Day);
			}
		
		else if (opt == 22){
			return helper.getTextElement(lblAppblokEvntCountIn30Day);
		}
		
		else if (opt == 33){
			return helper.getTextElement(lblDevblokEvntCountIn30Day);
		}
	
		else if (opt == 44) {
			return helper.getTextElement(lblWebblokEvntCountIn30Day);
		}
		
		else if (opt == 55){
			return helper.getTextElement(lblVulnDetektEvntCountIn30Day);
		}
		
		return "nolabel";
	}
	
	
	/*
	 * ThreatIN24 Hour Widget
	 */
	public String getLblWdgtTitleThreatDetection(){
		return helper.getTextElement(LblwdgtTitleThreatDetection);
	}
	
	public String getThreat24HrLblOOB(){
		return helper.getTextElement(lblDbdDefTextinWdgtThreatIn24Hr);
	}	
	
	public Boolean menusK7MenuBar(){
		List<String> lnkMenus = dshbrdObjRep.getProps("TABS");
		if (lnkAllMenu.size() == lnkMenus.size()) return true;
		return false;
	}
	
	public Boolean isRightMenu(int i){
		List<String> lnkMenus = dshbrdObjRep.getProps("TABS");
		switch(i){
		case 1:
			if (helper.getTextElement(lnkDashboard) == lnkMenus.get(0)){
				return true;
			}
			break;
		case 2:
			if (helper.getTextElement(lnkManageClients) == lnkMenus.get(1)){
				return true;
			}
			break;
		case 3:
			if (helper.getTextElement(lnkAppCtrl) == lnkMenus.get(2)){
				return true;
			}
			break;
		case 4:
			if (helper.getTextElement(lnkServerSetting) == lnkMenus.get(3)){
				return true;
			}
			break;
		case 5:
			if (helper.getTextElement(lnkReport) == lnkMenus.get(4)){
				return true;
			}
			break;
		}
		return false;
		
		}
		
		return true;
	}
	
	/*
	 * Navigating to different tabs.
	 */
	
	public void goManageClients(){
		helper.clickButton(lnkManageClients);
	}
	
	public void goAppCtrl(){
		helper.clickButton(lnkAppCtrl);
	}
	
	public void goServerSettings(){
		helper.clickButton(lnkServerSetting);
	}
	
	public void goReport(){
		helper.clickButton(lnkReport);
	}
	
	/*
	 * Log off from console
	 */
	public LoginPage logOff(){
		helper.clickButton(lstUserSwitchMenu);
		helper.clickButton(logOff);
		System.out.println("logoff");
		return PageFactory.initElements(dashboardDriver, LoginPage.class);
		
	}
	 

}
