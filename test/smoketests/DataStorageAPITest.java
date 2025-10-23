package smoketests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import process.DataStorageAPIImplementation;
import projectapis.DataStorageAPI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataStorageAPITest {

    @Test
    void smokeTestDataStorageAPI() {
        DataStorageAPI api = Mockito.mock(DataStorageAPI.class);
        Mockito.when(api.fetchComputation()).thenReturn(0L);

        long result = api.fetchComputation();
        DataStorageAPI realAPI = new DataStorageAPIImplementation();

        assertNotNull(realAPI);
        assertEquals(0L, result);
        
    

        /* not implemented methods
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
        */
    }
}
