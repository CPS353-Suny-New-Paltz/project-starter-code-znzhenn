package projectapis.process;

import java.util.ArrayList;
import java.util.List;
import projectapis.ComputationStatus;
import project.annotations.ProcessAPI;

@ProcessAPI
public class DataStorageAPIImplementation implements DataStorageAPI {

	private List<Integer> loadedNumbers = new ArrayList<>();
	private List<Long> savedResults = new ArrayList<>();
	private ComputationStatus status = ComputationStatus.NOT_EXISTS;
	private String savedData = "";

	public DataStorageAPIImplementation() {
	}

	@Override
	public List<Integer> loadIntegers(String inputSource, String delimiter) {
		loadedNumbers.clear();

		if (inputSource == null || inputSource.isEmpty()) {
			status = ComputationStatus.NOT_EXISTS;
			return loadedNumbers;
		}

		String[] parts = inputSource.split(delimiter);
		for (String part : parts) {
			part = part.trim();
			if (!part.isEmpty()) {
				try {
					loadedNumbers.add(Integer.parseInt(part));
				} catch (NumberFormatException e) {
					System.out.println("Warning: invalid number '" + part + "' skipped.");
				}
			}
		}

		status = loadedNumbers.isEmpty() ? ComputationStatus.NOT_EXISTS : ComputationStatus.EXISTS;
		if (!loadedNumbers.isEmpty())
			savedData = inputSource;

		return loadedNumbers;
	}

	@Override
	public void storeResults(String outputSource, List<Long> results) {
		savedResults.clear();
		savedResults.addAll(results);
		status = ComputationStatus.EXISTS;
	}

	@Override
	public long fetchComputation() {
		return savedResults.isEmpty() ? 0L : savedResults.get(0);
	}

	@Override
	public ComputationStatus getComputationStatus() {
		return status;
	}

	@Override
	public String loadData() {
		return savedData;
	}

	@Override
	public void saveComputation() {
		status = ComputationStatus.EXISTS;
	}
}
