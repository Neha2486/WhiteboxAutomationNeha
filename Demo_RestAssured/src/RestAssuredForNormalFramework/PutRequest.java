package RestAssuredForNormalFramework;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PutRequest {

		@Test
		public void putReq() {
			
			// specify the uri
			RestAssured.baseURI= "https://reqres.in/api/users";
			
			//get request
			RequestSpecification req = RestAssured.given();
			
			//update payload 
			JSONObject jo = new JSONObject(); //{"name":"test","salary":"123","age":"23"}
			
			jo.put("name","neha");
			jo.put("job","IT");
			
			req.body(jo.toJSONString());
			
			//get respose object
			
			Response res = req.request(Method.PUT,"/930" );
			
			int statusCode = res.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			
			String resBody = res.getBody().asString();
			System.out.println(resBody);
			
			System.out.println(res.asString());
		}
}
