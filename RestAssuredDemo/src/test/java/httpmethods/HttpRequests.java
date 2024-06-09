package httpmethods;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;




public class HttpRequests 
{	
	//his is going to be pushed
	int id;
	
	@Test(priority=1)
	void getUser()
	{
		 given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			//Validation of a value in body
			.body("page",equalTo(2))
			//to print whole response in console window
			.log().all();
	}
	
	@Test(priority=2)
	void addNewUser()
	{
				HashMap<String,String> data = new HashMap();
				data.put("name","Shivraj");
				data.put("Job","Engineer");
				
				id = given()
					.contentType("application/json")
					.body(data)
				.when()
					.post("https://reqres.in/api/users")	
					//Getting particular integer value from response
					.jsonPath().getInt("id");
					System.out.println("The id of the user is = "+id);
				
				
				/*.then()
					.statusCode(201)
					.log().all();*/
	}
	
	@Test(priority=3,dependsOnMethods={"addNewUser"})
	void updateRecord()
	{
		HashMap<String,String> data1 = new HashMap<String, String>();
		data1.put("Name","Shivraj");
		data1.put("Job","S/w Engineer");
		given()
			.contentType("application/json")
			.body(data1)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		.contentType("application/json")
		
		.when()
		.delete("https://reqres.in/api/users/2")
		
		.then()
		//.statusCode(200)
		.log().all();
	}
}
