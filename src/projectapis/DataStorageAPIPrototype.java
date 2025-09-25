package projectapis;

import project.annotations.ProcessAPIPrototype;

public class DataStorageAPIPrototype {

    @ProcessAPIPrototype
    public static void prototype(DataStorageAPI api) {
        // pretend client usage
        api.saveComputation();
        long result = api.fetchComputation();
        ComputationStatus status = api.getComputationStatus();
    }
}
