// process api
package projectapis;
import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorageAPI {
	//saving the result
    void saveComputation();

    //fetching the result 
    long fetchComputation();
    
    //check to see if it even exists (way to track)
    boolean hasComputation();
    
}
