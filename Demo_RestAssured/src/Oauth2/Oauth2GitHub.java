package Oauth2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Oauth2GitHub {
	
	String apiToken = "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";
	String url ="https://api.github.com";

	
		@Test
		public void oAuthGit() {
			String getUrl = url + "/user/repos";
			given().auth().oauth2(apiToken)
			.when().get(url)
			.then().log().all().statusCode(200);
		}
		}