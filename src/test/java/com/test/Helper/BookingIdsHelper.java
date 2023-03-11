package com.test.Helper;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

import com.test.Bookings.GetBookingIDs;


public class BookingIdsHelper {

/*
 * here trying to get one booking id from all booking ids list
 */
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

/*
 * getting the list of booking and returning list of ids as a helper function     
 */
    public static List<Integer> getBookingIds() {
        Response getBookingIdsResponse = GetBookingIDs.getBookingIDSList();
        JsonPath jsonPath = getBookingIdsResponse.jsonPath();
        return jsonPath.getList("bookingid");
    }
}
