package implementationtests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;
import projectapis.ComputationStatus;

import java.util.List;

public class DataStorageValidationTest {

    @Test
    public void testLoadIntegersRejectsNullInputs() {
        DataStorageAPI storage = new DataStorageAPIImplementation();

        List<Integer> result = storage.loadIntegers(null, ",");

        assertTrue(result.isEmpty(), "empty list when input is null");
        assertEquals(ComputationStatus.NOT_EXISTS, storage.getComputationStatus());
    }
}
