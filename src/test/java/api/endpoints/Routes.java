package api.endpoints;


/*swagger api -- https://petstore.swagger.io/v2
 * createUser -- https://petstore.swagger.io/v2/user
 * getUser ---- https://petstore.swagger.io/v2/user/vijipet1
 * 
 * updateUser ---- https://petstore.swagger.io/v2/user/vijipet2
 * delete User ---- https://petstore.swagger.io/v2/user/vijipet2
 */

public class Routes {
	
  public static String base_url = "https://petstore.swagger.io/v2";
  
  //user module
  public static String createuser_url = base_url + "/user";
  public static String getUser_url = base_url + "/user/{username}";
  public static String updateUser_url = base_url + "/user/{username}";
  public static String deleteuser_url = base_url + "/user/{username}";
  
  
  
}
