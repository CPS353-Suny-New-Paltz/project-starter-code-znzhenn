package projectapis.process;

import java.util.List;
import projectapis.ComputationStatus;
import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorageAPI {

	// load list of ints
	List<Integer> loadIntegers(String inputSource, String delimiter);

	// store results
	void storeResults(String outputSource, List<Long> results);

	// fetch computation
	long fetchComputation();

	// check if it exists
	ComputationStatus getComputationStatus();

	// load data
	String loadData();

	// mark saved
	void saveComputation();
}
