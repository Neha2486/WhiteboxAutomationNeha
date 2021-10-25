import static io.restassured.RestAssured.given; //static need to download manually

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;  // static need to download manually for asserting response body

public class rest2 {

	public static void main(String[] args) {
		// assertion to check response body scope and header 
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payLoad.addPlace())  //created payload class for storing body
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)");
		
	//add place -> update place with new address->Get Place to validate if new address is present in response
		// it involve three API  POST,GET and PUT
	}

}
