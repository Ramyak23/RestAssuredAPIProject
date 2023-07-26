package stepdefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
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
import java.io.File;

public class AssignmentStepDef {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	JSONObject jsonobject=new JSONObject();
	public static Integer assignID=0;
	public static Integer batchID=7007;
	@Given("User creates POST Request for the LMS API assignment endpoint")
	public void user_creates_post_request_for_the_lms_api_assignment_endpoint() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and request Body with mandatory,additional fields")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() {
		LoggerLoad.info("*****Validating Assignment Module*****");
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentName","Jul23-APIDiggers-DA-LMSTableau-010");
		 requestBody.put("batchId",BatchStepDef.bID);
		 requestBody.put("createdBy","U8608");
		 requestBody.put("graderId","U8608");
		 requestBody.put("dueDate","2023-07-31T19:42:41.680Z");
		 requestBody.put("assignmentDescription","DiabetesProject");
		 requestBody.put("comments","Diabetes");
		 requestBody.put("pathAttachment1","path1");
		 requestBody.put("pathAttachment2","path2");
		 requestBody.put("pathAttachment3","path3");
		 requestBody.put("pathAttachment4","path4");
		 requestBody.put("pathAttachment5","path5");
		 response = request.body(requestBody.toJSONString()).post(Routes.POSTASSIGNMENT);
		 JsonPath jsonPath = response.jsonPath();
	        assignID = jsonPath.getInt("assignmentId");
	        System.out.println("assignmentid: "+assignID);
		 
	}

	/*@Then("User receives {int} Created Status with assignment response body.")
	public void user_receives_created_status_with_assignment_response_body(Integer int1) {
	   
	}*/
     
	@Then("User check json schema validation for POST request")
	public void user_check_json_schema_validation_for_post_request() {
		 		response.then()
		 .assertThat().statusCode(201)
		 .body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\Shoban\\eclipse-workspace\\RestAssuredAPITesting\\src\\test\\resources\\postassignschema.json")));	
		  LoggerLoad.info("Post Request Successful");
		}
	
	@When("User sends HTTPS Request and request Body with existing values")
	public void user_sends_https_request_and_request_body_with_existing_values() {
			JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentName","Jul23-APIDiggers-DA-Tableau-001");
		 //requestBody.put("batchId","7007");
		 requestBody.put("createdBy","U8608");
		 requestBody.put("graderId","U8608");
		 requestBody.put("dueDate","2023-07-31T19:42:41.680Z");
		 requestBody.put("assignmentDescription","Tableau Project");
		 requestBody.put("comments","Diabetes");
		 requestBody.put("pathAttachment1","path1");
		 requestBody.put("pathAttachment2","path2");
		 requestBody.put("pathAttachment3","path3");
		 requestBody.put("pathAttachment4","path4");
		 requestBody.put("pathAttachment5","path5");
		 response = request.body(requestBody.toJSONString()).post(Routes.POSTASSIGNMENT);
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details for assignment")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_assignment(Integer int1) {
		assertEquals(400,response.getStatusCode());
		System.out.println("400 Bad Request");
	}

	@When("User sends HTTPS Request and request Body with assignment missing mandatory fields")
	public void user_sends_https_request_and_request_body_with_assignment_missing_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentName","Jul23-APIDiggers-DA-Tableau-001");
		 //requestBody.put("batchId","7007");
		 requestBody.put("createdBy","U8608");
		 requestBody.put("graderId","U8608");
		// requestBody.put("dueDate","2023-07-31T19:42:41.680Z");
		 requestBody.put("assignmentDescription","Tableau Project");
		 requestBody.put("comments","Diabetes");
		 requestBody.put("pathAttachment1","path1");
		 requestBody.put("pathAttachment2","path2");
		 requestBody.put("pathAttachment3","path3");
		 requestBody.put("pathAttachment4","path4");
		 requestBody.put("pathAttachment5","path5");
		 response = request.body(requestBody.toJSONString()).post(Routes.POSTASSIGNMENT);
	}

	@Given("User creates GET Request for the LMS API assignment endpoint")
	public void user_creates_get_request_for_the_lms_api_assignment_endpoint() {
	   request=given();
	}

	@When("User sends HTTPS Request to get all assignment")
	public void user_sends_https_request_to_get_all_assignment() {
		response = request.when().get(Routes.GETALLASSIGNMENT);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} OK Status with assignment response body.")
	public void user_receives_ok_status_with_assignment_response_body(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@When("User sends HTTPS Request with valid Assignment ID")
	public void user_sends_https_request_with_valid_assignment_id() {
		response = request.when().get(Routes.GETASSIGNMENTBYID+assignID);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with invalid Assignment ID")
	public void user_sends_https_request_with_invalid_assignment_id() {
		response = request.when().get(Routes.GETASSIGNMENTBYID+45);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for assignment")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_assignment(Integer int1) {
		assertEquals(404,response.getStatusCode());
		System.out.println("400 Not Found");
	}

	@When("User sends HTTPS Request with valid Batch Id")
	public void user_sends_https_request_with_valid_batch_id() {
		response = request.when().get(Routes.GETASSIGNFORBATCH+batchID);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request  with invalid Batch Id")
	public void user_sends_https_request_with_invalid_batch_id() {
		response = request.when().get(Routes.GETASSIGNFORBATCH+10);
		System.out.println("response: " + response.prettyPrint());
	}

	@Given("User creates PUT Request for the LMS API assignment endpoint")
	public void user_creates_put_request_for_the_lms_api_assignment_endpoint() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and request Body with Valid Assignment Id")
	public void user_sends_https_request_and_request_body_with_valid_assignment_id() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentName","Jul23-APIDiggers-DA-Tableau-001");
		 requestBody.put("assignmentId",assignID);
		 requestBody.put("batchId",BatchStepDef.bID);
		 requestBody.put("createdBy","U8608");
		 requestBody.put("graderId","U8608");
		 requestBody.put("dueDate","2023-07-31T19:42:41.680Z");
		 requestBody.put("assignmentDescription","Tableau Project");
		 requestBody.put("comments","Diabetes");
		 requestBody.put("pathAttachment1","filepath1");
		 requestBody.put("pathAttachment2","filepath2");
		 requestBody.put("pathAttachment3","filepath3");
		 requestBody.put("pathAttachment4","filepath4");
		 requestBody.put("pathAttachment5","filepath5");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTASSIGNMENT+assignID);
		 System.out.println("response: " + response.prettyPrint());
	        LoggerLoad.info("Assignment Updated Successfully");
	}

	@When("User sends HTTPS Request and request Body with invalid Assignment ID")
	public void user_sends_https_request_and_request_body_with_invalid_assignment_id() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentName","Jul23-APIDiggers-DA-Tableau-001");
		 requestBody.put("assignmentId",assignID);
		 requestBody.put("batchId","7007");
		 requestBody.put("createdBy","U8608");
		 requestBody.put("graderId","U8608");
		 requestBody.put("dueDate","2023-07-31T19:42:41.680Z");
		 requestBody.put("assignmentDescription","Tableau Project");
		 requestBody.put("comments","Diabetes");
		 requestBody.put("pathAttachment1","filepath1");
		 requestBody.put("pathAttachment2","filepath2");
		 requestBody.put("pathAttachment3","filepath3");
		 requestBody.put("pathAttachment4","filepath4");
		 requestBody.put("pathAttachment5","filepath5");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTASSIGNMENT+20);
	}

	@When("User sends HTTPS Request  and request Body with missing mandatory fields for valid id")
	public void user_sends_https_request_and_request_body_with_missing_mandatory_fields_for_valid_id() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("assignmentName","Jul23-APIDiggers-DA-Tableau-001");
		 //requestBody.put("assignmentId",assignID);
		 //requestBody.put("batchId","7007");
		 requestBody.put("createdBy","U8608");
		 requestBody.put("graderId","U8608");
		 //requestBody.put("dueDate","2023-07-31T19:42:41.680Z");
		 requestBody.put("assignmentDescription","Tableau Project");
		 requestBody.put("comments","Diabetes");
		 requestBody.put("pathAttachment1","filepath1");
		 requestBody.put("pathAttachment2","filepath2");
		 requestBody.put("pathAttachment3","filepath3");
		 requestBody.put("pathAttachment4","filepath4");
		 requestBody.put("pathAttachment5","filepath5");
		 response = request.body(requestBody.toJSONString()).put(Routes.PUTASSIGNMENT+assignID);
	}

	@Given("User creates DELETE Request for the LMS API assignment endpoint")
	public void user_creates_delete_request_for_the_lms_api_assignment_endpoint() {
	   request=given();
	}

	/*@When("User sends delete HTTPS Request with  valid Assignment Id")
	public void user_sends_delete_https_request_with_valid_assignment_id() {
		response = request.when().delete(Routes.DELETEASSIGNMENT+assignID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("Assignment Deleted Successfully");
	}*/

	@When("User sends delete HTTPS Request with invalid Assignment Id")
	public void user_sends_delete_https_request_with_invalid_assignment_id() {
		response = request.when().delete(Routes.DELETEASSIGNMENT+13);
		System.out.println("response: " + response.prettyPrint());
	}

}
