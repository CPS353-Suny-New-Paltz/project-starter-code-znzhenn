// process api
package projectapis.process;
import java.util.List;

import project.annotations.ProcessAPI;
import projectapis.ComputationStatus;

@ProcessAPI
public interface DataStorageAPI {
	
	//load integers
	List<Integer> loadIntegers(String inputSource, String delimiter);
	
	//saving the result
	void storeResults(String outputSource, List<Long> results);

    //fetching the result 
    long fetchComputation();
    
    //check to see if it even exists (way to track)
    ComputationStatus getComputationStatus();

	String loadData();

	void saveData(String data);

	void saveComputation();
    
}
