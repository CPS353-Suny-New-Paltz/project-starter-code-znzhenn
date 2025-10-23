package projectapis.process;

import java.util.List;
import java.util.ArrayList;
import projectapis.ComputationStatus;
import projectapis.process.DataStorageAPIImplementation;
import project.annotations.ProcessAPI;

@ProcessAPI
public class DataStorageAPIImplementation implements DataStorageAPI {

    private List<Long> saveComputation = new ArrayList<>();
    private boolean computationDone = false;
    private String savedData = "";

    public DataStorageAPIImplementation() { }

    @Override
    public void saveData(String data) {
        savedData = data;
        System.out.println("Saving data: " + data);
    }

    @Override
    public String loadData() {
        return savedData.isEmpty() ? "Loaded data" : savedData;
    }

    @Override
    public void saveComputation() {
        computationDone = true;
    }

    @Override
    public long fetchComputation() {
        return saveComputation.isEmpty() ? 0 : saveComputation.get(0);
    }

    @Override
    public ComputationStatus getComputationStatus() {
        return computationDone ? ComputationStatus.EXISTS : ComputationStatus.NOT_EXISTS;
    }

    @Override
    public List<Integer> loadIntegers(String inputSource, String delimiter) {
        return new ArrayList<>();
    }

    @Override
    public void storeResults(String outputSource, List<Long> results) {
        saveComputation.clear();
        saveComputation.addAll(results);
        computationDone = true;
        results.forEach(r -> System.out.println("Result: " + r));
    }
}
