package RestAssuredForNormalFramework;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	
	@Test
	public void getRequestWeater() {
		
		
			// specify the uri
			RestAssured.baseURI = "https://gorest.co.in/public-api/posts";
			
			//Get request object
			RequestSpecification httpRequest = RestAssured.given();
			
			//get response object		
			
			Response response = httpRequest.request(Method.GET);
			
			//print response body in console window
			String responseBody = response.getBody().asString();
			System.out.println(responseBody);
			
			//print responsecode in console window
			int responseCode = response.getStatusCode();
			System.out.println(responseCode);
			Assert.assertEquals(responseCode, 200);
			
			//get status line
			String statusLine = response.statusLine();
			System.out.println(statusLine);
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			
			//get complete json response
			System.out.println("complete json response:" + response.asString());
			
			//get json header
			System.out.println("complete json header :" + response.getHeaders()); // we will get only response headers
	}

}
