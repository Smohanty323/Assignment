package com.test.SetUp;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.HttpStatus;
import org.junit.Assert;

import com.test.Helper.ReadPropertyFiles;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookerHealthCheck 
{
	private static Response response =null;
	
	public static void healthCheck() throws FileNotFoundException, IOException {
		
			try {
				String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
				String ping = ReadPropertyFiles.getProperty("ApplicationConfig", "ping_resource");
				
				RestAssured.baseURI=uri;
				
				RequestSpecification request = new RequestSpecBuilder().build();
			
				response = given(request)
						   .when().get(ping)
						   .then().log().body().extract().response();
			}catch(Exception e)
			{
				e.printStackTrace();
				Assert.assertTrue(false);
			}
	}
	
	public static void validateHealthCheck()
	{
		if(response.getStatusCode()==HttpStatus.SC_CREATED)
		{
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}


}
