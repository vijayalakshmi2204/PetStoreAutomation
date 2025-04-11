package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userendpoints {
	public static Response CreateUser(User payload) {
		
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.log().body()
		
		.when().post(Routes.createuser_url);
		
		return response;
	}
		
		public static Response getUser(String username) {
			
			
			Response response=given()
			.pathParam("username", username)
			.log().body()
			
			.when().get(Routes.getUser_url);
			
			return response;
		
	}
		
		
		public static Response updateUser(String username,User payload) {
			
			
			Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		    .pathParam("username", username)
			.body(payload)
			.log().body()
			
			.when().put(Routes.updateUser_url);
			
			return response;
		}
		
		
		public static Response deleteuser(String username) {
			
			
			Response response=given()
			.pathParam("username", username)
			.log().body()
			
			.when().delete(Routes.deleteuser_url);
			
			return response;
		
	}
		
		
	
	

}
