package com.k7es.userdata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

public class Dashboard2 {
	@DataProvider(name="wdgtSize")
	public static Object[][] dmnNotificationWdgt(){
		String wdgtFile = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\wdgtSize.csv";
		ParseToCSV wdgtFileList = new ParseToCSV(wdgtFile);
		HashMap<Integer,List<String>> data = wdgtFileList.readAll();
		Iterator<Entry<Integer, List<String>>> iWdgtSize = data.entrySet().iterator();
		int i = 0,j=0;
		Object[][] wdgtFileObj = new Object[data.size()][1];
		while(iWdgtSize.hasNext()){
			Map.Entry<Integer,List<String>> datum = (Map.Entry<Integer,List<String>>) iWdgtSize.next();
			Dimension dmn = new Dimension(Integer.parseInt(datum.getValue().get(0)),Integer.parseInt(datum.getValue().get(1)));
			wdgtFileObj[i][j]=dmn;
			j++;
			i++;				
			}	
		return wdgtFileObj;
	}
	
	@DataProvider(name="activityWidget")
	public static Object[][] activityWdgt(){
		String actWdgt = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\activityWidget.csv";
		ParseToCSV actWdgtTestCSV = new ParseToCSV(actWdgt);
		HashMap<Integer,List<String>> data = actWdgtTestCSV.readAll();
		return dataFormatter(data);
	}
	
	private static Object[][] dataFormatter(HashMap<Integer,List<String>> input){
		int i=0;
		Object[][] objInp = new Object[input.size()][2];
		for(Entry<Integer, List<String>> eInput : input.entrySet()){
			int j=0;
			for(String val: eInput.getValue()){
				objInp[i][j]=val;
				j++;
			}
			i++;
		}
		return objInp;
	}
}
