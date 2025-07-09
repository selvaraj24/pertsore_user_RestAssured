package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	public Logger logger;
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID,String userName,String firstName,String lastName,String email,String pwd,String ph) {
		logger=LogManager.getLogger(this.getClass());
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
		logger=LogManager.getLogger(this.getClass());
		Response res=UserEndPoints.getUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
