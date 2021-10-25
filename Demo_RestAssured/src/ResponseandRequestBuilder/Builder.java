package ResponseandRequestBuilder;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddMapApiPojo;
import pojo.Location;

public class Builder {
	public static void main(String[] args) {
	
		//took this code from serialization
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
	
	//we can write all common code which we are using in program in given section together with the help of RequestSpecBuilder class object
	RequestSpecification req=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
								.setContentType(ContentType.JSON).build();
	
	//we can write all common attribute in response(then) together with the help of ResponseSpecBuilder class object to reduce duplication
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	RequestSpecification res = given().spec(req)
								.body(am);
	Response response = res.when().post("/maps/api/place/add/json")
					.then().spec(resspec).extract().response();
	
	String responseFinal = response.asString();
	
	System.out.println(responseFinal);
}
}