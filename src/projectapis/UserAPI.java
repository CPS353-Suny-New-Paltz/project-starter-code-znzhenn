//network api
package projectapis;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserAPI {
	//fetching factorial of the sum
    long fetchFactorialOfSum(); 
    
    //yoink if result already exists
    long fetchExistingResult();
}
