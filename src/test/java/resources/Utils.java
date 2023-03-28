package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		//without giving condition req it will recreate and replace with new value , multiple date can't logged.
		//use if condition with req
		if (req == null)
		{
		PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
	//	RestAssured.baseURI="https://rahulshettyacademy.com";
	//	 req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		 req =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				//logging request and response to in  file,or display in console
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		 return req;
	}
	return req;
	}
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\SV58TR744\\eclipse-workspace\\APIframework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	
		
	}
	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		
				JsonPath js = new JsonPath(resp);
				return js.get(key).toString();
	}
}
