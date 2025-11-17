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
		
        UserAPIImplementation userAPI = new UserAPIImplementation();
		
        //configure userAPI
        userAPI.setInput(INPUT);
        userAPI.setOutput(OUTPUT);
        userAPI.setDelimiter(",");

       
        //execute
        long result = userAPI.executeComputation();
        System.out.println("Computation result: " + result);

        
        // check if result is valid
        if (result == 0L) {
            System.out.println("Computation succeeded. Result: " + result);
        } else {
            System.out.println("Computation failed. Result: " + result);
        }
        
        DataStorageAPI dataStorage = new DataStorageAPIImplementation();
        dataStorage.loadIntegers(INPUT, ",");
        
        //load saved input data
        String savedData = dataStorage.loadData();
        System.out.println("Saved input data: " + savedData);
        
        //load output file
        dataStorage.loadIntegers(OUTPUT, ",");
        long fetchedResult = dataStorage.fetchComputation();

        System.out.println("Fetched stored result: " + fetchedResult);
        
        if (!savedData.isEmpty()) {
            System.out.println("Manual test completed successfully.");
        } else {
            System.out.println("Manual test failed: check data storage.");
        }
        
		// Helpful hint: the working directory of this program is <root project dir>,
		// so you can refer to the files just using the INPUT/OUTPUT constants. You do
		// not
		// need to (and should not) actually create those files in your repo
	}
}
