package integrationtests;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;
import org.junit.jupiter.api.Test;


public class IntegrationTestExample {

    @Test
    public void testIntegration() {
        DataStorageAPI testStorage = new DataStorageAPIImplementation();
        FactorialAPI factorialAPI = new FactorialAPIImplementation();
        UserAPI userAPI = new UserAPIImplementation(testStorage, factorialAPI);

        // Run your computation
        String inputFile = Path.of("test/testInputFile.test").toAbsolutePath().toString();
        userAPI.setInput(inputFile);

        String outputFile = Path.of("test/fakeOutput.txt").toAbsolutePath().toString();
        userAPI.setOutput(outputFile);


        long lastResult = userAPI.executeComputation();

        // Optionally assert
        assertTrue(lastResult >= 0);
    }
}
