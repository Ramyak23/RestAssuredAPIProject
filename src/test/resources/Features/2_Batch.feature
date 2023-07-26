Feature: Batch Module

#POST REQUEST
Scenario:  Check if user able to create a Batch with valid endpoint and request body (non existing values)
Given User creates POST batch Request for the LMS API endpoint
When User sends HTTPS Request and  request Body with mandatory, additional fields   
Then User receives 201Created Status with response body.  

Scenario: Check if user able to create a Batch missing mandatory fields in request body
Given User creates POST batch Request for the LMS API endpoint
When User sends HTTPS Request and request Body (missing mandatory fields)
Then User receives 400 Bad Request Status with message and boolean success details

#GET REQUEST (All Batches)
Scenario: Check if user able to retrieve all batches  with valid LMS API
Given User creates GET Request for the LMS API with batch endpoint
When User send HTTPS Request 
Then User receives 200 OK Status with batch response body.       

#GET REQUEST{ by Batch ID }
Scenario: Check if user able to retrieve a batch with valid BATCH ID
Given User creates GET Request for the LMS API with batch endpoint 
When User sends HTTPS Request with valid Batch ID
Then User receives 200 OK Status with batch response body.  

Scenario: Check if user able to retrieve a batch with invalid BATCH ID
Given User creates GET Request for the LMS API with batch endpoint 
When User sends HTTPS Request with invalid Batch ID
Then User receives 404 Not Found Status with message and boolean success details

#GET REQUEST{by Batch Name}
Scenario: Check if user able to retrieve a batch with valid BATCH NAME
Given User creates GET Request for the LMS API with batch endpoint 
When User sends HTTPS Request with valid Batch name
Then User receives 200 OK Status with batch response body. 

Scenario: Check if user able to retrieve a batch with invalid BATCH NAME
Given User creates GET Request for the LMS API with batch endpoint 
When User sends HTTPS Request with invalid Batch name
Then User receives 404 Not Found Status with message and boolean success details

#GET REQUEST{by program Id}
Scenario: Check if user able to retrieve a batch with valid Program ID
Given User creates GET Request for the LMS API with batch endpoint 
When User sends HTTPS Request with valid Program Id
Then User receives 200 OK Status with batch response body. 

Scenario: Check if user able to retrieve a batch with invalid Program Id
Given User creates GET Request for the LMS API with batch endpoint 
When User sends HTTPS Request with invalid Program Id
Then User receives 404 Not Found Status with message and boolean success details

#PUT REQUEST(Update Batch by batchID)
Scenario: Check if user able to update a Batch with valid batchID and mandatory request body
Given User creates PUT Request for the LMS API endpoint and Valid batch Id
When User sends HTTPS batch Request and request Body with mandatory, additional  fields  
Then User receives 200 OK Status with updated value in response body.

Scenario: Check if user able to update a Batch with invalid batchID and mandatory request body
Given User creates PUT Request for the LMS API endpoint  and  invalid batch ID
When User sends batch HTTPS Request and request Body with mandatory, additional  fields  
Then User receives 404 Not Found Status with message and boolean success details

#DELETE REQUEST(by Batch ID)
#Scenario: Check if user able to delete a Batch with valid batchID
#Given User creates DELETE Request for the LMS API endpoint with valid batchid
#When User sends delete batch HTTPS Request
#Then User receives 200 OK Status with batch response body.

Scenario: Check if user able to delete a Batch with valid LMS API,invalid batchID
Given User creates DELETE Request for the LMS API endpoint with invalid batchid 
When User sends delete batch HTTPS Request with invalid batchid
Then User receives 404 Not Found Status with message and boolean success details

