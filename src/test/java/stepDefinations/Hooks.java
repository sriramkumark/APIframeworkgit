package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

// Hooks used to set precondition and post conditions in cucumber
public class Hooks {
//give annotation to before execute a particula senario
	@Before("@Deleteplace")
	public void beforeScenario() throws IOException {
		//excute this code only when the placeid is Null
		//write a code thae will give you  place_id
		// we can't call feature file directly ,so calling the java code writing 
		stepDefination s= new stepDefination();
		if(stepDefination.place_id == null)
		{
			
		// calling method which in step defeination and invoke which is need prior	
		s.add_place_payload_with("shetty","French","Asia");		
		s.user_calls_with_http_request("addPlaceAPI", "POST");	
		s.verify_place_id_created_maps_to_using("shetty","getPlaceAPI");
	}
		}
}
