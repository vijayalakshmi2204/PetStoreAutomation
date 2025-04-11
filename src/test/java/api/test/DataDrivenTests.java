package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userendpoints;
import api.payload.User;
import api.utilities.DataProviderUtils;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider="userDataArray",dataProviderClass=DataProviderUtils.class)
	public void CreateUser_test(String userid,String Username,String firstname,String lastname,String email,String password,String phone){
		
		User userdata = new User();
		userdata.setId(Integer.parseInt(userid));
		userdata.setUsername(Username);
		userdata.setFirstName(firstname);
		userdata.setLastName(lastname);
		userdata.setEmail(email);
		userdata.setPassword(password);
		userdata.setPhone(phone);
		
		Response response = userendpoints.CreateUser(userdata);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider="usernamesOnly",dataProviderClass=DataProviderUtils.class)
	public void deleteuser_test(String Username) {
		
		Response response = userendpoints.deleteuser(Username);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
