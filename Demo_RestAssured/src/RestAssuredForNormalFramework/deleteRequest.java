package RestAssuredForNormalFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class deleteRequest {

		@Test
		public void deleteData() {
			
			//specify the uri
			RestAssured.baseURI = "https://reqres.in/api/users/2";
			
			//get request object
			RequestSpecification req = RestAssured.given();
			
			// get response object
			Response res = req.request(Method.DELETE);
			
			int statusCode= res.statusCode();
			Assert.assertEquals(statusCode, 204); //if there is no content to delete 204 will get
			
			System.out.println("The all headers: "+ res.getHeaders());
		}
}
