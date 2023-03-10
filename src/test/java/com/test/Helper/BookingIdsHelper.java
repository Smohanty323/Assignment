package com.test.Helper;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

import com.test.Bookings.GetBooking;


public class BookingIdsHelper {

    public static int getExistingId() {
    	try {
	        List<Integer> bookingIds = getBookingIds();
	        Collections.shuffle(bookingIds);
	        return bookingIds.get(0);
    	}catch(IndexOutOfBoundsException i)
    	{
    		i.printStackTrace();
    		return 0;
    	}
    }

    public static List<Integer> getBookingIds() {
        Response getBookingIdsResponse = GetBooking.getBookingIDSList();
        JsonPath jsonPath = getBookingIdsResponse.jsonPath();
        return jsonPath.getList("bookingid");
    }
}
