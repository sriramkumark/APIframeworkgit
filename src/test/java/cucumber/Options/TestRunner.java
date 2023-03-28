package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin= "json:target/jsonReports/cucu-reports.json", glue= "stepDefinations")
public class TestRunner {
//tags= "@DeletePlace""
	
	// to run with help of maven command , copy the project properties with location
	//eg:C:\Users\SV58TR744\eclipse-workspace\APIframework
	//goto cmd prompt and get into the location.  
	//in project  under bdd.framework package aap.java delete manually and then run the below command
	//give command mvn test (Complie and run)
	
	
	// if you have multiple feature file , under features give the exact path 
	//for generating HTMl report use maven cucumber report , githud depency from google search copy the code and paset in pom.xml file
	// in cucumber.options need to add plugin for json output to send as input for gerating reporting 
	// to run ,under cmd promptn   give: mvn test verify
 	
	
}
