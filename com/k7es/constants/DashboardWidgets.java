package com.k7es.constants;

import org.openqa.selenium.WebElement;

import com.k7es.po.Dashboard;

public enum DashboardWidgets {
	SECURITYSTATUS(Dashboard.WdgtSStatus),NOTIFICATION(Dashboard.WdgtNtfy),
	THREATIN24HR(Dashboard.WdgtThrtDetected),ACTIVITYIN30DAYS(Dashboard.WdgtActvty);	
	
	private final WebElement constElement;
	DashboardWidgets(WebElement element){
		this.constElement = element;
	}
	public WebElement getDsbrdWdgtElement(){return constElement;}
}
