Feature: program module

#GET ALL PROGRAMS
Scenario: Check if user able to retrieve all programs with valid LMS API
Given User creates GET Request for the LMS API endpoint
When User sends HTTPS Request 
Then User receives 200 OK Status with response body.

#POST PROGRAMS
Scenario: Check if user able to create a program with valid endpoint and request body (non existing values)
Given User creates POST Request for the LMS API endpoint
When  User sends HTTPS Request and request Body with mandatory, additional fields.         
Then  User receives 201 Created Status with response body.

#GET REQUEST BY PROGRAMID
Scenario: Check if user able to retrieve a program with valid program ID and LMS API
Given User creates GET Request for the LMS API endpoint
When User sends HTTPS Request with valid program ID
Then User receives 200 OK Status with response body.  

#PUT REQUEST (Update program by ID) 
Scenario: Check if user able to update a program with valid programID and mandatory request body
Given User creates PUT Request for the LMS API endpoint and Valid programID
When User sends HTTPS Request and request Body with updated fields.
Then User receives Status OK with updated value in response body.                        

#PUT REQUEST (Using program name)
Scenario: Check if user able to update a program with Valid program Name and request body
Given User creates PUT Request for the LMS API endpoint and Valid program Name
When User sends HTTPS Request and request Body with mandatory fields.
Then User receives 200OK Status with updated value in response body.  

#DELETE REQUEST (by program ID)
#Scenario: Check if user able to delete a program with valid program ID
#Given User creates DELETE Request for the LMS API endpoint  and  valid program ID
#When User sends delete HTTPS Request
#Then User receives 200 Ok status with message

#NEGATIVE SCENARIO
#GET REQUEST (program ID)
Scenario: Check if user able to retrieve a program with invalid program ID and LMS API
Given User creates GET Request for the LMS API endpoint
When User sends HTTPS Request with invalid program ID
Then User receives 404 Not Found Status with message and boolean success details for program

#DELETE REQUEST (by program ID)
Scenario: Check if user able to delete a program with valid LMS API,invalid program ID
Given User creates DELETE Request for the LMS API endpoint  and  invalid program ID
When User sends HTTPS delete program Request
Then User receives 404 Not Found Status with message and boolean success details for program

#DELETE REQUEST  (by program Name)
Scenario: Check if user able to delete a program with valid LMS API,invalid programName
Given User creates DELETE Request for the LMS API endpoint with invalid programName
When User sends HTTPS delete Request with invalid programName
Then User receives 404 Not Found Status with message and boolean success details for program

Scenario: Check if user able to create a program with valid endpoint and request body (existing values in Program Name)
Given User creates POST Request for the LMS API endpoint
When User sends HTTPS Request and  request Body with existing values
Then User receives 400 Bad Request Status with message and boolean success details for program

Scenario: Check if user able to create a program missing mandatory fields in request body
Given User creates POST Request for the LMS API endpoint
When User sends HTTPS Request and  request Body with missing program mandatory fields.  
Then User receives 400 Bad Request Status with message and boolean success details for program

Scenario: Check if user able to update a program with invalid programID and mandatory request body
Given User creates PUT Request for the LMS API endpoint and Valid programID
When User sends HTTPS Request  and  request Body with invalid programID
Then User receives 404 Not Found Status with message and boolean success details for program

Scenario: Check if user able to update a program  missing mandatory fields in request body
Given User creates PUT Request for the LMS API endpoint and Valid programID
When User sends HTTPS Request  and request Body  (missing mandatory fields)
Then User receives 400 Bad Request Status with message and boolean success details for program

Scenario: Check if user able to update a program with  invalid program Name and request body
Given User creates PUT Request for the LMS API endpoint and Valid program Name
When User sends HTTPS Request  and  request Body with invalid programname
Then User receives 404 Not Found Status with message and boolean success details for program

Scenario: Check if user able to update a program using valid program name - missing mandatory fields in request body 
Given User creates PUT Request for the LMS API endpoint and Valid program Name
When User sends HTTPS program Request  and request Body with missing mandatory fields
Then User receives 400 Bad Request Status with message and boolean success details for program

