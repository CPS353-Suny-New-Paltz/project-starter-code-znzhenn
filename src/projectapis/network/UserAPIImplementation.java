package projectapis.network;

import project.annotations.NetworkAPI;
import project.annotations.ProcessAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.process.DataStorageAPIImplementation;

import java.util.List;
import java.util.ArrayList;

@NetworkAPI
@ProcessAPI
public class UserAPIImplementation implements UserAPI {

	private String input;
	private String output;
	private String delimiter = ",";
	private final DataStorageAPIImplementation dataStorage = new DataStorageAPIImplementation();
	private final FactorialAPIImplementation factorialAPI = new FactorialAPIImplementation();

	@Override
	public void setInput(String input) {
		this.input = input;

	}

	@Override
	public void setOutput(String output) {
		this.output = output;

	}

	@Override
	public void setDelimiter(String delimiter) {
		if (delimiter != null && !delimiter.isEmpty()) {
			this.delimiter = delimiter;
		}

	}

	@Override
	public long executeComputation() {
		// dataStorage to load numbers
		List<Integer> numbers = dataStorage.loadIntegers(input, delimiter);
		if (numbers.isEmpty())
			return 0;

		long sum = 0;
		for (int number : numbers) {
			sum += factorialAPI.computeDigitFactorialSum(number);
		}

		// Store result in dataStorage
		List<Long> results = new ArrayList<>();
		results.add(sum);
		dataStorage.storeResults(output, results);

		return sum;
	}

	@Override
	public long fetchFactorialOfSum() {
		return dataStorage.fetchComputation();
	}

	@Override
	public long fetchExistingResult() {
		return dataStorage.fetchComputation();
	}

}
