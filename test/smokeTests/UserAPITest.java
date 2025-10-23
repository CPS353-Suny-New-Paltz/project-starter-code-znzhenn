package smokeTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import projectapis.network.UserAPI;


public class UserAPITest {
	@Test
	void smokeTestUSerAPI() {
		UserAPI api = Mockito.mock(UserAPI.class);
		Mockito.when(api.executeComputation()).thenReturn(40L);
		
		long result = api.executeComputation();
		
		/* not implemented methods
		void setInput(String input);
		void setOutput(String output);
		
		//not necessary in my computation
		void setDelimiter(String delimiter);
		
		long executeComputation();
		long fetchFactorialOfSum();
		long fetchExistingResult(); */
		
		
		assertEquals(40L, result);
		
	}
	
	
	
}