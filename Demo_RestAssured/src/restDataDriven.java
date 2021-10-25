import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class restDataDriven {

	

		
			// adding multiple data 
			
			@Test(dataProvider = "AddressData")
			public void addBookData(String name, String address) {
			RestAssured.baseURI =  "https://rahulshettyacademy.com";
			
			String response = given().log().all().queryParam("key", "qaclick").header("Content-Type","application/json")
			.body(payLoad.addPlace1(name, address))
			.when().post("/maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath js = new JsonPath(response);
			String id = js.getString("id");
			System.out.println(id);
			
		}
			
			@DataProvider(name = "AddressData")
			public Object[][] addressData(){
				
				return new Object[][] {{"Shruti","1052 Mcknight circle, apt 2"},{"Yashwin","1051 Mcknight circle, apt 2"},{"Anand","1050 Mcknight circle, apt 2"}};
				
				
			}
		

}