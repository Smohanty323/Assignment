@regression
Feature: API documentation for restful-booker

  Background: Validate application health check
    When User validate ping check of application
    Then User should able to check all other services

  @createBooking
  Scenario: This is to test create booking request in restful-booker
    When User intiate create booking request
    Then User validates create booking request is successful

  @getBookingIDS
  Scenario: This is to test get booking ids request in restful-booker
    When User intiate get booking ids request
    Then User validates get booking ids request is successful

  @getBookingById
  Scenario: This is to test get booking by id request in restful-booker
    Given User get one booking id from all created booking ids
    When User intiate get booking by id request
    Then User validates get booking by id request is successful

  @partialUpdate
  Scenario Outline: This is to test partial update booking request in restful-booker
    Given User get one booking id from all created booking ids
    When User intiate partial booking request with <firstname> and <lastname>
    Then User validates partial booking request is successful
    Then User validates firstname in response
    Then User validates lastname in response

    Examples: 
      | firstname | lastname  |
      | Sachin    | Tendulkar |

  @deleteBooking
  Scenario: This is to test delete booking request in restful-booker
    Given User get one booking id from all created booking ids
    When User intiate delete booking request
    Then User validates delete booking request is successful
