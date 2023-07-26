Feature: Delete

#DELETE REQUEST(by submissionID)
Scenario: Check if user able to delete a submission with valid submission Id
Given User creates DELETE Request for the LMS API delete submit endpoint    
When User sends HTTPS Request with valid submission ID
Then User receives 200 OK Status with response body for delete submission.

#DELETE REQUEST(by assignmentID)
Scenario:Check if user able to delete a record with valid Assignment ID
Given User creates DELETE Request for the LMS API valid assignment endpoint   
When User sends delete HTTPS Request with  valid Assignment Id
Then User receives 200 OK Status with assignment delete response body.

#DELETE REQUEST(by UserID)
Scenario: Check if user able to delete a user with valid User Id
Given User creates user DELETE Request for the LMS API endpoint 
When User sends HTTPS Request to delete a user with valid User Id
Then User receives 200 OK Status with user delete details response body.

#DELETE REQUEST(by Batch ID)
Scenario: Check if user able to delete a Batch with valid batchID
Given User creates DELETE Request for the LMS API endpoint with valid batchid
When User sends delete batch HTTPS Request
Then User receives 200 OK Status with batch response body for deletion.

#DELETE REQUEST (by program ID)
Scenario: Check if user able to delete a program with valid program ID
Given User creates DELETE Request for the LMS API endpoint  and  valid program ID
When User sends delete HTTPS Request
Then User receives 200 Ok status with message