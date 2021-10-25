package Oauth2;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class oAuth2_authenticationCode {

	public static void main(String[] args) {
		
		//Step 1 to get code by hitting the authentication server
		
//		System.setProperty("webdriver.chrome.driver","C:\\\\\\\\Program Files\\\\GeckoDriver\\\\Chromedriver.exe");
//			
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id="
//				+ "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
//			
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("nehasingh.iabm@gmail.com");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//		
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Pushpendravibha=24");
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);
		
		//fatche url for code --https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWjzqUyYMrowpUk
		//rKQVj2tgXTiGDMvoHi6_C0EHrzSQjUO_4l_QIgnUMxd6onBq7AA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#
		// String url = driver.getCurrentUrl();
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWgEp4WM5tYjDbuvHe4LE4BcUewFK8a2-MPczyLWLybYuq2C6552loDZRuANSCkyhw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		//manually enter url because google is not allowing automation and every time we run the program we need new url
		//split the url in 2 part from code and choose second part
		String splitedUrl= url.split("code=")[1];
		//split url2 again from &scope and choose 0 index url so code will come out
		String code = splitedUrl.split("&scope")[0];
		System.out.println(code);
		
	
		//step to get access token after hitting response server
		String responseAccessToken = given().urlEncodingEnabled(false) //if there is special character in code restAssured will automatically convert into some character, if we don't want to do that we need to tell explicitly by using urlEncodingEnabled method 
			.queryParams("code", code)
			.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(responseAccessToken);
		JsonPath js = new JsonPath(responseAccessToken);
		
		
		String accessToken = js.get("access_token");
		System.out.println(accessToken);
		
		//send access token to the redirected url to generate the response
	String response = given().queryParam("access_token", accessToken )
	.when().log().all()
	.get("https://rahulshettyacademy.com/getCourse.php").asString();
	// no need of extract().response().asString() because we are not doing validation in this step
	System.out.println(response);
	}

}
