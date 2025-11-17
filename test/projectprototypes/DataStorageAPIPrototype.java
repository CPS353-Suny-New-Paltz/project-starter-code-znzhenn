package projectprototypes;

import java.util.ArrayList;
import java.util.List;

import project.annotations.ProcessAPIPrototype;
import projectapis.ComputationStatus;
import projectapis.process.DataStorageAPI;

@ProcessAPIPrototype
public class DataStorageAPIPrototype implements DataStorageAPI {

    private List<Long> storedResults = new ArrayList<>();

    @Override
    public List<Integer> loadIntegers(String inputSource, String delimiter) {
        // Simulate reading integers from input
        List<Integer> list = new ArrayList<>();
        String data = "1,2,3,4"; // simple prototype data
        for (String s : data.split(delimiter)) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    @Override
    public void storeResults(String outputSource, List<Long> results) {
        // simulate saving
        storedResults.clear();
        storedResults.addAll(results);
    }

    @Override
    public long fetchComputation() {
        // return sum of stored results as a dummy computation
        return storedResults.stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public ComputationStatus getComputationStatus() {
        // Correct: return EXISTS if results exist, else NOT_EXISTS
        return storedResults.isEmpty() ? ComputationStatus.NOT_EXISTS : ComputationStatus.EXISTS;
    }

    @Override
    public String loadData() {
        return "1,2,3,4";
    }

    @Override
    public void saveComputation() {
        // simulate saving
    }
}
