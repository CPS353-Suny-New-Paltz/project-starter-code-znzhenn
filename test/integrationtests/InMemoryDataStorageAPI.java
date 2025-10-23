package integrationtests;

import java.util.List;

import process.DataStorageAPI;

import java.util.ArrayList;

import projectapis.ComputationStatus;

public class InMemoryDataStorageAPI implements DataStorageAPI {

    private final InMemoryInput input;
    private final InMemoryOutput output;
    private boolean computationDone = false;
    private List<Long> storedResults = new ArrayList<>();

    public InMemoryDataStorageAPI(InMemoryInput input, InMemoryOutput output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public List<Integer> loadIntegers(String inputSource, String delimiter) {
        return input.getInput();
    }

    @Override
    public void storeResults(String outputSource, List<Long> results) {
        storedResults.clear();
        storedResults.addAll(results);
        computationDone = true;
        for (Long r : results) {
            output.write(String.valueOf(r));
        }
    }

    @Override
    public long fetchComputation() {
        return storedResults.isEmpty() ? 0 : storedResults.get(0);
    }

    @Override
    public ComputationStatus getComputationStatus() {
        return computationDone ? ComputationStatus.EXISTS : ComputationStatus.NOT_EXISTS;
    }

    @Override
    public String loadData() {
        return "Loaded data"; // simple placeholder
    }

    @Override
    public void saveData(String data) {
        output.write(data);
    }

    @Override
    public void saveComputation() {
        computationDone = true;
    }
}
