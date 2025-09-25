//network api
package projectAPIs;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserAPI {
	//fetching factorial of the sum
    long fetchFactorialOfSum(); 
    
    long fetchExistingResult();
}
