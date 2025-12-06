package testharness;

import java.io.File;
import projectapis.network.UserAPI;

import projectapis.conceptual.FactorialAPI;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final UserAPI coordinator;

	public TestUser(UserAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ','; //changed delimiter to follow my format
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		
		// Set inputs on the UserAPI
        coordinator.setInput(inputPath);
        coordinator.setOutput(outputPath);
        coordinator.setDelimiter(String.valueOf(delimiter));

        // Execute computation
        coordinator.executeComputation();
	}

}
