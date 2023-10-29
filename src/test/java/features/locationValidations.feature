Feature: Validate Location APIs
  Scenario: Verify that the user is able to add a location
    Given User has Add Location payload
    When User calls "AddPlaceAPI" with Post http request
    Then User should see success status code 200
    And "status" is "OK"