package integrationtests;

import org.junit.jupiter.api.Test;
import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

public class ComputeEngineIntegrationTest {

    @Test
    void testComputeEngineIntegration() {
        // Initial input and output
        InMemoryInput inputConfig = new InMemoryInput(Arrays.asList(1, 10, 25));
        InMemoryOutput outputConfig = new InMemoryOutput();
        DataStorageAPI dataStore = new InMemoryDataStorageAPI(inputConfig, outputConfig);

        // APIs
        FactorialAPI factorialAPI = new FactorialAPIImplementation();
        UserAPI userAPI = new UserAPIImplementation(dataStore, factorialAPI);

        // Perform computations
        List<Integer> numbers = inputConfig.getInput();
        long result1 = factorialAPI.computeFactorial(numbers.get(0));
        long result2 = userAPI.executeComputation();  // make sure this returns a long

        // Store results
        dataStore.storeResults("results", Arrays.asList(result1, result2));

        // Verify output
        List<String> results = outputConfig.getOutput();
        assertFalse(results.isEmpty(), "Output should not be empty");
        assertEquals(2, results.size(), "Should have 2 output entries");
        assertEquals("1", results.get(0), "Expected factorial of 1");
        assertEquals(String.valueOf(result2), results.get(1), "Expected UserAPI computation");
    }
}
