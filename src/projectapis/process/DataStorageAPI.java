// process api
package projectapis.process;
import project.annotations.ProcessAPI;
import projectapis.ComputationStatus;

@ProcessAPI
public interface DataStorageAPI {
	//saving the result
    void saveComputation();

    //fetching the result 
    long fetchComputation();
    
    //check to see if it even exists (way to track)
    ComputationStatus getComputationStatus();

	String loadData();

	void saveData(String data);
    
}
