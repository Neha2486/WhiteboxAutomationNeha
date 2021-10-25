package RestAssuredForNormalFramework;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postRequest {
			
		@Test
		public void postMapApi() {
			
			//specify uri
			
			RestAssured.baseURI = "https://reqres.in/api/users";
			
			//get request object
			RequestSpecification httpRequest = RestAssured.given();
			
			//request payload sending along with post request
			
			httpRequest.header("Content-Type", "application/json");
			
			
			JSONObject requestParams = new JSONObject();
			
			requestParams.put("name","Neha");
			requestParams.put("job","teacher");
			
		
			
			
			httpRequest.body(requestParams.toJSONString()); // to send all json data to request
			
			// get response object
			Response response = httpRequest.request(Method.POST);
			
			//get response body
			String responseBody = response.asString();
			System.out.println(responseBody);
			
			//get response code
			int responseCode = response.statusCode();
			Assert.assertEquals(responseCode, 201);
			System.out.println(responseCode);
			String statusLine = response.statusLine();
			System.out.println(statusLine);
			
			//get place_ID
			
//			String empID = response.jsonPath().get("id");
//			System.out.println(empID);
			
			
			
		}
}
