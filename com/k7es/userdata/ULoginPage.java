package com.k7es.userdata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.testng.annotations.DataProvider;

public class ULoginPage {
	
	@DataProvider(name="users")
	public static Object[][] users(){
		String usersFile = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\users.csv";
		ParseToCSV usersList = new ParseToCSV(usersFile);
		HashMap<Integer, List<String>> data = usersList.readAll();
		Object[][] userObj = new Object[data.size()][1];
		int i = 0;
		//Iterator<Entry<Integer,List<String>>> iUsers = data.entrySet().iterator();		
		for(Entry<Integer, List<String>> eUsers : data.entrySet()){
				int j=0;
				for(String user:eUsers.getValue()){
					userObj[i][j]=user;
					j++;
				}
				i=eUsers.getKey();
			}
		return userObj;
	}
	
	@DataProvider(name="password")
	public static Object[][] loginFail(){
		String passwords = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\password.csv";
		ParseToCSV pwdList = new ParseToCSV(passwords);
		List<List<String>> pwdParams = pwdList.lReadAll();
		Object[][] pwdObj = new Object[pwdParams.size()][pwdParams.get(0).size()];
		int i = 0;
		for(List<String> params: pwdParams){
			int j = 0;
			for(String param: params){
				pwdObj[i][j]=param;
				j++;
			}
			i++;
		}		
		return pwdObj;
		
	}
	
	@DataProvider(name="loginSuccess")
	public static Object[][] loginSuccess(){
		String password="E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\loginSuccess.csv";
		ParseToCSV passwordParams = new ParseToCSV(password);
		List<List<String>> passwordParamsList = passwordParams.lReadAll();
		Object[][] pwdObj = new Object[passwordParamsList.size()][passwordParamsList.get(0).size()];
		int i =0;
		for(List<String> passwords : passwordParamsList){
			int j =0;
			for(String param:passwords){
				pwdObj[i][j]= param;
				j++;
			}
			i++;
		}
		return pwdObj;
	}

}
