package com.test.Bookings;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import com.test.Helper.ReadPropertyFiles;
import com.test.Helper.UpdateJsonData;
import com.test.SetUp.BookerAuth;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PartialUpdateBooking {
	
	public static Response response=null;
	public static File fi=null;
	
	public static String expectedFirstName=null;
	public static String expectedLastName=null;

/*
 * user Rest assured library to get response for partial update request 
 * 
 */
	public static Response partialUpdateBookingRequest(String firstname,String lastname, int bookingId) throws FileNotFoundException, IOException {

			try {
				expectedFirstName=firstname;
				expectedLastName=lastname;
				String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
				String resource = ReadPropertyFiles.getProperty("ApplicationConfig", "booking_resource");

				String token = "token="+BookerAuth.getToken();	
				UpdateJsonData.updateJson("firstname", firstname);
				UpdateJsonData.updateJson("lastname", lastname);
				
				fi=new File("src/test/resources/requestPayloads/PartialUpdate.json");
				
				RestAssured.baseURI=uri;
				response = given()
						.header("Content-Type","application/json")
						.body(fi)
						.header("Cookie",token)
						.when().patch(resource+"/"+bookingId)
						.then().log().body().extract().response();
				
			}catch(Exception e)
			{
				Assert.assertFalse(true, "error in partial update request");
			}
		return response;
		
	}
	
/*
 * Validate response status code as per standard 
* 
*/
	public static void validatePartialBookingRequest()
	{
		if(response.getStatusCode()==HttpStatus.SC_OK)
		{
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}

	}
	
	public static void validateFirstNameInPartialUpdate()
	{

		String actualFirstName = response.jsonPath().get("firstname").toString();	
		Assert.assertEquals(actualFirstName, expectedFirstName);
	}
	
	public static void validateLastNameInPartialUpdate()
	{

		String actualLastName = response.jsonPath().get("lastname").toString();
		Assert.assertEquals(actualLastName, expectedLastName);
	}

}
