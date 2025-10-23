package integrationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import projectapis.conceptual.*;
import projectapis.network.*;
import projectapis.process.*;

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