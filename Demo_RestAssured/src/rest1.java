import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class rest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Validate if add place API is working as expected
		
		//three principle of restAssured
		
		//given - all input details
		// when  - submit the API (POST, GET, PUT or DELETE) resource and http req
		//then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Rahul Shetty Academy\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		
	}

}
