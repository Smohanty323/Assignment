package com.test.SetUp;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.HttpStatus;

import com.test.Helper.ReadPropertyFiles;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookerAuth 
{
/*
 * used rest assured methods to get a valid token which will be used for some of the api test cases	
 */
	public static String getToken() throws FileNotFoundException, IOException {
		
		String uri = ReadPropertyFiles.getProperty("ApplicationConfig", "baseURI");
		String auth_resource = ReadPropertyFiles.getProperty("ApplicationConfig", "auth_resource");
		
		RestAssured.baseURI=uri;
		
		RequestSpecification request = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody("{ \"username\" : \"admin\", \"password\" : \"password123\"}")
                .build();
	
		Response response = given(request)
						   .when().post(auth_resource)
						   .then().statusCode(HttpStatus.SC_OK)
						   .and().log().body().extract().response();
		
		return response.jsonPath().get("token");
    }

}
