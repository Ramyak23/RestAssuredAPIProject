Feature: Submit Module

#POST REQUEST
Scenario: Check if user able to  create a submission  with valid endpoint and request body (non existing values)
Given User creates POST Request for the LMS API submit endpoint 
When User sends submit HTTPS Request with non existing values
Then User receives 201 Created Status with success submit response body.          

Scenario: Check if user able to create a submission with valid endpoint and request body (existing value)
Given User creates POST Request for the LMS API submit endpoint
When User sends submit HTTPS Request and request Body with existing value
Then User receives 400 Bad Request Status with message and boolean success details for submit

Scenario: Check if user able to create a submission missing mandatory fields in request body
Given User creates POST Request for the LMS API submit endpoint
When User sends HTTPS Request and request Body with missing mandatory submit fields
Then User receives 400 Bad Request Status with message and boolean success details for submit

#GET REQUEST(All Submissions)
Scenario: Check if user able to retrieve all submission with valid LMS API endpoint
Given User creates GET Request for the LMS API submit endpoint
When User sends submit HTTPS Request 
Then User receives 200 OK Status with response body for submission.        

#GET REQUEST{get Grades by Assignment ID }
Scenario: Check if user able to retrieve a grades with valid Assignment ID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request with valid Assignmentid
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to retrieve a grades with invalid Assignment ID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request with invalid Assignmentid
Then User receives 404 Not Found Status with message and boolean success details for submission

#GET REQUEST{get Grades by StudentID }
Scenario: Check if user able to retrieve a grades with valid Student ID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request with valid Student ID
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to retrieve a grades with invalid Student ID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request  with invalid Student ID
Then User receives 404 Not Found Status with message and boolean success details for submission

#GET REQUEST{get Grades by BatchID }
Scenario: Check if user able to retrieve a grades with valid  batchID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request to retrieve a grades with valid batchID
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to retrieve a grades with invalid BatchID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request to retrieve a grades with invalid batchID
Then User receives 404 Not Found Status with message and boolean success details for submission

#GET REQUEST{get Submission by BatchID }
Scenario: Check if user able to retrieve a submission with valid batchID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request to retrieve a submission with valid batchID
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to retrieve a submission with invalid Batch Id
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request to retrieve a submission with invalid batchID
Then User receives 404 Not Found Status with message and boolean success details for submission

#GET REQUEST{get Submission by UserID }
Scenario: Check if user able to retrieve a submission with valid UserID
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request to retrieve a submission with valid UserID
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to retrieve a submission with invalid UserId
Given User creates GET Request for the LMS API submit endpoint
When User sends HTTPS Request to retrieve a submission with invalid UserID
Then User receives 404 Not Found Status with message and boolean success details for submission

#PUT REQUEST(Resubmit assisgnment by submission ID)
Scenario: Check if user able to update a submission with valid submission ID and mandatory request body
Given User creates PUT Request for the LMS API submit endpoint  
When User sends update HTTPS Request and request Body with Valid submission Id 
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to update a submission with invalid submission ID and mandatory request body
Given User creates PUT Request for the LMS API submit endpoint  
When User sends update HTTPS Request and request Body with invalid submission Id
Then User receives 404 Not Found Status with message and boolean success details for submission

Scenario: Check if user able to update a submission with valid submission ID and missing mandatory fields request body
Given User creates PUT Request for the LMS API submit endpoint  
When User sends update HTTPS Request with missing mandatory fields to update a submission with valid submission ID
Then User receives 400 Bad Request Status with message and boolean success details for submit

#PUT REQUEST(Grade assignment by Submission ID)
Scenario: Check if user able to  grade assignment with valid submission  Id and mandatory request body
Given User creates PUT Request for the LMS API submit endpoint  
When User sends update HTTPS Request to grade assignment with valid submissionid 
Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to grade assignment with invalid submission  Id and mandatory request body
Given User creates PUT Request for the LMS API submit endpoint  
When User sends update HTTPS Request to grade assignment with invalid submissionid 
Then User receives 404 Not Found Status with message and boolean success details for submission

Scenario: Check if user able to grade assignment with valid submission Id and missing mandatory request body
Given User creates PUT Request for the LMS API submit endpoint  
When User sends update HTTPS Request with missing mandatory fields to grade assignment with valid submissionid 
Then User receives 400 Bad Request Status with message and boolean success details for submit

#DELETE REQUEST(by submissionID)
#Scenario: Check if user able to delete a submission with valid submission Id
#Given User creates DELETE Request for the LMS API submit endpoint    
#When User sends HTTPS Request with valid submission ID
#Then User receives 200 OK Status with response body for submission.

Scenario: Check if user able to delete a submission with valid LMS API,invalid Submission Id
Given User creates DELETE Request for the LMS API submit endpoint   
When User sends HTTPS Request with invalid submission id
Then User receives 404 Not Found Status with message and boolean success details for submission