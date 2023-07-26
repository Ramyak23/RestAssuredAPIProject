package stepdefinition;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utilities.LoggerLoad;
import static io.restassured.RestAssured.*;

public class DeleteStepDef {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	JSONObject jsonobject=new JSONObject();
	
	@Given("User creates DELETE Request for the LMS API delete submit endpoint")
	public void user_creates_delete_request_for_the_lms_api_delete_submit_endpoint() {
		request=given();
	}

	@When("User sends HTTPS Request with valid submission ID")
	public void user_sends_https_request_with_valid_submission_id() {
		System.out.println("submissionid: "+SubmitStepDef.submitID);
		response = request.when().delete(Routes.DELETESUBMIT+SubmitStepDef.submitID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("AssignmentSubmission Deleted Successfully");
				
	}
	
	@Then("User receives {int} OK Status with response body for delete submission.")
	public void user_receives_ok_status_with_response_body_for_delete_submission(int statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}
	
		
	@Given("User creates DELETE Request for the LMS API valid assignment endpoint")
	public void user_creates_delete_request_for_the_lms_api_valid_assignment_endpoint() {
	   request=given();
	}

	@When("User sends delete HTTPS Request with  valid Assignment Id")
	public void user_sends_delete_https_request_with_valid_assignment_id() {
		response = request.when().delete(Routes.DELETEASSIGNMENT+AssignmentStepDef.assignID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("Assignment Deleted Successfully");
	}
	
	@Then("User receives {int} OK Status with assignment delete response body.")
	public void user_receives_ok_status_with_assignment_delete_response_body(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Given("User creates user DELETE Request for the LMS API endpoint")
	public void user_creates_user_delete_request_for_the_lms_api_endpoint() {
		request = given();
	}

	@When("User sends HTTPS Request to delete a user with valid User Id")
	public void user_sends_https_request_to_delete_a_user_with_valid_user_id() {
		response = request.when().delete(Routes.DELETEUSER+UserStepDef.userID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("User Deleted");
	}
	
	@Then("User receives {int} OK Status with user delete details response body.")
	public void user_receives_ok_status_with_user_delete_details_response_body(int statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Given("User creates DELETE Request for the LMS API endpoint with valid batchid")
	public void user_creates_delete_request_for_the_lms_api_endpoint_with_valid_batchid() {
		request = given();
	}

	@When("User sends delete batch HTTPS Request")
	public void user_sends_delete_batch_https_request() {
		response = request.when().delete(Routes.DELETEBATCH+BatchStepDef.bID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("Batch Deleted");
	}
	@Then("User receives {int} OK Status with batch response body for deletion.")
	public void user_receives_ok_status_with_batch_response_body_for_deletion(int statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Given("User creates DELETE Request for the LMS API endpoint  and  valid program ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id() {
		request = given();
	}

	@When("User sends delete HTTPS Request")
	public void user_sends_delete_https_request() {
		response = request.when().delete(ProgramStepDef.BASE_URI+ProgramStepDef.DELETEPRGMID+ProgramStepDef.programID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("Program Deleted");
	}

	@Then("User receives {int} Ok status with message")
	public void user_receives_ok_status_with_message(int statuscode) {
		int deletestatuscode = response.getStatusCode();

		if (deletestatuscode == 200) {

				System.out.println("Delete Request Successful");
		LoggerLoad.info("Delete Request Successful");

                           }
	}
}
