package pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Deserialization4 {

	public static void main(String[] args) {
		
		String[] courseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};
	
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
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWhFcu0AuKhpdaYpVgL-Wm5wJEVJcJifQUVrl3OApUvz4jm5W3drBHf8fgCAnkhAQQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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
		
	MainClass1 mc1 = given().queryParam("access_token", accessToken ).expect().defaultParser(Parser.JSON) // we need to explicitly say response we need in json format,if content type in postman header is application/json then we can avoid this step
	.when()
	.get("https://rahulshettyacademy.com/getCourse.php").as(MainClass1.class);// as method will help json file CONVERT into java object
	
		
		System.out.println(mc1.getInstructor());
		System.out.println(mc1.getLinkedIn());
		
		//get course title of 2nd course in api course
		
		String title = mc1.getCourses().getApi().get(1).getCourseTitle();
		System.out.println(title);
		
		// get the price of title protracter
		List<Webautomation> wAutoCourses = mc1.getCourses().getWebAutomation();
		
		for(int i=0; i<wAutoCourses.size(); i++) {
			if(wAutoCourses.get(i).getCourseTitle().equalsIgnoreCase("protractor"))
			{
				String requiredPrice =  wAutoCourses.get(i).getPrice();
				System.out.println(requiredPrice);
			}

		}
		//get all course title present in webAutomation and compare with expected title
		ArrayList<String> al = new ArrayList<String>();
		for(int j = 0; j<wAutoCourses.size(); j++) {
			al.add(wAutoCourses.get(j).getCourseTitle());
		}
		
		List<String> expectedList= Arrays.asList(courseTitle);		
		
		Assert.assertTrue(al.equals(expectedList));;
	}
	}


