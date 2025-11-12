package smoketests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import projectapis.conceptual.*;
import projectapis.network.*;
import projectapis.process.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserAPITest {
	@Test
	void smokeTestUSerAPI() {
		/*
		 * Mock objects bad UserAPI api = Mockito.mock(UserAPI.class);
		 * Mockito.when(api.executeComputation()).thenReturn(40L);
		 * 
		 * long result = api.executeComputation();
		 * 
		 * assertEquals(40L, result);
		 * 
		 * UserAPI realAPI = new UserAPIImplementation(); assertNotNull(realAPI);
		 */

		
		//empty implementations
		DataStorageAPI dataStorage = new DataStorageAPIImplementation();
        FactorialAPI factorialAPI = new FactorialAPIImplementation();
        UserAPI api = new UserAPIImplementation();
        
        assertNotNull(api, "UserAPI should not be null");
        
        api.setInput("testInput.txt");
        api.setOutput("testOutput.txt");
        long result = api.executeComputation();
        
        assertEquals(100L, result, "shoould fail");
        
		/*
		DataStorageAPI dataStorage = null;
		FactorialAPI factorialAPI = null;
		UserAPI api = new UserAPIImplementation();
		assertNotNull(api);

		long result = api.executeComputation();
		System.out.println("UserAPI execution result: " + result); */

		/*
		 * not implemented methods void setInput(String input); void setOutput(String
		 * output);
		 * 
		 * //not necessary in my computation void setDelimiter(String delimiter);
		 * 
		 * long executeComputation(); long fetchFactorialOfSum(); long
		 * fetchExistingResult();
		 */

	}

}