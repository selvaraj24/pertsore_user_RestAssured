package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User payload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
		logger=LogManager.getLogger(this.getClass());
		faker=new Faker();
		payload=new User();
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("***** CreatingUserTestStart  *****");
		Response response=UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** CreatingUserTestEnd  *****");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		logger.info("***** GetUserTestStart  *****");
		Response response=UserEndPoints.getUser(this.payload.getUsername());
		response.then().log().all();
		System.out.println(payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** GetUserTestEnd  *****");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("***** UpdatingUserTestStart  *****");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(payload,this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//afterUpdate
		Response afterresponse=UserEndPoints.getUser(this.payload.getUsername());
		Assert.assertEquals(afterresponse.getStatusCode(), 200);
		logger.info("***** UpdatingUserTestEnd  *****");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("***** DeleteUserTestStart  *****");
		Response response=UserEndPoints.deleteUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** DeleteUserTestEnd  *****");
	}

}
