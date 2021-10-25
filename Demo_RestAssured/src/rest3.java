import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class rest3 {

	public static void main(String[] args) {
		
		//add place -> update place with new address->Get Place to validate if new address is present in response
				// it involve three API  POST,GET and PUT
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(payLoad.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
																			// .extract().response().asString() this is the method to extract response in string format
		System.out.println(response);
		
		// to extract place id from full string 
		JsonPath js = new JsonPath(response); 
		
		String placeID = js.getString("place_id");
		System.out.println(placeID);
		
		//update place
		
		String newAddress = "70 winter walk, USA";
		
		 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
				.when().put("/maps/api/place/update/json")
				.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		 
		 // Get place
		 
		String getResponse =  given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		 .when().get("/maps/api/place/get/json")
		 .then().assertThat().statusCode(200).extract().response().asString();
		 
		 JsonPath js1 = new JsonPath(getResponse);
		
		 String actualAddress = js1.getString("address");
		 System.out.println(actualAddress);
		 
		 Assert.assertEquals(actualAddress, newAddress);
		

	}

}
