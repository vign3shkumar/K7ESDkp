package com.k7es.test;

import org.testng.Assert;

public class Verify extends Assert{
	
	private StringBuilder verify;
	
	public Verify(){
		this.verify = new StringBuilder();
	}
	
	public void verifyTrue(Boolean b){
	 try
	 {
		 Assert.assertTrue(b);
	 }
	 catch(Error e){
		 verify.append(e);
	 }
	}
	
	public void verifyTrue(Boolean b, String msg){
		try
		{
			Assert.assertTrue(b, msg);
		}
		catch(Error e){
			verify.append(e);
		}
	}
	
	public void verifyFalse(Boolean b){
		try
		{
			Assert.assertFalse(b);
		}
		catch(Error e){
			verify.append(e);
		}
	}
	
	public void verifyFalse(Boolean b, String msg){
		try
		{
			Assert.assertFalse(b, msg);
		}
		catch(Error e){
			verify.append(e);
		}
	}
	public void clearVerifyErrors(){
		verify = new StringBuilder();
	}
	
	public void commitVerifyErrors(){
		String error = verify.toString();
		clearVerifyErrors();
		if(!("").equals(error)){
			Assert.fail(error);
		}
	}

}
