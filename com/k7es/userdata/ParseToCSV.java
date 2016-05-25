package com.k7es.userdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseToCSV {
	List<String> CSVlines;
	int i;
	public ParseToCSV(String inputFile){
		String line = null;
		this.i = 0;
		CSVlines = new ArrayList<String>();
		try
		{
			FileReader reader = new FileReader(inputFile);
			BufferedReader bReader = new BufferedReader(reader);
			while((line = bReader.readLine())!= null){
				CSVlines.add(line);
				}
			bReader.close();
			reader.close();	
		}
		catch(IOException e){
			
		}
	}
	
	public List<List<String>> readAll(){
		List<List<String>> paramsList = new ArrayList<List<String>>();
		for(String line: CSVlines){
			String[] lineParams = line.split(",");
			List<String> params = Arrays.asList(lineParams);
			paramsList.add(params);
		}
		return paramsList;
	}
	
	public List<String> readLine(){
		List<String> params =null;
		if (i<= CSVlines.size()){
			String[] lineParams = CSVlines.get(i).split(",");
			params = Arrays.asList(lineParams);
		}
		i++;
		return params;
		
	}

}
