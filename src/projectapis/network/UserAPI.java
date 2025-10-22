//network api
package projectapis.network;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserAPI {
	//fetching factorial of the sum
    long fetchFactorialOfSum(); 
    
    long fetchExistingResult();
}
