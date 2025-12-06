package projectapis.process;

import java.util.List;
import java.util.Arrays;
import project.annotations.ProcessAPIPrototype;
import projectapis.ComputationStatus;

public class DataStorageAPIPrototype implements DataStorageAPI {

	@ProcessAPIPrototype
	public static void prototype(DataStorageAPI api) {
		// pretend client usage
		List<Integer> inputs = api.loadIntegers("input.txt", ",");
		api.storeResults("output.txt", Arrays.asList(145L, 720L));
		ComputationStatus status = api.getComputationStatus();
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
	public void saveComputation() {
		// TODO Auto-generated method stub
		
	}
}
