package smoketests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import network.UserAPIImplementation;
import projectapis.UserAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserAPITest {
	@Test
	void smokeTestUSerAPI() {
		UserAPI api = Mockito.mock(UserAPI.class);
		Mockito.when(api.executeComputation()).thenReturn(40L);
		
		long result = api.executeComputation();
		
		assertEquals(40L, result);
		
		UserAPI realAPI = new UserAPIImplementation();
		assertNotNull(realAPI);
		
		/* not implemented methods
		void setInput(String input);
		void setOutput(String output);
		
		//not necessary in my computation
		void setDelimiter(String delimiter);
		
		long executeComputation();
		long fetchFactorialOfSum();
		long fetchExistingResult(); */
	
		
	}
	
	
	
}