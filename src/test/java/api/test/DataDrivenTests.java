package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID,String userName,String firstName,String lastName,String email,String pwd,String ph) {
		
		User payload=new User();
		
		payload.setId(Integer.parseInt(userID));
		payload.setUsername(userName);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(ph);
		payload.setPhone(ph);
		
		Response res=UserEndPoints.createUser(payload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testGetUser(String userName) {
		
		Response res=UserEndPoints.getUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
