package com.test.Bookings;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.Assert;

import com.test.Helper.ReadPropertyFiles;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIDs {

	public static Response response=null;

/*
* used rest assured methods to get the response for getbookingdetail by id booking request	
*/	
	public static Response getBookingDetailsById(int bookingId) throws FileNotFoundException, IOException{

			try {
				String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
				String resource = ReadPropertyFiles.getProperty("ApplicationConfig", "booking_resource");
				
				RestAssured.baseURI=uri;
				response = 
						given()
						.header("Content-Type","application/json")
						.when().get(resource+"/"+bookingId)
						.then().log().body().extract().response();
				
			}catch(Exception e)
			{
				Assert.assertFalse(true, "error in get booking request by Id");
			}
		
		return response;	
    }

/*
* Validate response status code as per standard 
*/	
	public static void validateGetBookingDetails()
	{
		if(response.getStatusCode()==HttpStatus.SC_OK)
		{
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	
//	============================================ get bookingIDS ==================================================================
/*
* used rest assured methods to get the response for all booking ids request	
*/	
	public static Response getBookingIDSList(){
		
			try {
				String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
				String resource = ReadPropertyFiles.getProperty("ApplicationConfig", "booking_resource");
				
				RestAssured.baseURI=uri;
				response = 
						given()
						.header("Content-Type","application/json")
						.when().get(resource)
						.then().log().body().extract().response();
				
			}catch(Exception e)
			{
				Assert.assertFalse(true, "error in get bookingIDs");
			}
			return response;		
	}

/*
* Validate response status code as per standard 
*/	
	public static void validateGetBookingIDs()
	{
		if(response.getStatusCode()==HttpStatus.SC_OK)
		{
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
}
