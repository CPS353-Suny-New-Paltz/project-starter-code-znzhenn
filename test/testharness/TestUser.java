package testharness;

import java.io.File;
import org.junit.jupiter.api.Test;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.MultithreadedNetworkAPI;
import projectapis.network.UserAPI;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final UserAPI coordinator;

	public TestUser() {
        FactorialAPI factorialAPI = new FactorialAPIImplementation();
        this.coordinator = new MultithreadedNetworkAPI(factorialAPI);
    }

	public void run(String outputPath) {
		char delimiter = ','; //changed delimiter to follow my format
		String inputPath = "test/testInputFile.test";
		// String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		File outFile = new File(outputPath);
        outFile.getParentFile().mkdirs();

		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		
		// Set inputs on the UserAPI
        coordinator.setInput(inputPath);
        coordinator.setOutput(outputPath);
        coordinator.setDelimiter(String.valueOf(delimiter));

        // Execute computation
        coordinator.executeComputation();
	}
	
	@Test
    public void explicitConstructorCallForSmokeTest() {
        new MultithreadedNetworkAPI(new FactorialAPIImplementation());
    }

	
}
