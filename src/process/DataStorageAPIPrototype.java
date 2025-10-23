package process;

import java.util.List;
import java.util.Arrays;
import project.annotations.ProcessAPIPrototype;
import projectapis.ComputationStatus;
import projectapis.DataStorageAPI;

public class DataStorageAPIPrototype {

    @ProcessAPIPrototype
    public static void prototype(DataStorageAPI api) {
        // pretend client usage
    	List<Integer> inputs = api.loadIntegers("input.txt", ",");
        api.storeResults("output.txt", Arrays.asList(145L, 720L));
        ComputationStatus status = api.getComputationStatus();
    }
}
