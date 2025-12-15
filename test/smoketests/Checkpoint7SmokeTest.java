package smoketests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import projectapis.network.MultithreadedNetworkAPI;
import projectapis.network.UserAPI;
import projectapis.process.DataStorageAPIImplementation;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.conceptual.FactorialAPI;

public class Checkpoint7SmokeTest {

    @Test
    void smokeNetworkAPI() {
        //instantiate networkapi implementation
        UserAPI network = new MultithreadedNetworkAPI(new FactorialAPIImplementation());
        assertNotNull(network);
        
        network.setInput("dummyInput");
        network.setOutput("dummyOutput");
        network.setDelimiter(",");
    }

    @Test
    void smokeProcessAPI() {
        DataStorageAPI dataStorage = new DataStorageAPIImplementation();
        assertNotNull(dataStorage);

        dataStorage.storeResults("dummyOutput", java.util.List.of(1L, 2L, 3L));
        var numbers = dataStorage.loadIntegers("dummyInput", ",");
        assertNotNull(numbers);
    }

    @Test
    void smokeConceptualAPI() {
        FactorialAPI factorial = new FactorialAPIImplementation();
        assertNotNull(factorial);

        long result = factorial.computeDigitFactorialSum(5);
        assertNotNull(result);
    }
}
