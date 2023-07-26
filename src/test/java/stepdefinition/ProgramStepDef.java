package stepdefinition;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.LoggerLoad;

import static io.restassured.RestAssured.*;
import io.restassured.response.ValidatableResponse;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import org.json.simple.JSONObject;

public class ProgramStepDef {
 
	public static String BASE_URI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	public static String GETALLENDPOINT="/allPrograms";
	public static String SAVEENDPOINT="/saveprogram";
	public static String GETONEPRGMID="/programs/";
	public static String DELETEPRGMID="/deletebyprogid/";
		private Response response;
		private ValidatableResponse json;
		private RequestSpecification request;
		JSONObject jsonobject=new JSONObject();
		public static Integer programID=0;
		public static String prgmName=null;
		 
	@Given("User creates GET Request for the LMS API endpoint")
	public void user_creates_get_request_for_the_lms_api_endpoint() {
				request = given();
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		LoggerLoad.info("*****Validating Program Module*****");
		response = request.when().get(BASE_URI+GETALLENDPOINT);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(int statusCode) {
		//json = response.then().statusCode(statusCode).log().all();
		assertEquals(statusCode,response.getStatusCode());
			}

	@Given("User creates POST Request for the LMS API endpoint")
	public void user_creates_post_request_for_the_lms_api_endpoint() {
		
		request = RestAssured.given().baseUri(BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and request Body with mandatory, additional fields.")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() {
		// Construct the request body JSON
        JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Selenium Automation Testing");
        requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the POST request with the constructed request body
        response = request.body(requestBody.toJSONString()).post(SAVEENDPOINT);
		 
	}

	@Then("User receives {int} Created Status with response body.")
	public void user_receives_created_status_with_response_body(int expectedStatusCode) {
		// Assert the response status code
        //assertEquals(response.getStatusCode(), expectedStatusCode);
		response.then()
		 .assertThat().statusCode(201)
		 .body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\Shoban\\eclipse-workspace\\RestAssuredAPITesting\\src\\test\\resources\\postprogramschema.json")));	
        LoggerLoad.info("New Program Created successfully");
        // Optionally, you can also retrieve and store the programID from the response body for further testing
        JsonPath jsonPath = response.jsonPath();
        programID = jsonPath.getInt("programId");
        System.out.println(programID);
        prgmName = jsonPath.getString("programName");
        System.out.println(prgmName);
}
  
	@When("User sends HTTPS Request with valid program ID")
	public void user_sends_https_request_with_valid_program_id() {
		response = request.when().get(BASE_URI+GETONEPRGMID+programID);
		System.out.println("response: " + response.prettyPrint());
	}
	@Given("User creates PUT Request for the LMS API endpoint and Valid programID")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_id() {
		request = RestAssured.given().baseUri(BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and request Body with updated fields.")
	public void user_sends_https_request_and_request_body_with_updated_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Web Automation");
        requestBody.put("programId", programID);
        requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the PUT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put("/putprogram/"+programID);
        System.out.println("response: " + response.prettyPrint());
        LoggerLoad.info("Program Updated");
	}

	@Then("User receives Status OK with updated value in response body.")
	public void user_receives_status_ok_with_updated_value_in_response_body() {
		assertEquals(response.getStatusCode(), 200);
	}
	@Given("User creates PUT Request for the LMS API endpoint and Valid program Name")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name() {
		request = RestAssured.given().baseUri(BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and request Body with mandatory fields.")
	public void user_sends_https_request_and_request_body_with_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Selenium Automation");
        //requestBody.put("programId", programID);
        requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the PUT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put("/program/"+prgmName);
        System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives 200OK Status with updated value in response body.")
	public void user_receives_200ok_status_with_updated_value_in_response_body() {
		int putstatuscode = response.getStatusCode();

		if (putstatuscode == 200) {

			System.out.println("Put Request Successful");

                           }
	}
   
	/*@Given("User creates DELETE Request for the LMS API endpoint  and  valid program ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id() {
		request = given();
	}

	@When("User sends delete HTTPS Request")
	public void user_sends_delete_https_request() {
		response = request.when().delete(BASE_URI+DELETEPRGMID+programID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("Program Deleted");
	}

	@Then("User receives {int} Ok status with message")
	public void user_receives_ok_status_with_message(int statuscode) {
		int deletestatuscode = response.getStatusCode();

		if (deletestatuscode == 200) {

				System.out.println("Delete Request Successful");
		//logger.info("Delete Request Successful");

                           }
	}*/
	@When("User sends HTTPS Request with invalid program ID")
	public void user_sends_https_request_with_invalid_program_id() {
		response = request.when().get(BASE_URI+GETONEPRGMID+26);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for program")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_program(Integer statuscode) {
		assertEquals(404,response.getStatusCode());
		System.out.println("404 Not Found");
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid program ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_program_id() {
		request = given();
	}

	@When("User sends HTTPS delete program Request")
	public void user_sends_https_delete_program_request() {
		response = request.when().delete(BASE_URI+DELETEPRGMID+12);
		System.out.println("response: " + response.prettyPrint());
	}

	@Given("User creates DELETE Request for the LMS API endpoint with invalid programName")
	public void user_creates_delete_request_for_the_lms_api_endpoint_with_invalid_program_name() {
		request = given();
	}

	@When("User sends HTTPS delete Request with invalid programName")
	public void user_sends_https_delete_request_with_invalid_program_name() {
		response = request.when().delete(BASE_URI+DELETEPRGMID+"SDET");
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request and  request Body with existing values")
	public void user_sends_https_request_and_request_body_with_existing_values() {
		// Construct the request body JSON
        JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Selenium");
        requestBody.put("programName", "July23_API_test");
        requestBody.put("programStatus", "InProgress");

        // Send the POST request with the constructed request body
        response = request.body(requestBody.toJSONString()).post(SAVEENDPOINT);
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details for program")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_program(int statuscode) {
		assertEquals(400,response.getStatusCode());
		System.out.println("400 Bad Request");
	}

	@When("User sends HTTPS Request and  request Body with missing program mandatory fields.")
	public void user_sends_https_request_and_request_body_with_missing_program_mandatory_fields() {
		// Construct the request body JSON
        JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Selenium Automation Testing");
        //requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the POST request with the constructed request body
        response = request.body(requestBody.toJSONString()).post(SAVEENDPOINT);
	}

	@When("User sends HTTPS Request  and  request Body with invalid programID")
	public void user_sends_https_request_and_request_body_with_invalid_program_id() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Web Automation");
        requestBody.put("programId", "62");
        requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the PUT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put("/putprogram/"+62);
        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request  and request Body  \\(missing mandatory fields)")
	public void user_sends_https_request_and_request_body_missing_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Web Automation");
        requestBody.put("programId", programID);
        //requestBody.put("programName", "July23-APIDiggers-SDET-001");
        //requestBody.put("programStatus", "Active");

        // Send the PUT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put("/putprogram/"+programID);
        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request  and  request Body with invalid programname")
	public void user_sends_https_request_and_request_body_with_invalid_programname() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Selenium Automation");
        //requestBody.put("programId", programID);
        requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the PUT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put("/program/"+"APITest");
        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS program Request  and request Body with missing mandatory fields")
	public void user_sends_https_program_request_and_request_body_with_missing_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("programDescription", "Selenium Automation");
        //requestBody.put("programId", programID);
        //requestBody.put("programName", "July23-APIDiggers-SDET-001");
        requestBody.put("programStatus", "Active");

        // Send the PUT request with the constructed request body
        response = request.body(requestBody.toJSONString()).put("/program/"+prgmName);
        System.out.println("response: " + response.prettyPrint());
	}

}