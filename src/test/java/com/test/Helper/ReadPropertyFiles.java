package com.test.Helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFiles {

	
	public static String getProperty(String filename,String key) throws FileNotFoundException, IOException
	{
		 Properties p=new Properties();
		 p.load(new FileInputStream("src/test/resources/config/"+filename+".properties"));
		 String value=p.getProperty(key);
		 
		 return value;
	}
	
	
}
