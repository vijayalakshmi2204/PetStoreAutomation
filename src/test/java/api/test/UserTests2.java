package api.test;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userendpoints;
import api.endpoints.userendpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
    User userdata;
    
	
	public Logger logger;

    @BeforeClass
    public void setup() {
        // Initialize userdata in @BeforeMethod, but do not regenerate the username
        userdata = new User();
        faker = new Faker();
        userdata.setId(faker.idNumber().hashCode());
        userdata.setUsername(faker.name().username());  // Reuse the same username
        userdata.setFirstName(faker.name().firstName());
        userdata.setLastName(faker.name().lastName());
        userdata.setEmail(faker.internet().emailAddress());
        userdata.setPassword(faker.internet().password(5, 10));
        userdata.setPhone(faker.phoneNumber().cellPhone());
        //userdata.setUserStatus(0);  // Optional, if needed
        
        logger = LogManager.getLogger(this.getClass());	
    }
	
	@Test(priority=1)
	
	public void createUser_test() {
		
		logger.info("***********  Creating User   ************");
		
		Response response = userendpoints2.CreateUser(userdata);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		logger.info("***********  User is created   ************");
	}

	
	@Test(priority=2, dependsOnMethods = {"createUser_test"})
	public void getUser_test() throws InterruptedException {
		
		logger.info("***********  getting User  information  ************");
	    int retries = 5;  // Number of retries
	    int attempt = 0;
	    boolean userFound = false;
	    
	    while (attempt < retries && !userFound) {
	        Response response = userendpoints2.getUser(this.userdata.getUsername());
	        response.then().log().all();
	        
	        if (response.getStatusCode() == 200) {
	            userFound = true;  // User found
	            Assert.assertEquals(response.getStatusCode(), 200);
	        } else {
	            attempt++;
	            Thread.sleep(1000);  // Wait before retrying
	        }
	    }

	    if (!userFound) {
	        Assert.fail("User not found after retries");
	    }
	    
	    logger.info("***********  Displaying User information ************");
	}
	
	
	
	
	
	@Test(priority=3,dependsOnMethods = {"createUser_test"})
	public void updateUser_test() {
		
		logger.info("***********  Updating User Information   ************");
		userdata.setFirstName(faker.name().firstName());
		userdata.setLastName(faker.name().lastName());
		
		Response response = userendpoints2.updateUser(this.userdata.getUsername(),userdata);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***********   User Information is updated  ************");
		
	}
	
	@Test(priority=4)
	public void deleteUser_test() {
	    // Log the username to confirm you are deleting the correct user
	    System.out.println("Deleting user: " + this.userdata.getUsername());
	    
	    Response response = userendpoints2.deleteuser(this.userdata.getUsername());
	    response.then().log().all();
	    
	    // Check if the response status code is 404 (Not Found), 400 (Bad Request), or other failures
	    if (response.getStatusCode() == 404) {
	        System.out.println("User not found for deletion: " + this.userdata.getUsername());
	    } else {
	        Assert.assertEquals(response.getStatusCode(), 200);  // Assuming 200 OK means successful deletion
	    }
	}
	
	
	
	
	
}
