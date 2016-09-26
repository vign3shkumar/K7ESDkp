package com.k7es.userdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	
	public HashMap<Integer,List<String>> readAll(){
		//List<List<String>> paramsList = new ArrayList<List<String>>();
		HashMap<Integer,List<String>> paramsList2 = new HashMap<Integer,List<String>>();
		int i=1;
		for(String line: CSVlines){
			String[] lineParams = line.split(",");
			List<String> params = Arrays.asList(lineParams);
			paramsList2.put(i, params);
			//paramsList.add(params);
			i++;
		}
		return paramsList2;
	}
	
	public List<List<String>> lReadAll(){
		List<List<String>> paramsList = new ArrayList<List<String>>();
		//HashMap<Integer,List<String>> paramsList2 = new HashMap<Integer,List<String>>();
		int i=1;
		for(String line: CSVlines){
			String[] lineParams = line.split(",");
			List<String> params = Arrays.asList(lineParams);
			//paramsList2.put(i, params);
			paramsList.add(params);
			i++;
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
