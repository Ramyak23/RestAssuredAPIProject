package stepdefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import org.json.simple.JSONObject;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utilities.LoggerLoad;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserStepDef {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	JSONObject jsonobject=new JSONObject();
	public static String userID=null;
	
	@Given("User creates GET Request for the LMS API User endpoint")
	public void user_creates_get_request_for_the_lms_api_user_endpoint() {
		request = given();
	}

	@When("User sends get all HTTPS Request")
	public void user_sends_get_all_https_request() {
		LoggerLoad.info("*****Validating User Module*****");
		response = request.when().get(Routes.GETALLUSER);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} OK Status with user details response body.")
	public void user_receives_ok_status_with_user_details_response_body(int statuscode) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Given("User creates POST user Request for the LMS API endpoint")
	public void user_creates_post_user_request_for_the_lms_api_endpoint() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and user request Body with mandatory, additional fields")
	public void user_sends_https_request_and_user_request_body_with_mandatory_additional_fields() throws JsonProcessingException {
		
		        
		Map<String, Object> map = new LinkedHashMap();
		map.put("userComments", "creating new user as student");
		map.put("userEduPg", "ME");
		map.put("userEduUg", "BE");
		map.put("userFirstName", "APIDiggers_002");
		map.put("userLastName", "John");
		map.put("userLinkedinUrl", "xyzaba@linkedin.com");
		map.put("userLocation", "USA");
		map.put("userMiddleName", "Mary");
		map.put("userPhoneNumber", "362547801245");
		map.put("userTimeZone", "CST");
		map.put("userVisaStatus","H4");
		map.put("userRoleMaps", Arrays.asList(new LinkedHashMap<String, String>() {
		    {
		        put("roleId", "R03");
		        put("userRoleStatus","Active");
		    }
		}));
        
        
        // Send the POST request with the constructed request body
        response = request.body(map).when().post(Routes.POSTUSER);
        String abc = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(abc);
	}

	@Then("User receives {int} Created Status with user response body.")
	public void user_receives_created_status_with_user_response_body(int statuscode) {
				assertEquals(response.getStatusCode(), 201);
				LoggerLoad.info("User Created Successfully");
				 JsonPath jsonPath = response.jsonPath();
				 userID=jsonPath.getString("userId");
				 System.out.println("userid: "+userID);
	}

	@When("User sends HTTPS Request and request Body with missing mandatory fields")
	public void user_sends_https_request_and_request_body_with_missing_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
        requestBody.put("userComments","creating new user as student");
        requestBody.put("userEduPg","ME");
        requestBody.put("userEduUg","BE");
       // requestBody.put("userFirstName","APIDiggers_002");
        requestBody.put("userLastName","John");
        requestBody.put("userLinkedinUrl","xyzaba@linkedin.com");
        requestBody.put("userLocation","USA");
        requestBody.put("userMiddleName","Mary");
        //requestBody.put("userPhoneNumber","362547801245");
        requestBody.put("userTimeZone", "CST");
        requestBody.put("userVisaStatus", "H4");
        JSONObject roledeatils = new JSONObject();
        roledeatils.put("roleId", "R03");
        roledeatils.put("userRoleStatus", "Active");    
        requestBody.put("userRoleMaps", roledeatils);
        // Send the POST request with the constructed request body
        response = request.body(requestBody.toJSONString()).post(Routes.POSTUSER);
	}

	@Then("User receives {int} Bad Request Status with message and boolean success user details")
	public void user_receives_bad_request_status_with_message_and_boolean_success_user_details(int statuscode) {
		assertEquals(400,response.getStatusCode());
		System.out.println("400 Bad Request");
	}

	@When("User sends HTTPS Request with valid UserID")
	public void user_sends_https_request_with_valid_user_id() {
		response = request.when().get(Routes.GETUSERBYID+userID);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request with invalid User ID")
	public void user_sends_https_request_with_invalid_user_id() {
		response = request.when().get(Routes.GETUSERBYID+"U10");
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("User receives {int} Not Found Status with message and boolean success user details")
	public void user_receives_not_found_status_with_message_and_boolean_success_user_details(int statuscode) {
		assertEquals(404,response.getStatusCode());
			System.out.println("400 Not Found");
	}

	@When("User sends HTTPS Request for all staff endpoint")
	public void user_sends_https_request_for_all_staff_endpoint() {
		response = request.when().get(Routes.GETALLSTAFF);
		System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request for user with roles")
	public void user_sends_https_request_for_user_with_roles() {
		response = request.when().get(Routes.GETALLUSERWITHROLES);
		System.out.println("response: " + response.prettyPrint());
	}

	@Given("User creates PUT Request for the LMS API user endpoint")
	public void user_creates_put_request_for_the_lms_api_user_endpoint() {
		request = RestAssured.given().baseUri(Routes.BASE_URI).contentType(ContentType.JSON);
	}

	@When("User sends HTTPS Request and request Body with user mandatory and additional fields")
	public void user_sends_https_request_and_request_body_with_user_mandatory_and_additional_fields() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("userComments","creating new user as student");
	        requestBody.put("userEduPg","ME");
	        requestBody.put("userEduUg","BTech");
	        requestBody.put("userFirstName","APIDiggers_002");
	        requestBody.put("userId", userID);
	        requestBody.put("userLastName","Johnny");
	        requestBody.put("userLinkedinUrl","xyzaba@linkedin.com");
	        requestBody.put("userLocation","USA");
	        requestBody.put("userMiddleName","Mary");
	        requestBody.put("userPhoneNumber","362547801245");
	        requestBody.put("userTimeZone", "EST");
	        requestBody.put("userVisaStatus", "H4");
			   response = request.body(requestBody.toJSONString()).put(Routes.UPDATEUSER+userID);
		        System.out.println("response: " + response.prettyPrint());
		        LoggerLoad.info("User Updated Successfully");

	}

	@When("User sends HTTPS Request  and request Body  \\(missing user mandatory fields)")
	public void user_sends_https_request_and_request_body_missing_user_mandatory_fields() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("userComments","creating new user as student");
	        requestBody.put("userEduPg","ME");
	        requestBody.put("userEduUg","BTech");
	        //requestBody.put("userFirstName","APIDiggers_002");
	        requestBody.put("userId", userID);
	        //requestBody.put("userLastName","Johnny");
	        requestBody.put("userLinkedinUrl","xyzaba@linkedin.com");
	        requestBody.put("userLocation","USA");
	        requestBody.put("userMiddleName","Mary");
	        requestBody.put("userPhoneNumber","362547801245");
	        requestBody.put("userTimeZone", "EST");
	        requestBody.put("userVisaStatus", "H4");
			   response = request.body(requestBody.toJSONString()).put(Routes.UPDATEUSER+userID);
		        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request and request Body to Update User role status by User ID")
	public void user_sends_https_request_and_request_body_to_update_user_role_status_by_user_id() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("roleId","R03");
        requestBody.put("userRoleStatus","Available");
        response = request.body(requestBody.toJSONString()).put(Routes.PUTROLESTATUS+userID);
        System.out.println("response: " + response.prettyPrint());
			 
	}

	@When("User sends HTTPS Request and request Body to Assign User to Program\\/Batch by UserID")
	public void user_sends_https_request_and_request_body_to_assign_user_to_program_batch_by_user_id() throws JsonProcessingException {
		Map<String, Object> map = new LinkedHashMap();
		map.put("programId", BatchStepDef.programID);
		map.put("roleId", "R03");
		map.put("userId", userID);

			map.put("userRoleProgramBatches", Arrays.asList(new LinkedHashMap<String, String>() {
		    {
		        put("batchId", BatchStepDef.bID.toString());
		        put("userRoleProgramBatchStatus","Active");
		    }
		}));
        
        
        // Send the POST request with the constructed request body
        response = request.body(map).when().put(Routes.PUTPRGMBATCHSTATUS+userID);
        String res = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(res);
	}

	@Given("User creates DELETE Request for the LMS API endpoint")
	public void user_creates_delete_request_for_the_lms_api_endpoint() {
		request = given();
	}

	/*@When("User sends HTTPS Request to delete a user with valid User Id")
	public void user_sends_https_request_to_delete_a_user_with_valid_user_id() {
		response = request.when().delete(Routes.DELETEUSER+userID);
		System.out.println("response: " + response.prettyPrint());
		LoggerLoad.info("User Deleted");
	}*/

	@When("User sends HTTPS Request to delete a user with invalid User Id")
	public void user_sends_https_request_to_delete_a_user_with_invalid_user_id() {
		response = request.when().delete(Routes.DELETEUSER+"U23");
		System.out.println("response: " + response.prettyPrint());
	}
   
	@When("User sends HTTPS Request and  request Body with invalid User Id")
	public void user_sends_https_request_and_request_body_with_invalid_user_id() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("userComments","creating new user as student");
	        requestBody.put("userEduPg","ME");
	        requestBody.put("userEduUg","BTech");
	        requestBody.put("userFirstName","APIDiggers_002");
	        requestBody.put("userId", "U30");
	        requestBody.put("userLastName","Johnny");
	        requestBody.put("userLinkedinUrl","xyzaba@linkedin.com");
	        requestBody.put("userLocation","USA");
	        requestBody.put("userMiddleName","Mary");
	        requestBody.put("userPhoneNumber","362547801245");
	        requestBody.put("userTimeZone", "EST");
	        requestBody.put("userVisaStatus", "H4");
			   response = request.body(requestBody.toJSONString()).put(Routes.UPDATEUSER+"U30");
		        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request and  request Body for user role status")
	public void user_sends_https_request_and_request_body_for_user_role_status() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("roleId","R03");
        requestBody.put("userRoleStatus","Available");
        response = request.body(requestBody.toJSONString()).put(Routes.PUTROLESTATUS+"U15");
        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request and  request Body with role missing field")
	public void user_sends_https_request_and_request_body_with_role_missing_field() {
		JSONObject requestBody = new JSONObject();
		//requestBody.put("roleId","R03");
        requestBody.put("userRoleStatus","Available");
        response = request.body(requestBody.toJSONString()).put(Routes.PUTROLESTATUS+userID);
        System.out.println("response: " + response.prettyPrint());
	}

	@When("User sends HTTPS Request and  request Body with program \\/ batch invalid userid")
	public void user_sends_https_request_and_request_body_with_program_batch_invalid_userid() throws JsonProcessingException {
		Map<String, Object> map = new LinkedHashMap();
		map.put("programId", "11900");
		map.put("roleId", "R03");
		map.put("userId", "U25");

			map.put("userRoleProgramBatches", Arrays.asList(new LinkedHashMap<String, String>() {
		    {
		        put("batchId", "7007");
		        put("userRoleProgramBatchStatus","Active");
		    }
		}));
        
        
        // Send the POST request with the constructed request body
        response = request.body(map).when().put(Routes.PUTPRGMBATCHSTATUS+"U25");
        String xyza = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(xyza);
	}

	@When("User sends HTTPS Request and  request Body with program \\/ batch missing fields")
	public void user_sends_https_request_and_request_body_with_program_batch_missing_fields() throws JsonProcessingException {
		Map<String, Object> map = new LinkedHashMap();
		//map.put("programId", "11900");
		map.put("roleId", "R03");
		//map.put("userId", userID);

			map.put("userRoleProgramBatches", Arrays.asList(new LinkedHashMap<String, String>() {
		    {
		        put("batchId", "7007");
		        put("userRoleProgramBatchStatus","Active");
		    }
		}));
        
        
        // Send the POST request with the constructed request body
        response = request.body(map).when().put(Routes.PUTPRGMBATCHSTATUS+userID);
        String xyz1 = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(xyz1);
	}


}
