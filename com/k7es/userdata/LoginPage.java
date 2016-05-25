package com.k7es.userdata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class LoginPage {
	
	@DataProvider(name="loginFail")
	public Object[][] loginFail() throws IOException{
		String input = "";
		FileReader reader = new FileReader(input);
		
	}

}
