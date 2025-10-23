package smokeTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;

public class DataStorageAPITest {

    @Test
    void smokeTestConceptualAPI() {
        DataStorageAPI api = Mockito.mock(DataStorageAPI.class);
        Mockito.when(api.fetchComputation()).thenReturn(0L);

        long result = api.fetchComputation();

        assertNotNull(api);
        assertEquals(0L, result);
        new DataStorageAPIImplementation();

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
