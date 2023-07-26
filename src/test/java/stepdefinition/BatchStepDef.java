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

public class BatchStepDef {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	JSONObject jsonobject=new JSONObject();
	public static Integer programID=0;
	public static String bName=null;
	public static Integer bID=0;
	public static String prgmName=null;
	
	@Given("User creates POST batch Request for the LMS API endpoint")
	public void user_creates_post_batch_request_for_the_lms_api_endpoint() {
				request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}
	@When("User sends HTTPS Request and  request Body with mandatory, additional fields")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() {
		LoggerLoad.info("*****Validating Batch Module*****");
		JSONObject requestBody = new JSONObject();
        requestBody.put("batchDescription", "Tableau");
        requestBody.put("batchName", "July23-APIDiggers-DA-DA55-001");
        requestBody.put("batchNoOfClasses", "5");
        requestBody.put("batchStatus", "Active");
        requestBody.put("programId", ProgramStepDef.programID);
        // Send the POST request with the constructed request body
        response = request.body(requestBody.toJSONString()).post(Routes.SAVEENDPOINT);
	}
	@Then("User receives 201Created Status with response body.")
	public void user_receives_201created_status_with_response_body() {
		//assertEquals(response.getStatusCode(), 201);
		response.then()
		 .assertThat().statusCode(201)
		 .body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\Shoban\\eclipse-workspace\\RestAssuredAPITesting\\src\\test\\resources\\postbatchschema.json")));	
		LoggerLoad.info("Batch Created");
        // Optionally, you can also retrieve and store the programID from the response body for further testing
        JsonPath jsonPath = response.jsonPath();
        programID = jsonPath.getInt("programId");
        bName=jsonPath.getString("batchName");
        bID=jsonPath.getInt("batchId");
        prgmName=jsonPath.getString("programName");
        System.out.println("programid: "+programID);
        System.out.println("batchid: "+bID);
        System.out.println("batchname: "+bName);
        System.out.println("programname: "+prgmName);
        
	}
	

	@When("User sends HTTPS Request and request Body \\(missing mandatory fields)")
	public void user_sends_https_request_and_request_body_missing_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("batchDescription", "Tableau");
        //requestBody.put("batchName", "July23-APIDiggers-DA-DA55-001");
        requestBody.put("batchNoOfClasses", "5");
        requestBody.put("batchStatus", "Active");
        requestBody.put("programId", "11900");
        // Send the POST request with the constructed request body
        response = request.body(requestBody.toJSONString()).post(Routes.SAVEENDPOINT);
	}
   
	@Then("User receives {int} Bad Request Status with message and boolean success details")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer statuscode) {
		assertEquals(400,response.getStatusCode());
		System.out.println("400 Bad Request");
	}
	
	@Given("User creates GET Request for the LMS API with batch endpoint")
	public void user_creates_get_request_for_the_lms_api_with_batch_endpoint() {
		request = given();
	} 
	
	@When("User send HTTPS Request")
	public void user_send_https_request() {
		response = request.when().get(Routes.BASE_URI+Routes.SAVEENDPOINT);
		System.out.println("response: " + response.prettyPrint());
	}
   
	@Then("User receives {int} OK Status with batch response body.")
	public void user_receives_ok_status_with_batch_response_body(int statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@When("User sends HTTPS Request with valid Batch ID")
	public void user_sends_https_request_with_valid_batch_id() {
		response = request.when().get(Routes.GETBATCHBYID+bID);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with invalid Batch ID")
	public void user_sends_https_request_with_invalid_batch_id() {
		response = request.when().get(Routes.GETBATCHBYID+25);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} Not Found Status with message and boolean success details")
	public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer statuscode) {
		assertEquals(404,response.getStatusCode());
		//assertEquals("404 Not Found",response.getStatusLine());
		System.out.println("400 Not Found");
	}

	@When("User sends HTTPS Request with valid Batch name")
	public void user_sends_https_request_with_valid_batch_name() {
		response = request.when().get(Routes.GETBATCHBYNAME+bName);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with invalid Batch name")
	public void user_sends_https_request_with_invalid_batch_name() {
		response = request.when().get(Routes.GETBATCHBYNAME+"APIDiggers");
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with valid Program Id")
	public void user_sends_https_request_with_valid_program_id() {
		response = request.when().get(Routes.GETBATCHBYPRGMID+programID);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with invalid Program Id")
	public void user_sends_https_request_with_invalid_program_id() {
		response = request.when().get(Routes.GETBATCHBYPRGMID+265);
		System.out.println("response: " + response.prettyPrint());
	}

	@Given("User creates PUT Request for the LMS API endpoint and Valid batch Id")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_batch_id() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS batch Request and request Body with mandatory, additional  fields")
	public void user_sends_https_batch_request_and_request_body_with_mandatory_additional_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("batchDescription", "Tableau");
        requestBody.put("batchId", bID);
        requestBody.put("batchName", "July23-APIDiggers-DA-DA55-001");
        requestBody.put("batchNoOfClasses", "15");
        requestBody.put("batchStatus", "Active");
        requestBody.put("programId", programID);
        requestBody.put("programName", prgmName);
        // Send the PuT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put(Routes.PUTBATCH+bID);
        System.out.println("response: " + response.prettyPrint());
        LoggerLoad.info("Batch Updated Successfully");
	}

	@Then("User receives {int} OK Status with updated value in response body.")
	public void user_receives_ok_status_with_updated_value_in_response_body(Integer statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("User creates PUT Request for the LMS API endpoint  and  invalid batch ID")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_invalid_batch_id() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends batch HTTPS Request and request Body with mandatory, additional  fields")
	public void user_sends_batch_https_request_and_request_body_with_mandatory_additional_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("batchDescription", "Tableau");
        requestBody.put("batchId", bID);
        requestBody.put("batchName", "July23-APIDiggers-DA-DA55-001");
        requestBody.put("batchNoOfClasses", "15");
        requestBody.put("batchStatus", "Active");
        requestBody.put("programId", "11900");
        requestBody.put("programName", prgmName);
        // Send the PuT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put(Routes.PUTBATCH+25);
        System.out.println("response: " + response.prettyPrint());
	}

	/*@Given("User creates DELETE Request for the LMS API endpoint with valid batchid")
	public void user_creates_delete_request_for_the_lms_api_endpoint_with_valid_batchid() {
		request = given();
	}

	@When("User sends delete batch HTTPS Request")
	public void user_sends_delete_batch_https_request() {
		response = request.when().delete(Routes.DELETEBATCH+bID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("Batch Deleted");
	}*/

	@Given("User creates DELETE Request for the LMS API endpoint with invalid batchid")
	public void user_creates_delete_request_for_the_lms_api_endpoint_with_invalid_batchid() {
		request = given();
	}
	@When("User sends delete batch HTTPS Request with invalid batchid")
	public void user_sends_delete_batch_https_request_with_invalid_batchid() {
		response = request.when().delete(Routes.DELETEBATCH+23);
		System.out.println("response: " + response.prettyPrint());
	}
}
