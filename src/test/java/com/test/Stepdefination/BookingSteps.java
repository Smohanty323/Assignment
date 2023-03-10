package com.test.Stepdefination;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.test.Bookings.CreateBooking;
import com.test.Bookings.DeleteBooking;
import com.test.Bookings.GetBooking;
import com.test.Bookings.PartialUpdateBooking;
import com.test.Helper.BookingIdsHelper;
import com.test.SetUp.BookerHealthCheck;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BookingSteps 
{
	static int bookingId =0;
	
	@When("User validate ping check of application")
	public void user_validate_ping_check_of_application() throws FileNotFoundException, IOException {
		BookerHealthCheck.healthCheck();
	}

	@Then("User should able to check all other services")
	public void user_should_able_to_check_all_other_services() {
		BookerHealthCheck.validateHealthCheck();
	}

	@When("User intiate create booking request")
	public void user_intiate_create_booking_request() throws FileNotFoundException, IOException {
		CreateBooking.createBookingRequest();
	}

	@Then("User validates create booking request is successful")
	public void user_validates_create_booking_request_is_successful() {
		CreateBooking.validateCreateBookingResponse(); 
	}

	@When("User intiate get booking ids request")
	public void user_intiate_get_booking_ids_request() {
		GetBooking.getBookingIDSList();
	}

	@Then("User validates get booking ids request is successful")
	public void user_validates_get_booking_ids_request_is_successful() {
		GetBooking.validateGetBookingIDs();
	}

	@Given("User get one booking id from all created booking ids")
	public void user_get_one_booking_id_from_all_created_booking_ids() throws FileNotFoundException, IOException {
		bookingId = BookingIdsHelper.getExistingId();
		if(bookingId==0)
		{
			Response responseid = CreateBooking.createBookingRequest();
			bookingId=responseid.jsonPath().get("bookingid");
		}
	}

	@When("User intiate get booking by id request")
	public void user_intiate_get_booking_by_id_request() throws FileNotFoundException, IOException {
		GetBooking.getBookingDetailsById(bookingId);
	}

	@Then("User validates get booking by id request is successful")
	public void user_validates_get_booking_by_id_request_is_successful() {
		GetBooking.validateGetBookingDetails();
	}

	@When("^User intiate partial booking request with (.+) and (.+)$")
	public void user_intiate_partial_booking_request(String firstname,String lastname) throws FileNotFoundException, IOException {
		PartialUpdateBooking.partialUpdateBookingRequest(firstname, lastname,bookingId);
	}

	@Then("User validates partial booking request is successful")
	public void user_validates_partial_booking_request_is_successful() {
		PartialUpdateBooking.validatePartialBookingRequest();
	}
	
	@Then("User validates firstname in response")
	public void user_validates_firstname_in_response() {
		PartialUpdateBooking.validateFirstNameInPartialUpdate();
	}

	@Then("User validates lastname in response")
	public void user_validates_lastname_in_response() {
		PartialUpdateBooking.validateLastNameInPartialUpdate();
	}


	@When("User intiate delete booking request")
	public void user_intiate_delete_booking_request() throws FileNotFoundException, IOException {
		DeleteBooking.deleteBooking(bookingId);
	}

	@Then("User validates delete booking request is successful")
	public void user_validates_delete_booking_request_is_successful() {
		DeleteBooking.validateDeleteBookingResponse();
	}
	
	

}
