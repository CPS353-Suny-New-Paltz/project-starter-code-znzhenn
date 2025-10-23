package projectapis.process;

import java.util.ArrayList;
import java.util.List;
import projectapis.ComputationStatus;
import project.annotations.ProcessAPI;

@ProcessAPI
public class DataStorageAPIImplementation implements DataStorageAPI {

    private List<Long> savedResults = new ArrayList<>();
    private boolean computationDone = false;
    private String savedData = "";

    public DataStorageAPIImplementation() { }

    @Override
    public List<Integer> loadIntegers(String inputSource, String delimiter) {
        return new ArrayList<>(); // minimal stub
    }

    @Override
    public void storeResults(String outputSource, List<Long> results) {
        savedResults.clear();
        savedResults.addAll(results);
        computationDone = true;
    }

    @Override
    public long fetchComputation() {
        return savedResults.isEmpty() ? 0L : savedResults.get(0);
    }

    @Override
    public ComputationStatus getComputationStatus() {
        return computationDone ? ComputationStatus.EXISTS : ComputationStatus.NOT_EXISTS;
    }

    @Override
    public String loadData() {
        return savedData;
    }

    @Override
    public void saveComputation() {
        computationDone = true;
    }
}
