package integrationtests;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

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
    	/*
    	InMemoryInput input = null;
		InMemoryOutput output = null;	*/
		
    	DataStorageAPI dataStorage = new DataStorageAPIImplementation();
        FactorialAPI factorialAPI = new FactorialAPIImplementation();
        UserAPI userAPI = new UserAPIImplementation(dataStorage, factorialAPI);

        // Run your computation
        /*String inputFile = Path.of("test/testInputFile.test").toAbsolutePath().toString();
        userAPI.setInput(inputFile);

        String outputFile = Path.of("test/fakeOutput.txt").toAbsolutePath().toString();
        userAPI.setOutput(outputFile);*/


        userAPI.setInput("test/testInputFile.test");
        userAPI.setOutput("test/fakeOutput.txt");
        userAPI.setDelimiter(",");

        long result = userAPI.executeComputation();

        // Optionally assert
        assertTrue(result >= 0);
    }
}
