package integrationtests;

import org.junit.jupiter.api.Test;

import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.conceptual.FactorialAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.network.UserAPI;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;


public class ComputeEngineIntegrationTest {
	
	@Test
	void testComputeEngineIntegration() {
		InMemoryInput inputConfig = new InMemoryInput(Arrays.asList(5));
		InMemoryOutput outputConfig = new InMemoryOutput();
		InMemoryDataStorageAPI dataStore = new InMemoryDataStorageAPI(inputConfig, outputConfig);
		
		FactorialAPI factorialAPI = new FactorialAPIImplementation();
        UserAPI userAPI = new UserAPIImplementation();
        
        long factorialResult = factorialAPI.computeFactorial(5);
        long userResult = userAPI.executeComputation();
        
        dataStore.storeResults("results", Arrays.asList(factorialResult, userResult));
        
        List<String> results = outputConfig.getOutput();
        
        assertEquals("120", results.get(0));
        assertEquals("40", results.get(1));

	}
	
}