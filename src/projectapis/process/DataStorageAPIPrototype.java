package projectapis.process;

import project.annotations.ProcessAPIPrototype;
import projectapis.ComputationStatus;

public class DataStorageAPIPrototype {

    @ProcessAPIPrototype
    public static void prototype(DataStorageAPI api) {
        // pretend client usage
        api.saveComputation();
        long result = api.fetchComputation();
        ComputationStatus status = api.getComputationStatus();
    }
}
