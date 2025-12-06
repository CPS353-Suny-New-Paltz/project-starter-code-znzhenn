package integrationtests;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Arrays;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;
import integrationtests.InMemoryInput;
import integrationtests.InMemoryOutput;

import org.junit.jupiter.api.Test;


public class IntegrationTestExample {

    @Test
    public void testIntegration() {
    	
    	InMemoryInput input = new InMemoryInput(Arrays.asList(1, 15,10,5,2,3,8));
    	InMemoryOutput output = new InMemoryOutput();
    	
    	// real implementations
    	DataStorageAPI dataStorage = new InMemoryDataStorageAPI(input, output);
    	FactorialAPI factorialAPI = new FactorialAPIImplementation();
    	UserAPI userAPI = new UserAPIImplementation(dataStorage, factorialAPI);

    	userAPI.setInput("ignored.csv"); 
    	userAPI.setOutput("ignored.txt");
    	userAPI.setDelimiter(",");

    	long result = userAPI.executeComputation();
    	assertTrue(result >= 0, "Result should be non-negative");
    	System.out.println("Results: " + output.getOutput());

        
    }
}
