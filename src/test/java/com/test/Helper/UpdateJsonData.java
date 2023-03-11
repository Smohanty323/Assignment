package com.test.Helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UpdateJsonData {

/*
 * primilarly used to update the one json data in json file, first updating the data then write back to the file	
 */
	@SuppressWarnings("unchecked")
	public static void updateJson(String key, String value) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/requestPayloads/PartialUpdate.json"));
        JSONObject jsonObject = (JSONObject)obj;
        
        jsonObject.put(key, value);
        
        try {
            FileWriter file = new FileWriter("src/test/resources/requestPayloads/PartialUpdate.json");
            file.write(jsonObject.toJSONString());
            file.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
       
	}

}
