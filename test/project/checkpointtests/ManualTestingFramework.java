package project.checkpointtests;

import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;


public class ManualTestingFramework {

	public static final String INPUT = "manualTestInput.txt";
	public static final String OUTPUT = "manualTestOutput.txt";

	public static void main(String[] args) {

		// implementation of all 3 APIs
		DataStorageAPIImplementation dataStorage = new DataStorageAPIImplementation();
        FactorialAPIImplementation factorialAPI = new FactorialAPIImplementation();
        UserAPIImplementation userAPI = new UserAPIImplementation();
		
        
        //configure userAPI
        userAPI.setInput(INPUT);
        userAPI.setOutput(OUTPUT);
        userAPI.setDelimiter(",");

       
        //execute
        long result = userAPI.executeComputation();
        System.out.println("Computation result: " + result);

        
        // check if result is valid
        if (result == 0) {
            System.out.println("Computation succeeded. Result: " + result);
        } else {
            System.out.println("Computation failed. Result: " + result);
        }
        
        //load saved input data
        String savedData = dataStorage.loadData();
        System.out.println("Saved input data: " + savedData);

        if (!savedData.isEmpty()) {
            System.out.println("Manual test completed successfully.");
        } else {
            System.out.println("Manual test failed: check data storage.");
        }
        
        //fetch result
        long fetchedResult = userAPI.fetchExistingResult();
        System.out.println("Fetched existing result: " + fetchedResult);
        
        String savedData = dataStorage.loadData();
        System.out.println("Saved input data: " + savedData);
        
        if (fetchedResult == result && !savedData.isEmpty()) {
            System.out.println("Manual test completed successfully.");
        } else {
            System.out.println("Manual test failed: check computation or data storage.");
        }
        
		// Helpful hint: the working directory of this program is <root project dir>,
		// so you can refer to the files just using the INPUT/OUTPUT constants. You do
		// not
		// need to (and should not) actually create those files in your repo
	}
}
