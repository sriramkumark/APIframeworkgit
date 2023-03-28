package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;  // need to manually 
import io.restassured.response.Response;
import static org.junit.Assert.*;
//  instead of ceating object for Utils class ,by extending the class get all the methods in it.
public class stepDefination extends Utils {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response; 
	TestDataBuild data= new TestDataBuild();
	static String place_id;
	//@Given("Add place Payload")
	
	//when static data used
	/*public void add_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  //  throw new io.cucumber.java.PendingException();
	//System.out.println("add place");
		 
		// resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        // respec object moving to another method response spec 
		res=given().spec(requestSpecification())
		.body(data.addPlacePayload());
	}*/
	

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  //  throw new io.cucumber.java.PendingException();
	//	System.out.println(name);
		res=given().spec(requestSpecification())
				.body(data.addPlacePayload(name,language,address));
	}



	//@Given("Add place Payload")
	//public void add_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  //  throw new io.cucumber.java.PendingException();
	//System.out.println("add place");
		 
		// resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        // respec object moving to another method response spec 
		//res=given().spec(requestSpecification())
		//.body(data.addPlacePayload(name,language,address));
	//}
	//with dynamic parameter with http method
	/*@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String resource) {*/
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		//		System.out.println("add place");
		
		// adding new enum APIResouce class
	/*APIResources resourceAPI= APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    
		// to perform multiple API request witn either GET or POST
		//	response =res.when().post("/maps/api/place/add/json").
		//response =res.when().post("/maps/api/place/add/json").
		
		//to call the AddplaceAPI with enum calls constrctor
		
		response =res.when().post(resourceAPI.getResource()).
		
				then().spec(resspec).extract().response();
		
	}   */
	
		
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	//Construcor will called with value of resource which you pass 
		APIResources resourceAPI= APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
			if(method.equalsIgnoreCase("POST"))
		
				{response =res.when().post(resourceAPI.getResource());
			String check1= response.asString();
			System.out.println("post");
			System.out.println(check1);
				}
		else if(method.equalsIgnoreCase("GET"))	
			{response =res.when().get(resourceAPI.getResource());
			String check2= response.asString();
			System.out.println("get");
			System.out.println(check2);
			}
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1)
	{
	   // // Write code here that turns the phrase above into concrete actions
	  //  throw new io.cucumber.java.PendingException();
		//System.out.println("add place");
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		//System.out.println("add place");
		//String resp=response.asString();
		//JsonPath js = new JsonPath(resp);
		//assertEquals(js.get(keyValue),ExpectedValue);
		//String ssres=getJsonPath(response,keyValue);
	//	System.out.println(ssres);
        //assertEquals(ssres,ExpectedValue);
		assertEquals(getJsonPath(response,keyValue),ExpectedValue);
	}


	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	 // throw new io.cucumber.java.PendingException();
		//String place_id=getJsonPath(response,"place_id");  for access local variable we follow this syntax
		 place_id=getJsonPath(response,"place_id");	
		res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		String actualname= getJsonPath(response, "name");
	//	System.out.println(actualname);
		assertEquals(actualname ,expectedName);
		}


	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		res= given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}



}
