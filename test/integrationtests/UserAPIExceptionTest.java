package integrationtests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;

import java.util.List;

public class UserAPIExceptionTest {

    // throws inside loadInts
    private static class ExplodingDataStorage implements DataStorageAPI {
        @Override
        public List<Integer> loadIntegers(String input, String delimiter) {
            throw new RuntimeException("Boom!");
        }

        @Override public void storeResults(String output, List<Long> results) { 
        	
        }
        
        @Override public long fetchComputation() { 
        	return 0; 
        }
        
        @Override public projectapis.ComputationStatus getComputationStatus() { 
        	return null; 
        }
        
        @Override public String loadData() { 
        	return null; 
        }
        
        @Override public void saveComputation() {
        	
        }
    }

    @Test
    public void testExecuteComputationHandlesExceptions() {
        // uses component
        DataStorageAPI badStorage = new ExplodingDataStorage();
        FactorialAPI factorial = new FactorialAPIImplementation();

        UserAPI userAPI = new UserAPIImplementation(badStorage, factorial);

        //set so that we can use
        userAPI.setInput("anything");  
        userAPI.setOutput("anything");

        long result = userAPI.executeComputation();

        assertEquals(0L, result, 
            "should return 0L when internal exception occurs");
    }
}
