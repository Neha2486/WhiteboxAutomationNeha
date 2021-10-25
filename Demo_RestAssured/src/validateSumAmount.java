import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class validateSumAmount {
	
	
	
	@Test
	public void validateAmount() {
	JsonPath js = new JsonPath(payLoad.complexJson());
	
	int coursesCount = js.getInt("courses.size()");

	int purchaseAmount = js.getInt("dashbojard.purchaseAmount");
	int sum = 0;
	//Verify if sum of all course prices matches with purchase Amount
	
	for(int i = 0; i <coursesCount; i++) {
		int price = js.getInt("courses["+ i +"].price");
		int coursecopies = js.getInt("courses["+ i +"].copies"); 	
		int  amount = price*coursecopies;
		sum = sum +amount;
	}
	System.out.println(sum);
	
	Assert.assertEquals(purchaseAmount, sum);
}
}