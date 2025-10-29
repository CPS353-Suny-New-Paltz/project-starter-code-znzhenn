package project.checkpointtests;

import projectapis.network.UserAPIImplementation;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.process.DataStorageAPIImplementation;


public class ManualTestingFramework {

	public static final String INPUT = "manualTestInput.txt";
	public static final String OUTPUT = "manualTestOutput.txt";

	public static void main(String[] args) {
		// TODO 1:
		// Instantiate a real (ie, class definition lives in the src/ folder)
		// implementation of all 3 APIs
		DataStorageAPIImplementation dataStorage = new DataStorageAPIImplementation();
        FactorialAPIImplementation factorialAPI = new FactorialAPIImplementation();
        UserAPIImplementation userAPI = new UserAPIImplementation();
		//
		// TODO 2:
		// Run a computation with an input file of <root project
		// dir>/manualTestInput.txt and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ','

        //execute
        long result = userAPI.executeComputation();
        
        //print result
        System.out.println("Computation result: " + result);
        
        //fetch stored results
        long fetched = userAPI.fetchExistingResult();
        System.out.println("Fetched existing result: " + fetched);
        
		// Helpful hint: the working directory of this program is <root project dir>,
		// so you can refer to the files just using the INPUT/OUTPUT constants. You do
		// not
		// need to (and should not) actually create those files in your repo
	}
}
