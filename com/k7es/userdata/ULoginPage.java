package com.k7es.userdata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

public class ULoginPage {
	
	@DataProvider(name="users")
	public static Object[][] users(){
		String usersFile = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\users.csv";
		ParseToCSV usersList = new ParseToCSV(usersFile);
		List<List<String>> data = usersList.readAll();
		Object[][] userObj = new Object[data.size()][data.get(0).size()];
		int i = 0;
		for(List<String> params: data){
			int j = 0;
			for(String param: params){
				userObj[i][j]=param;
				j++;
			}
			i++;
		}
		
		return userObj;
	}
	
	@DataProvider(name="password")
	public static Object[][] loginFail(){
		String passwords = "E:\\Products\\Eclipse\\WorkSpace\\K7ESBVT\\testData\\password.csv";
		ParseToCSV pwdList = new ParseToCSV(passwords);
		List<List<String>> pwdParams = pwdList.readAll();
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

}
