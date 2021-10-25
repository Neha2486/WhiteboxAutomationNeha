package RestAssuredForNormalFramework;



import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class dummyGetEmployeeapi {

	
			// specify the uri
			@Test
			public void dummyEmpGet() {
				
				RestAssured.baseURI ="https://dummy.restapiexample.com/api/v1/employee";
				
				//get request object
				RequestSpecification req = RestAssured.given();
				
				// get response object
				Response rep = req.request(Method.GET,"/1");
				
				//get full response
				
				String fullRes = rep.asString();
				System.out.println(fullRes);
				
						
			}
}
