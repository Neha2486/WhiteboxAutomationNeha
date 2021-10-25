import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestFatchfile {
	
	//fetching JsonData from external file
	// convert content of file into string -> content of file can convert into Byte -> Byte data to String

	public static void main(String[] args) throws IOException {
RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\anusi\\Desktop\\Json.txt"))))
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
																			
		System.out.println(response);
		
		 
		JsonPath js = new JsonPath(response); 
		
		String placeID = js.getString("place_id");
		System.out.println(placeID);

	}

}
