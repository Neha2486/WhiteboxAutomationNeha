import io.restassured.path.json.JsonPath;

public class complexJsonCode {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(payLoad.complexJson());
		
		//print number of course return by API
		
		int coursesCount = js.getInt("courses.size()");
		
		System.out.println(coursesCount);
		
		
		// Print purchase amount
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		System.out.println(purchaseAmount);
		
		// Print title of the first course
		
		String title = js.getString("courses[0].title");
		
		System.out.println(title);
		
		//print all course title and there respective prices
		
		for(int i =0; i< coursesCount; i++) {
			String title1 = js.getString("courses["+ i +"].title");
			
			
			String price = js.getString("courses["+ i +"].price").toString();
			System.out.println("Title of course is :" + title1 + "Price is:" + price);
			
		}
		//print number of copies sold by RPA
		
		for(int i =0; i<coursesCount; i++) {
			String getCourse = js.getString("courses["+ i +"].title");
			if(getCourse.equalsIgnoreCase("RPA"))
			{
				System.out.println(js.getString("courses["+ i +"].copies"));
				
			}
		}
			
			
			
		
	}

}
