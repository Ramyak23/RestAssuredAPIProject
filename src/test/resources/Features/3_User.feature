Feature: User Module

#GET REQUEST (All Users)
Scenario: Check if user able to retrieve all user with valid LMS API
Given User creates GET Request for the LMS API User endpoint
When User sends get all HTTPS Request 
Then User receives 200 OK Status with user details response body.      

#POST REQUEST
Scenario: Check if user able to create a User with valid endpoint and request body (non existing values)
Given User creates POST user Request for the LMS API endpoint
When User sends HTTPS Request and user request Body with mandatory, additional fields
Then User receives 201 Created Status with user response body. 

Scenario: Check if user able to create a User missing mandatory fields in request body
Given User creates POST user Request for the LMS API endpoint
When User sends HTTPS Request and request Body with missing mandatory fields
Then User receives 400 Bad Request Status with message and boolean success user details

#GET REQUEST{by UserID}
Scenario: Check if user able to retrieve a user with valid User ID
Given User creates GET Request for the LMS API User endpoint 
When User sends HTTPS Request with valid UserID
Then User receives 200 OK Status with user details response body.  

Scenario: Check if user able to retrieve a user with invalid User ID
Given User creates GET Request for the LMS API User endpoint 
When User sends HTTPS Request with invalid User ID
Then User receives 404 Not Found Status with message and boolean success user details
 
#GET REQUEST{ ALL Staff} 
Scenario: Check if user able to retrieve a user with valid LMS API
Given User creates GET Request for the LMS API User endpoint 
When User sends HTTPS Request for all staff endpoint 
Then User receives 200 OK Status with user details response body. 

#GET REQUEST{ User with roles}
Scenario: Check if user able to retrieve a user with valid LMS API
Given User creates GET Request for the LMS API User endpoint
When User sends HTTPS Request for user with roles 
Then User receives 200 OK Status with user details response body.

#PUT REQUEST(Update User by User ID)
Scenario: Check if user able to update a user with valid User ID and request body
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and request Body with user mandatory and additional fields 
Then User receives 200 OK Status with user details response body.

Scenario: Check if user able to update a user with valid User ID and missing mandatory fields request body
Given User creates PUT Request for the LMS API user endpoint  
When User sends HTTPS Request  and request Body  (missing user mandatory fields)
Then User receives 400 Bad Request Status with message and boolean success user details

#PUT REQUEST (Update User role status by User ID)
Scenario: Check if user able to update a user with valid User Id and request body
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and request Body to Update User role status by User ID
Then User receives 200 OK Status with user details response body.

#PUT REQUEST(Assign User to Program / Batch by User ID)
Scenario: Check if user able to assign user to program / batch with valid User Id and request body
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and request Body to Assign User to Program/Batch by UserID    
Then User receives 200 OK Status with user details response body.

#DELETE REQUEST(by UserID)
#Scenario: Check if user able to delete a user with valid User Id
#Given User creates DELETE Request for the LMS API endpoint 
#When User sends HTTPS Request to delete a user with valid User Id
#Then User receives 200 OK Status with user details response body.

Scenario: Check if user able to delete a user with valid LMS API,invalid user Id
Given User creates DELETE Request for the LMS API endpoint
When User sends HTTPS Request to delete a user with invalid User Id
Then User receives 404 Not Found Status with message and boolean success user details

#PUT NEGATIVE REQUEST
Scenario: Check if user able to update a user with invalid User Id and request body
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and  request Body with invalid User Id 
Then User receives 404 Not Found Status with message and boolean success user details

Scenario: Check if user able to update a user with invalid User Id and request body
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and  request Body for user role status 
Then User receives 404 Not Found Status with message and boolean success user details

Scenario: Check if user able to update a user with valid User Id and request body (missing field)
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and  request Body with role missing field
Then User receives 400 Bad Request Status with message and boolean success user details

Scenario: Check if user able to  assign user to program / batch with invalid User Id and request body
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and  request Body with program / batch invalid userid
Then User receives 404 Not Found Status with message and boolean success user details

Scenario: Check if user able to  assign user to program / batch with valid User Id and request body (missing field)
Given User creates PUT Request for the LMS API user endpoint
When User sends HTTPS Request and  request Body with program / batch missing fields
Then User receives 400 Bad Request Status with message and boolean success user details
