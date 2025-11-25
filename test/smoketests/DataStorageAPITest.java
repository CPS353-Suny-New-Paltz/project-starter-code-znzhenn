package smoketests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import projectapis.ComputationStatus;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Arrays;

public class DataStorageAPITest {

	@Test
	void smokeTestDataStorageAPI() {
		DataStorageAPI api = new DataStorageAPIImplementation();
        assertNotNull(api, "API implementation should not be null");

        // Act
        List<Integer> loaded = api.loadIntegers("fakeInput.txt", ",");
        api.storeResults("fakeOutput.txt", Arrays.asList(10L, 20L));
        ComputationStatus status = api.getComputationStatus();
        long result = api.fetchComputation();
        

        // Assert: should pass
        assertEquals(0, loaded.size(), "No integers should load from a missing file");
        assertEquals(ComputationStatus.EXISTS, status, "Status should be EXISTS after storing results");
        //assertEquals(0L, result, "Default computation result should be 0 for smoke test");
        
        /*
		Mockito.when(api.fetchComputation()).thenReturn(0L);
		assertNotNull(api);
		System.out.println("DataStorageAPI fetchComputation result: " + result);
		*/
        
        
		/*
		 * not implemented methods //load integers List<Integer> loadIntegers(String
		 * inputSource, String delimiter);
		 * 
		 * //saving the result void storeResults(String outputSource, List<Long>
		 * results);
		 * 
		 * //fetching the result long fetchComputation();
		 * 
		 * //check to see if it even exists (way to track) ComputationStatus
		 * getComputationStatus();
		 * 
		 * String loadData();
		 * 
		 * void saveData(String data);
		 * 
		 * void saveComputation();
		 */
	}
}
