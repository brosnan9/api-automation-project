Feature: Validate Location APIs
  Scenario Outline: Verify that the user is able to add a location
    Given User has Add Location payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then User should see success status code 200
    And "status" is "OK"
    And verify that place_id has value same as "<name>" using "GetPlaceAPI"

    Examples:

    |name               |  address   |language|
    |World Trade Centre |3T, Floor 5 |Hindi   |
#    |Anchol Trade Centre|4T, Floor 6 |Dutch   |