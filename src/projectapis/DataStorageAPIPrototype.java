package projectapis;

import project.annotations.ProcessAPIPrototype;

public class DataStorageAPIPrototype {

    @ProcessAPIPrototype
    public static DataStorageAPI create() {
        return new DataStorageAPI() {
            @Override
            public void saveComputation() {
            }

            @Override
            public long fetchComputation() {
                return 0L;
            }

            @Override
            public ComputationStatus getComputationStatus() {
                return ComputationStatus.NOT_EXISTS; //returns something for now
            }
        };
    }
}
