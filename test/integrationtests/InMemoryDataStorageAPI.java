package integrationtests;

import java.util.List;

import process.DataStorageAPI;
import projectapis.ComputationStatus;

public class InMemoryDataStorageAPI implements DataStorageAPI {
	
	private final InMemoryInput input;
	private final InMemoryOutput output;
	
	public InMemoryDataStorageAPI(InMemoryInput input, InMemoryOutput output) {
        this.input = input;
        this.output = output;
    }
	
	public List<Integer> readInput() {
		return input.getIntegers();
	}
	
	public void storeDataResults(String key, List<Long> results){
		for (Long result : results) {
			output.write(String.valueOf(result));
		}
		
	}

	@Override
	public List<Integer> loadIntegers(String inputSource, String delimiter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeResults(String outputSource, List<Long> results) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long fetchComputation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ComputationStatus getComputationStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveData(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveComputation() {
		// TODO Auto-generated method stub
		
	}
	
	
}