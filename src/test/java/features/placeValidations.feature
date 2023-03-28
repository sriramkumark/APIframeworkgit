Feature: Validating place API's
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI 
Given Add place Payload with "<name>" "<language>" "<address>"
When User calls "addPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

 

Examples: 
	|name|language|address|
	|sriram|Tamil|tradecenter|
#	|bbname |English|  ANNA Nagar|
#want to execute specify scenario give the tag name like below and add the tag in the testrunner file	
	
@Deleteplace	
Scenario: verify if delete place functionality is working

Given DeletePlace Payload
When User calls "deletePlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
	
	
	
	
	
	