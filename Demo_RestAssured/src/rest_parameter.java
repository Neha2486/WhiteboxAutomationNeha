import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class rest_parameter {
			
	
	@Test
	public void addBook() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payLoad.addPlace1("Neha", "1053 Mcknight circle, apt 2"))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		
		js.get("id");
		
		
	}
	
}
