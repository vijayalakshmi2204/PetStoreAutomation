package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userendpoints2 {
	
	//method to fetch data from property file
	static ResourceBundle geturl() {
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");  // here routes represents name of the property file
		return routes;
		
	}
	
	public static Response CreateUser(User payload) {
		
		String createuser_url =geturl().getString("createuser_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.log().body()
		
		.when().post(createuser_url);
		
		return response;
	}
		
		public static Response getUser(String username) {
			
			String getUser_url = geturl().getString("getUser_url");
			Response response=given()
			.pathParam("username", username)
			.log().body()
			
			.when().get(getUser_url);
			
			return response;
		
	}
		
		
		public static Response updateUser(String username,User payload) {
			
			String updateUser_url = geturl().getString("updateUser_url");
			Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		    .pathParam("username", username)
			.body(payload)
			.log().body()
			
			.when().put(updateUser_url);
			
			return response;
		}
		
		
		public static Response deleteuser(String username) {
			
			String deleteuser_url =geturl().getString("deleteuser_url");
			Response response=given()
			.pathParam("username", username)
			.log().body()
			
			.when().delete(deleteuser_url);
			
			return response;
		
	}
		
		
	
	

}
