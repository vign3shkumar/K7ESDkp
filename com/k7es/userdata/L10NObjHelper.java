package com.k7es.userdata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class L10NObjHelper {
	
	Properties dbrdProps;
	String defReturnval = null;
	String sSplitterforList = "\\|";
	
	public L10NObjHelper(String filename) throws IOException{
		FileInputStream inDbrdStream = new FileInputStream(filename);
		Properties dbrdProperties = new Properties();
		dbrdProperties.load(inDbrdStream);
		dbrdProps=dbrdProperties;
		inDbrdStream.close();
	}
	
	public String getProp(String propKey){
		return dbrdProps.getProperty(propKey, defReturnval);
	}
	
	public List<String> getProps(String propKey){
		List<String> lPropVals =null;
		String propVal = dbrdProps.getProperty(propKey, defReturnval);
		if(propVal != this.defReturnval){
			lPropVals = Arrays.asList(propVal.split(sSplitterforList));
		}
		return lPropVals;
	}
	
	

}
