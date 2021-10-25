package VedangRecording;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;

import java.util.List; 


public class getRequestBdd {

	@Test
	public void countPhoto() {
		
		String albums = given().
		when().get("https://jsonplaceholder.typicode.com/photos").
		then().assertThat().statusCode(200).extract().asString();
		
		List<Integer> allID = from(albums).get("id"); // from is the methods present in the JsonPath 
		// same thing we can do by creating jsonPath object
		
		System.out.println("Total Id's:" + allID.size());
		Assert.assertEquals(allID.size(), 5000);
	}
	
	@Test
	public void assertBody() {
		given().
		when().get("https://jsonplaceholder.typicode.com/photos")
		.then().assertThat().statusCode(200)
		.body("title[0]", equalTo("accusamus beatae ad facilis cum similique qui sunt"))
		.body("title[1]", is("reprehenderit est deserunt velit ipsam"));
		//we took title[0] b/c 5000 record we want to choose 1st one in url also we can give /1
		//is and equalTo methods both are present in hamcrest class and have same function
	}
	
	@Test
	public void complexJson() {
		given()
		.when().get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200)
		.body("data.email[0]", equalTo("michael.lawson@reqres.in"))
		.body("data.first_name[1]",equalTo("Lindsay"));
		//.root("data").body("last_name", is("Ferguson"));
		//root method we can mention parent data so need to right again and again.
	}
	
	@Test
	public void multipleAssert() {
		given()
		.when().get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200)		
		.body("data.first_name",hasItems("Lindsay","Michael","George"));
		// hasItem method is present in hemcrest class to assert multiple value
}}