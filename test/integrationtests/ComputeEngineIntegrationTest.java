package integrationtests;

import org.junit.jupiter.api.Test;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;


public class ComputeEngineIntegrationTest {
	
	@Test
	void testComputeEngineIntegration() {
		//initial input
		InMemoryInput inputConfig = new InMemoryInput(Arrays.asList(1,10,25));
		InMemoryOutput outputConfig = new InMemoryOutput();
		InMemoryDataStorageAPI dataStore = new InMemoryDataStorageAPI(inputConfig, outputConfig);
		
		FactorialAPI factorialAPI = new FactorialAPIImplementation();
        UserAPI userAPI = new UserAPIImplementation();
        
        List<Integer> numbers = inputConfig.getInput();
        
        long result1 = factorialAPI.computeFactorial(numbers.get(0));
        long result2 = userAPI.executeComputation();
       
        
        dataStore.storeResults("results", Arrays.asList(result1, result2));
        
        List<String> results = outputConfig.getOutput();
        
        assertFalse(results.isEmpty(), "Output should not be empty");
        assertEquals(2, results.size(), "Should have 2 output entries");
        assertEquals("1", results.get(0), "Expected factorial of 1");      
        assertEquals("40", results.get(1), "Expected UserAPI computation");

	}
	
}