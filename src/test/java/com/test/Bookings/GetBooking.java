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

public class GetBooking {

	public static Response response=null;
	
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
