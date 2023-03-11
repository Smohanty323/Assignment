package com.test.Bookings;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.Assert;

import com.test.Helper.BookingIdsHelper;
import com.test.Helper.ReadPropertyFiles;
import com.test.SetUp.BookerAuth;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBooking {
	
	public static Response response=null;
	public static int actualBookingId=0;

/*
* used rest assured methods to delete a booking by id	
*/	
	public static void deleteBooking(int bookingId) throws FileNotFoundException, IOException{

				try {
					actualBookingId=bookingId;				
					String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
					String resource = ReadPropertyFiles.getProperty("ApplicationConfig", "booking_resource");
					
					String token = "token="+BookerAuth.getToken();
					
					RestAssured.baseURI=uri;
					response = 
							given()
							.header("Content-Type","application/json")
							.header("Cookie",token)
							.when().delete(resource+"/"+bookingId)
							.then().log().body().extract().response();
					
				}catch(Exception e)
				{
					Assert.assertFalse(true, "error in delete bookings");
				}	
	}
	
/*
* Validate response status code as per standard and validating the deleted booking id is not present inside the get booking ids List 
*/	
	public static void validateDeleteBookingResponse()
	{
		if(response.getStatusCode()==HttpStatus.SC_CREATED)
		{
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
		String deletedBookingId = String.valueOf(actualBookingId);
		System.out.println(deletedBookingId);
		
		List<Integer> bookingIdLists = BookingIdsHelper.getBookingIds();
		Assert.assertTrue(!bookingIdLists.contains(deletedBookingId), "to be deleted booking id is still present in the list");
	}
}
