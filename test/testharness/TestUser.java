package testharness;

import java.io.File;
import java.nio.file.Path;

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
		String delimiter = ","; //changed delimiter
		Path inputPath = Path.of("test", "testInputFile.test").toAbsolutePath();
		//String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		//check file existence
		File inputFile = inputPath.toFile();
        if (!inputFile.exists()) {
            throw new RuntimeException("Input file not found at: " + inputFile.getAbsolutePath());
        }
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		
		// Set inputs on the UserAPI
        coordinator.setInput(inputFile.getAbsolutePath());
        coordinator.setOutput(outputPath);
        coordinator.setDelimiter(delimiter);

        // Execute computation
        coordinator.executeComputation();
	}

}
