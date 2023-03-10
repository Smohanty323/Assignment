package com.test.Bookings;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import com.test.Helper.ReadPropertyFiles;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CreateBooking 
{
	
	public static File fi=null;
	public static Response response=null;
	
	public static Response createBookingRequest() throws FileNotFoundException, IOException {

		try {
			String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
			String resource = ReadPropertyFiles.getProperty("ApplicationConfig", "booking_resource");
			
			fi=new File("src/test/resources/requestPayloads/CreateBooking.json");
			
			RestAssured.baseURI=uri;
			response = 
					given()
					.header("Content-Type","application/json")
					.body(fi)
					.when().post(resource)
					.then().log().body().extract().response();
			
		}catch(Exception e)
		{
			Assert.assertFalse(true, "error in create booking request");
		}
		return response;
		
	}
	
/*
* Validate response status code as per standard 
*  and validation booking id generated should not be null
*/	
	public static void validateCreateBookingResponse()
	{
		if(response.getStatusCode()==HttpStatus.SC_OK)
		{
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
		String actualBookingId = response.jsonPath().get("bookingid").toString();		
		System.out.println(actualBookingId);
		Assert.assertTrue(!actualBookingId.equals(null));

	}
	
	
	

}
