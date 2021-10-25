package pojo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization {
	
	public static void main(String[] args) {
		
		// converting java object (am) into jason body
		
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			AddMapApiPojo am = new AddMapApiPojo();
			
			am.setAccuracy(50);
			am.setAddress("29, side layout, cohen 09");
			am.setLanguage("French-IN");
			am.setName("Rahul Shetty Academy");
			am.setPhone_number("(+91) 983 893 3937");
			am.setWebsite("http://rahulshettyacademy.com");
			
			Location l = new Location();
			l.setLat(-38.383494);
			l.setLng(33.427362);
			 
			am.setLocation(l);
			
			List<String> al = new ArrayList<String>();
			al.add("shoe park");
			al.add("shop");
			
			am.setTypes(al);
			
			
			String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(am)
			.when().post("/maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
			
			System.out.println(response);
		
	}

}
