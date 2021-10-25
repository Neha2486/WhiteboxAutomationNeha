package VedangRecording;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class BasicAuthentication {
	
	//preemptive basic auth ...means passing credentials in request before server send response. no need for additional connection
	
	@Test
	public void basicAuthPostmanAPI() {
		
		given().auth().preemptive().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().log().all().assertThat().statusCode(200);
	}
	
	//challenged authentication  -doesn't send the credential until server requests explicitly.additinal request send to challenge the server 
	//and then follow up with same request again and then follow up with same request again along with basic credentials. this default one
	
	@Test
	public void basicChallengedAuth() {
		
		given().auth().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().log().all().assertThat().statusCode(200);
	}
//	@Test
//	public void basicAuthPost() {
//		
//		given().log().all().contentType("application/json").auth().preemptive().basic("Neha2486", "ShrutiYashwin@24")
//		.body("{\"name\":\"BasicAuthenticationRestAssured\"}")
//		.when()
//		.post("https://api.github.com/user/repos")
//		.then().log().all().assertThat().statusCode(201);
//	}
//	
//	@Test
//	public void basicAuthDelete() {
//	
//		given().log().all().auth().preemptive().basic("Neha2486", "ShrutiYashwin@24")
//		.when()
//		.delete("https://api.github.com/repos/Neha2486/BasicAuthenticationRestAssured")
//		.then()
//		.statusCode(204).log().all();
		
		//200 -- will delete successfully and response message
		//204-- delete successfully but not get response message (no content in response)
//	}
}
