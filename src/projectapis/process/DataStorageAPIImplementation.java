package projectapis.process;

import java.util.List;
import java.util.ArrayList;
import projectapis.ComputationStatus;
import project.annotations.ProcessAPI;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;

@ProcessAPI
public class DataStorageAPIImplementation implements DataStorageAPI {
	
	public void DataStorageAPIImplementation() {
		
	}
	
    @Override
    public void saveData(String data) {
        System.out.println("Saving data: " + data);
    }

    @Override
    public String loadData() {
        return "Loaded data";
    }

    @Override
    public void saveComputation() {
        // Stub implementation
    }

    @Override
    public long fetchComputation() {
        return 0;
    }

    @Override
    public ComputationStatus getComputationStatus() {
    	return ComputationStatus.NOT_EXISTS;
    }

    @Override
    public List<Integer> loadIntegers(String inputSource, String delimiter) {
    	return new ArrayList<>(); // empty list for tests
    }

    @Override
    public void storeResults(String outputSource, List<Long> results) {
    	results.forEach(r -> System.out.println("Result: " + r));
    }
}
