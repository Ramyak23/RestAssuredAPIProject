Feature: Assignment Module

#POST REQUEST
Scenario: Check if user able to add a record with valid endpoint and request body (non existing values)
Given User creates POST Request for the LMS API assignment endpoint
When User sends HTTPS Request and request Body with mandatory,additional fields
#Then User receives 201 Created Status with assignment response body.
Then User check json schema validation for POST request

Scenario: Check if user able to add a record with valid endpoint and request body (existing value)
Given User creates POST Request for the LMS API assignment endpoint
When User sends HTTPS Request and request Body with existing values
Then User receives 400 Bad Request Status with message and boolean success details for assignment

Scenario: Check if user able to add a record missing mandatory fields in request body
Given User creates POST Request for the LMS API assignment endpoint
When User sends HTTPS Request and request Body with assignment missing mandatory fields
Then User receives 400 Bad Request Status with message and boolean success details for assignment

#GET REQUEST 
Scenario: Check if user able to retrieve a record with valid LMS API
Given User creates GET Request for the LMS API assignment endpoint
When User sends HTTPS Request to get all assignment
Then User receives 200 OK Status with assignment response body. 

Scenario: Check if user able to retrieve a record with valid Assignment ID
Given User creates GET Request for the LMS API assignment endpoint 
When User sends HTTPS Request with valid Assignment ID
Then User receives 200 OK Status with assignment response body.

Scenario: Check if user able to retrieve a record with invalid Assignment ID
Given User creates GET Request for the LMS API assignment endpoint 
When User sends HTTPS Request with invalid Assignment ID
Then User receives 404 Not Found Status with message and boolean success details for assignment

Scenario: Check if user able to retrieve a record with valid BATCH ID
Given User creates GET Request for the LMS API assignment endpoint 
When User sends HTTPS Request with valid Batch Id
Then User receives 200 OK Status with assignment response body.   

Scenario: Check if user able to retrieve a record with invalid BATCH ID
Given User creates GET Request for the LMS API assignment endpoint
When User sends HTTPS Request  with invalid Batch Id
Then User receives 404 Not Found Status with message and boolean success details for assignment

#PUT REQUEST
Scenario:Check if user able to update a record with valid AssignmentID and mandatory request body
Given User creates PUT Request for the LMS API assignment endpoint   
When User sends HTTPS Request and request Body with Valid Assignment Id
Then User receives 200 OK Status with assignment response body. 

Scenario:Check if user able to update a record with invalid Assignment ID and mandatory request body
Given User creates PUT Request for the LMS API assignment endpoint 
When User sends HTTPS Request and request Body with invalid Assignment ID 
Then User receives 404 Not Found Status with message and boolean success details for assignment

Scenario:Check if user able to update a record with valid AssignmentID and missing mandatory fields request body
Given User creates PUT Request for the LMS API assignment endpoint 
When User sends HTTPS Request  and request Body with missing mandatory fields for valid id
Then User receives 400 Bad Request Status with message and boolean success details for assignment

#DELETE REQUEST
#Scenario:Check if user able to delete a record with valid Assignment ID
#Given User creates DELETE Request for the LMS API assignment endpoint   
#When User sends delete HTTPS Request with  valid Assignment Id
#Then User receives 200 OK Status with assignment response body.

Scenario:Check if user able to delete a record with invalid LMS API,
Given User creates DELETE Request for the LMS API assignment endpoint  
When User sends delete HTTPS Request with invalid Assignment Id
Then User receives 404 Not Found Status with message and boolean success details for assignment