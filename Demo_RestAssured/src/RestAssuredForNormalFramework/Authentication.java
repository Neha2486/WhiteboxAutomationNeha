package RestAssuredForNormalFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authentication {

	
		@Test
		public void basicAuth() {
			
			//basic authentication
			
			RestAssured.baseURI = "https://api.github.com/user";
			
			PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();	// class to create Authentication object
			//need user name and password to enter github otherwise it will send 401 ...need authentication
			authScheme.setUserName("Neha2486");
			authScheme.setPassword("ShrutiYashwin@24");
			
			RestAssured.authentication= authScheme;
			
			RequestSpecification req = RestAssured.given();
			
			Response res = req.request(Method.GET);
			
			int statusCode = res.statusCode();
			System.out.println( "Status code :" + statusCode);
			Assert.assertEquals(statusCode, 200);
			
			System.out.println("status body :" + res.getBody().asString());
			
		}
}
