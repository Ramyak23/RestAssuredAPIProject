package stepdefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utilities.LoggerLoad;

public class SubmitStepDef{

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	JSONObject jsonobject=new JSONObject();
	public static Integer submitID=0;
	
	
	@Given("User creates POST Request for the LMS API submit endpoint")
	public void user_creates_post_request_for_the_lms_api_submit_endpoint() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
			}

	@When("User sends submit HTTPS Request with non existing values")
	public void user_sends_submit_https_request_with_non_existing_values() {
		LoggerLoad.info("*****Validating AssignmentSubmit Module*****");
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId",AssignmentStepDef.assignID);
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","path1");
		 requestBody.put("subPathAttach2","path2");
		 requestBody.put("subPathAttach3","path3");
		 requestBody.put("subPathAttach4","path4");
		 requestBody.put("subPathAttach5","path5");
		 requestBody.put("subDateTime","07-26-2023T20:42:41");
		 requestBody.put("gradedBy","null");
		 requestBody.put("gradedDateTime","null");
		 requestBody.put("grade","-1");
		 response = request.body(requestBody.toJSONString()).post(Routes.POSTSUBMIT);
				 
	}

	@Then("User receives {int} Created Status with success submit response body.")
	public void user_receives_created_status_with_success_submit_response_body(int statuscode) {
	   //assertEquals(201, response.getStatusCode());
	   response.then()
		 .assertThat().statusCode(201)
		 .body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\Shoban\\eclipse-workspace\\RestAssuredAPITesting\\src\\test\\resources\\postsubmitschema.json")));	
	   JsonPath jsonPath = response.jsonPath();
       submitID = jsonPath.getInt("submissionId");
       System.out.println("submissionid: "+submitID);
	   
	}

	@When("User sends submit HTTPS Request and request Body with existing value")
	public void user_sends_submit_https_request_and_request_body_with_existing_value() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId","4321");
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","path1");
		 requestBody.put("subPathAttach2","path2");
		 requestBody.put("subPathAttach3","path3");
		 requestBody.put("subPathAttach4","path4");
		 requestBody.put("subPathAttach5","path5");
		 requestBody.put("subDateTime","07-26-2023 20:42:41");
		 requestBody.put("gradedBy","null");
		 requestBody.put("gradedDateTime","null");
		 requestBody.put("grade","-1");
		 response = request.body(requestBody.toJSONString()).post(Routes.POSTSUBMIT);
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details for submit")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_submit(Integer int1) {
		assertEquals(400,response.getStatusCode());
		System.out.println("400 Bad Request");
	}

	@When("User sends HTTPS Request and request Body with missing mandatory submit fields")
	public void user_sends_https_request_and_request_body_with_missing_mandatory_submit_fields() {
		JSONObject requestBody = new JSONObject();
		 //requestBody.put("assignmentId",AssignmentStepDef.assignID);
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","path1");
		 requestBody.put("subPathAttach2","path2");
		 requestBody.put("subPathAttach3","path3");
		 requestBody.put("subPathAttach4","path4");
		 requestBody.put("subPathAttach5","path5");
		//requestBody.put("subDateTime","07-26-2023 20:42:41");
		 requestBody.put("gradedBy","null");
		 requestBody.put("gradedDateTime","null");
		 requestBody.put("grade","-1");
		 response = request.body(requestBody.toJSONString()).post(Routes.POSTSUBMIT);
	}

	@Given("User creates GET Request for the LMS API submit endpoint")
	public void user_creates_get_request_for_the_lms_api_submit_endpoint() {
		request=given();
	}

	@When("User sends submit HTTPS Request")
	public void user_sends_submit_https_request() {
		response = request.when().get(Routes.GETALLSUBMISSION);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} OK Status with response body for submission.")
	public void user_receives_ok_status_with_response_body_for_submission(int statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}

	@When("User sends HTTPS Request with valid Assignmentid")
	public void user_sends_https_request_with_valid_assignmentid() {
		response = request.when().get(Routes.GETGRADESBYASSIGNID+3915);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with invalid Assignmentid")
	public void user_sends_https_request_with_invalid_assignmentid() {
		response = request.when().get(Routes.GETGRADESBYASSIGNID+25);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for submission")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_submission(int statuscode) {
		assertEquals(404,response.getStatusCode());
		System.out.println("400 Not Found");
	}

	@When("User sends HTTPS Request with valid Student ID")
	public void user_sends_https_request_with_valid_student_id() {
		response = request.when().get(Routes.GETGRADESBYSTUID+"U8628");
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request  with invalid Student ID")
	public void user_sends_https_request_with_invalid_student_id() {
		response = request.when().get(Routes.GETGRADESBYSTUID+"U25");
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request to retrieve a grades with valid batchID")
	public void user_sends_https_request_to_retrieve_a_grades_with_valid_batch_id() {
		response = request.when().get(Routes.GETGRADESBYBATCHID+7007);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request to retrieve a grades with invalid batchID")
	public void user_sends_https_request_to_retrieve_a_grades_with_invalid_batch_id() {
		response = request.when().get(Routes.GETGRADESBYBATCHID+30);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request to retrieve a submission with valid batchID")
	public void user_sends_https_request_to_retrieve_a_submission_with_valid_batch_id() {
		response = request.when().get(Routes.GETSUBBYBATCHID+7007);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request to retrieve a submission with invalid batchID")
	public void user_sends_https_request_to_retrieve_a_submission_with_invalid_batch_id() {
		response = request.when().get(Routes.GETSUBBYBATCHID+30);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request to retrieve a submission with valid UserID")
	public void user_sends_https_request_to_retrieve_a_submission_with_valid_user_id() {
		response = request.when().get(Routes.GETSUBBYUSERID+"U8628");
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request to retrieve a submission with invalid UserID")
	public void user_sends_https_request_to_retrieve_a_submission_with_invalid_user_id() {
		response = request.when().get(Routes.GETSUBBYUSERID+"U231");
		System.out.println("response: " + response.prettyPrint());
	}

	@Given("User creates PUT Request for the LMS API submit endpoint")
	public void user_creates_put_request_for_the_lms_api_submit_endpoint() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends update HTTPS Request and request Body with Valid submission Id")
	public void user_sends_update_https_request_and_request_body_with_valid_submission_id() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId",AssignmentStepDef.assignID);
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","filepath1");
		 requestBody.put("subPathAttach2","filepath2");
		 requestBody.put("subPathAttach3","filepath3");
		 requestBody.put("subPathAttach4","filepath4");
		 requestBody.put("subPathAttach5","filepath5");
		 requestBody.put("subDateTime","07-26-2023T20:42:41");
		 requestBody.put("gradedBy","null");
		 requestBody.put("gradedDateTime","null");
		 requestBody.put("grade","-1");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTRESUBMIT+submitID);
		 System.out.println("response: " + response.prettyPrint());
	        LoggerLoad.info("Assignment Submit Updated Successfully");
	}

	@When("User sends update HTTPS Request and request Body with invalid submission Id")
	public void user_sends_update_https_request_and_request_body_with_invalid_submission_id() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId","4321");
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","filepath1");
		 requestBody.put("subPathAttach2","filepath2");
		 requestBody.put("subPathAttach3","filepath3");
		 requestBody.put("subPathAttach4","filepath4");
		 requestBody.put("subPathAttach5","filepath5");
		 requestBody.put("subDateTime","07-26-2023 20:42:41");
		 requestBody.put("gradedBy","null");
		 requestBody.put("gradedDateTime","null");
		 requestBody.put("grade","-1");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTRESUBMIT+23);
	}

	@When("User sends update HTTPS Request with missing mandatory fields to update a submission with valid submission ID")
	public void user_sends_update_https_request_with_missing_mandatory_fields_to_update_a_submission_with_valid_submission_id() {
		JSONObject requestBody = new JSONObject();
		 //requestBody.put("assignmentId",AssignmentStepDef.assignID);
		 //requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","filepath1");
		 requestBody.put("subPathAttach2","filepath2");
		 requestBody.put("subPathAttach3","filepath3");
		 requestBody.put("subPathAttach4","filepath4");
		 requestBody.put("subPathAttach5","filepath5");
		 //requestBody.put("subDateTime","07-26-2023 20:42:41");
		 requestBody.put("gradedBy","null");
		 requestBody.put("gradedDateTime","null");
		 requestBody.put("grade","-1");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTRESUBMIT+submitID);
	}

	@When("User sends update HTTPS Request to grade assignment with valid submissionid")
	public void user_sends_update_https_request_to_grade_assignment_with_valid_submissionid() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId",AssignmentStepDef.assignID);
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","filepath1");
		 requestBody.put("subPathAttach2","filepath2");
		 requestBody.put("subPathAttach3","filepath3");
		 requestBody.put("subPathAttach4","filepath4");
		 requestBody.put("subPathAttach5","filepath5");
		 requestBody.put("subDateTime","07-26-2023T20:42:41");
		 requestBody.put("gradedBy","U8608");
		 requestBody.put("gradedDateTime","07-26-2023T22:12:00");
		 requestBody.put("grade","2");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTGRADEASSIGN+submitID);
		 System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends update HTTPS Request to grade assignment with invalid submissionid")
	public void user_sends_update_https_request_to_grade_assignment_with_invalid_submissionid() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId","4321");
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","path1");
		 requestBody.put("subPathAttach2","path2");
		 requestBody.put("subPathAttach3","path3");
		 requestBody.put("subPathAttach4","path4");
		 requestBody.put("subPathAttach5","path5");
		 requestBody.put("subDateTime","07-26-2023 20:42:41");
		 requestBody.put("gradedBy","U8608");
		 requestBody.put("gradedDateTime","07-26-2023 22:12:00");
		 requestBody.put("grade","2");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTGRADEASSIGN+40);
	}

	@When("User sends update HTTPS Request with missing mandatory fields to grade assignment with valid submissionid")
	public void user_sends_update_https_request_with_missing_mandatory_fields_to_grade_assignment_with_valid_submissionid() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentId","4321");
		 requestBody.put("userId","U8628");
		 requestBody.put("subDesc","Tableau Project");
		 requestBody.put("subComments","Project Submission");
		 requestBody.put("subPathAttach1","path1");
		 requestBody.put("subPathAttach2","path2");
		 requestBody.put("subPathAttach3","path3");
		 requestBody.put("subPathAttach4","path4");
		 requestBody.put("subPathAttach5","path5");
		 requestBody.put("subDateTime","07-26-2023 20:42:41");
		 //requestBody.put("gradedBy","U8608");
		 requestBody.put("gradedDateTime","07-26-2023 22:12:00");
		 //requestBody.put("grade","2");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTGRADEASSIGN+submitID);
	}

	@Given("User creates DELETE Request for the LMS API submit endpoint")
	public void user_creates_delete_request_for_the_lms_api_submit_endpoint() {
		request=given();
	}

	/*@When("User sends HTTPS Request with valid submission ID")
	public void user_sends_https_request_with_valid_submission_id() {
		System.out.println("submissionid: "+submitID);
		response = request.when().delete(Routes.DELETESUBMIT+submitID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("AssignmentSubmission Deleted Successfully");
		//System.out.println("assignid:"+AssignmentStepDef.assignID);
		
	}*/

	@When("User sends HTTPS Request with invalid submission id")
	public void user_sends_https_request_with_invalid_submission_id() {
		response = request.when().delete(Routes.DELETESUBMIT+1024);
		System.out.println("response: " + response.prettyPrint());
	}


}
